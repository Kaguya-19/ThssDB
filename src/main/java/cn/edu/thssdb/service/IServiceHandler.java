package cn.edu.thssdb.service;

import cn.edu.thssdb.LockManager;
import cn.edu.thssdb.exception.DatabaseNotSelectedException;
import cn.edu.thssdb.plan.LogicalGenerator;
import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.plan.impl.*;
import cn.edu.thssdb.query.QueryResult;
import cn.edu.thssdb.rpc.thrift.ConnectReq;
import cn.edu.thssdb.rpc.thrift.ConnectResp;
import cn.edu.thssdb.rpc.thrift.DisconnectReq;
import cn.edu.thssdb.rpc.thrift.DisconnectResp;
import cn.edu.thssdb.rpc.thrift.ExecuteStatementReq;
import cn.edu.thssdb.rpc.thrift.ExecuteStatementResp;
import cn.edu.thssdb.rpc.thrift.GetTimeReq;
import cn.edu.thssdb.rpc.thrift.GetTimeResp;
import cn.edu.thssdb.rpc.thrift.IService;
import cn.edu.thssdb.rpc.thrift.Status;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.utils.Global;
import cn.edu.thssdb.utils.StatusUtil;
import org.apache.thrift.TException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// import cn.edu.thssdb.schema.Database; //?
// import java.rmi.server.ExportException;
public class IServiceHandler implements IService.Iface {

  private static final AtomicInteger sessionCnt = new AtomicInteger(0);
  private final Manager manager;
  private final ConcurrentHashMap<Long, LockManager> lockManagerList;

  public IServiceHandler() {
    manager = Manager.getInstance();
    lockManagerList = new ConcurrentHashMap<>();
  }

  @Override
  public GetTimeResp getTime(GetTimeReq req) throws TException {
    GetTimeResp resp = new GetTimeResp();
    resp.setTime(new Date().toString());
    resp.setStatus(new Status(Global.SUCCESS_CODE));
    return resp;
  }

  @Override
  public ConnectResp connect(ConnectReq req) throws TException {
    return new ConnectResp(StatusUtil.success(), sessionCnt.getAndIncrement());
  }

  @Override
  public DisconnectResp disconnect(DisconnectReq req) throws TException {
    LockManager lockManager;
    lockManagerList.computeIfAbsent(req.sessionId, LockManager::new);
    lockManager = lockManagerList.get(req.sessionId);
    if (lockManager == null) {
      return new DisconnectResp(StatusUtil.fail("You are not connected. Please connect first."));
    }
    lockManager.commit(req.sessionId);
    lockManagerList.remove(req.sessionId);
    return new DisconnectResp(StatusUtil.success());
  }

  // TODO: How to deal with missing input?
  @Override
  public ExecuteStatementResp executeStatement(ExecuteStatementReq req) throws TException {
    if (req.getSessionId() < 0) {
      return new ExecuteStatementResp(
          StatusUtil.fail("You are not connected. Please connect first."), false);
    }
    // TODO: implement execution logic
    LogicalPlan plan = (LogicalPlan) LogicalGenerator.generate(req.statement);
    String name, msg = null;
    boolean isInterrupted = false;

    LockManager lockManager;
    lockManagerList.computeIfAbsent(req.sessionId, LockManager::new);
    lockManager = lockManagerList.get(req.sessionId);

    // System.out.println("[DEBUG] Plan:" + plan);
    try {
      switch (plan.getType()) {
        case CREATE_DB:
          name = ((CreateDatabasePlan) plan).getDatabaseName();
          msg = "Database " + name + " is created.";
          boolean used = manager.createDatabaseIfNotExists(name, true);
          if (used) msg = msg.concat("\nDatabase " + name + " is activated.");
          break;

        case DROP_DB:
          name = ((DropDatabasePlan) plan).getDatabaseName();
          msg = "Database " + name + " is dropped.";
          manager.deleteDatabase(name);
          break;

        case USE_DB:
          // TODO: need to separate clients using which database
          name = ((UseDatabasePlan) plan).getDatabaseName();
          msg = "Database " + name + " is activated.";
          manager.switchDatabase(name);
          break;

        case SHOW_DB:
          msg = manager.getAllDatabase();
          break;

        case CREATE_TABLE:
          name = ((CreateTablePlan) plan).getTableName();
          msg = "Table " + name + " is created.";
          int pk = ((CreateTablePlan) plan).getPrimary();
          manager
              .getCurrentDatabase()
              .createTable(
                  name, ((CreateTablePlan) plan).getColumns().toArray(new Column[0]), pk); // ??
          break;

        case DROP_TABLE:
          name = ((DropTablePlan) plan).getTableName();
          msg = "Table " + name + " is dropped.";
          manager.getCurrentDatabase().dropTable(name);
          break;

        case SHOW_TABLE:
          name = ((ShowTablePlan) plan).getTableName();
          msg = manager.getCurrentDatabase().getTableByName(name).getTableStructure();
          break;

        case SHOW_ALL_TABLE:
          if (manager.getCurrentDatabase() == null) {
            throw new DatabaseNotSelectedException();
          }
          msg = manager.getCurrentDatabase().getTables();
          break;

        case INSERT:
          try {
            Database db = manager.getCurrentDatabase();
            ((InsertPlan) plan).doInsert(lockManager, db);
            if (lockManager.transactionStarted()) db.logger.writeLog(req.statement);
          } catch (Exception e) {
            isInterrupted = true;
            msg = e.getMessage();
            break;
            //            return new ExecuteStatementResp(StatusUtil.fail(e.getMessage()), false);
          }
          msg = "insert";
          //          return new ExecuteStatementResp(StatusUtil.success("insert"), false);
          break;

        case DELETE:
          try {
            Database db = manager.getCurrentDatabase();
            ((DeletePlan) plan).doDelete(lockManager, db);
            if (lockManager.transactionStarted()) db.logger.writeLog(req.statement);
          } catch (Exception e) {
            isInterrupted = true;
            msg = e.getMessage();
            break;
          }
          msg = "delete";
          break;

        case UPDATE:
          try {
            Database db = manager.getCurrentDatabase();
            ((UpdatePlan) plan).doUpdate(lockManager, db);
            if (lockManager.transactionStarted()) db.logger.writeLog(req.statement);
          } catch (Exception e) {
            isInterrupted = true;
            msg = e.getMessage();
            System.out.println("update exception");
            break;
          }
          msg = "update";
          break;

        case SELECT:
          try {
            // DEBUG
            QueryResult resultTable =
                ((SelectPlan) plan).doSelect(lockManager, manager.getCurrentDatabase());
            ExecuteStatementResp resp =
                new ExecuteStatementResp(StatusUtil.success(resultTable.toString()), false);
            // System.out.println("select result: " + resultTable.toString());
            resp.setRowList(resultTable.getRowList());
            resp.setColumnsList(resultTable.getColumnsList());
            return resp;

            //            break;
          } catch (Exception e) {
            isInterrupted = true;
            msg = e.getMessage();
            break;
          }

        case BEGIN_TRANSACTION:
          lockManager.beginTransaction();
          msg = "begin transaction";
          break;

        case COMMIT:
          lockManager.commit(req.sessionId);
          manager.getCurrentDatabase().logger.cleanLog();
          msg = "committed";
          break;

        case SET_TRANSACTION_ISOLATION_LEVEL:
          lockManager.setTransactionMode(((SetTransactionIsolationLevelPlan) plan).getMode());
          msg = "Transaction isolation mode switch to: " + lockManager.getMode();
          break;

        case QUIT:
          lockManager.commit(req.sessionId);
          manager.getCurrentDatabase().logger.cleanLog();
          msg = "quit";
          break;

        default:
      }
    } catch (Exception e) {
      msg = e.getMessage();
      isInterrupted = true;
    }

    // single statement auto commit
    if (!lockManager.transactionStarted() && plan.getType() != LogicalPlan.LogicalPlanType.COMMIT) {
      System.out.println("Auto commit.");
      lockManager.commit(req.sessionId);
      //      manager.getCurrentDatabase().logger.cleanLog();
    }

    if (isInterrupted) {
      return new ExecuteStatementResp(StatusUtil.fail(msg), false);
    } else if (msg != null) {
      return new ExecuteStatementResp(StatusUtil.success(msg), false);
    }

    // TODO: release all lock in lock manager before return
    //    lockManager.commit();

    return null;
    //    return new ExecuteStatementResp(StatusUtil.fail("Unknown statement, please try again."),
    // false);
  }
}
