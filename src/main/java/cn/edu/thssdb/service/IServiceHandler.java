package cn.edu.thssdb.service;

import cn.edu.thssdb.exception.DatabaseNotExistException;
import cn.edu.thssdb.plan.LogicalGenerator;
import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.plan.impl.*;
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
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.schema.Database; //?
import cn.edu.thssdb.utils.Global;
import cn.edu.thssdb.utils.StatusUtil;
import org.apache.thrift.TException;

import java.rmi.server.ExportException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class IServiceHandler implements IService.Iface {

  private static final AtomicInteger sessionCnt = new AtomicInteger(0);
  private final Manager manager;

  public IServiceHandler() {
    manager = Manager.getInstance();
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
    LogicalPlan plan = LogicalGenerator.generate(req.statement);
    String name, msg;
    System.out.println("[DEBUG] " + plan);
    try {
      switch (plan.getType()) {
        case CREATE_DB:
          name = ((CreateDatabasePlan)plan).getDatabaseName();
          msg = "Database " + name + " is created.";
          boolean used = manager.createDatabaseIfNotExists(name);
          if (used)
            msg = msg.concat("\nDatabase " + name + " is activated.");
          return new ExecuteStatementResp(StatusUtil.success(msg), false);

        case DROP_DB:
          name = ((DropDatabasePlan)plan).getDatabaseName();
          msg = "Database " + name + " is dropped.";
          manager.deleteDatabase(name);
          return new ExecuteStatementResp(StatusUtil.success(msg), false);

        case USE_DB:
          name = ((UseDatabasePlan)plan).getDatabaseName();
          msg = "Database " + name + " is activated.";
          manager.switchDatabase(name);
          return new ExecuteStatementResp(StatusUtil.success(msg), false);

        case CREATE_TABLE:
          name = ((CreateTablePlan)plan).getTableName();
          msg = "Table " + name + " is created.";
          manager.getCurrentDatabase().create(name, ((CreateTablePlan)plan).getColumns().toArray(new Column[0])); // ??
          return new ExecuteStatementResp(StatusUtil.success(msg), false);

        case DROP_TABLE:
          name = ((DropTablePlan)plan).getTableName();
          msg = "Table " + name + " is dropped.";
          manager.getCurrentDatabase().drop(name);
          return new ExecuteStatementResp(StatusUtil.success(msg), false);

        case SHOW_TABLE:
          name = ((ShowTablePlan)plan).getDatabaseName();
          msg = manager.getDatabase(name).getTables();
          return new ExecuteStatementResp(StatusUtil.success(msg), false);

        default:

      }
    } catch (Exception e) {
      return new ExecuteStatementResp(StatusUtil.fail(e.getMessage()), false);
    }
    return null;
//    return new ExecuteStatementResp(StatusUtil.fail("Unknown statement, please try again."), false);
  }
}
