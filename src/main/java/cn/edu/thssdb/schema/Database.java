package cn.edu.thssdb.schema;

import cn.edu.thssdb.Global;
import cn.edu.thssdb.Logger;
import cn.edu.thssdb.exception.DuplicateTableException;
import cn.edu.thssdb.exception.TableNotExistException;
import cn.edu.thssdb.plan.LogicalGenerator;
import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.plan.impl.CreateTablePlan;
import cn.edu.thssdb.plan.impl.DeletePlan;
import cn.edu.thssdb.plan.impl.InsertPlan;
import cn.edu.thssdb.plan.impl.UpdatePlan;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {

  private String databaseName; // check legal name
  private HashMap<String, Table> tables;
  private static ReentrantReadWriteLock lock;
  public Logger logger;

  public Database(String databaseName) {
    this.databaseName = databaseName;
    this.tables = new HashMap<>();
    this.lock = new ReentrantReadWriteLock();
    this.logger =
        new Logger(Global.MANAGER_DIR.concat(databaseName + File.separator), databaseName);
  }

  // TODO: need database lock?
  // Create table.
  public void createTable(String tableName, Column[] columns, int primaryIndex) {
    if (tables.containsKey(tableName)) {
      throw new DuplicateTableException(tableName);
    }
    columns[primaryIndex].setPrimary();
    Table table = new Table(this.databaseName, tableName, columns, primaryIndex);
    tables.put(tableName, table);
    persist(tableName);
    System.out.println("[DEBUG] " + "Table " + tableName + " is created.");
  }

  // TODO: need database lock
  public void dropTable(String tableName) {
    if (!tables.containsKey(tableName)) {
      throw new TableNotExistException(tableName);
    }
    tables.remove(tableName);
    dropTablePersist(tableName);
    System.out.println("[DEBUG] " + "Table " + tableName + " is dropped.");
  }

  // TODO: need database lock
  public String getTables() {
    String tableNameList = "";
    System.out.println("[DEBUG] " + "Table count: " + tables.values().size());
    int i = 0;
    int n = tables.values().size() - 2;
    for (Table table : tables.values()) {
      tableNameList = tableNameList.concat(table.tableName);
      if (i < n) tableNameList = tableNameList + ", ";
    }
    if (tableNameList.length() == 0) {
      return "No tables.";
    }
    return "Tables: " + tableNameList;
  }

  public Table getTableByName(String tableName) {
    if (!tables.containsKey(tableName)) {
      throw new TableNotExistException(tableName);
    }
    return tables.get(tableName);
  }

  private void dropTablePersist(String tableName) {
    String scriptFilePath =
        Global.MANAGER_DIR.concat(databaseName + File.separator + databaseName + ".script");
    String pattern = "CREATE TABLE ".concat(tableName);
    try {
      File file = new File(scriptFilePath);
      File tempFile =
          new File(Global.MANAGER_DIR.concat(databaseName + File.separator + "manager.temp"));
      if (tempFile.exists()) tempFile.delete();
      tempFile.createNewFile();

      BufferedReader reader = new BufferedReader(new FileReader(file));
      BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
      String currentLine;
      while ((currentLine = reader.readLine()) != null) {
        if (!currentLine.startsWith(pattern)) {
          writer.write(currentLine);
          writer.newLine();
        }
      }
      reader.close();
      writer.close();

      if (file.delete()) {
        if (!tempFile.renameTo(file)) {
          System.out.println("Failed to rename the temp file.");
        }
      } else {
        System.out.println("Failed to delete the original file.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Called when modified
  private void persist(String tableName) {

    // update manager.script
    String scriptFilePath =
        Global.MANAGER_DIR.concat(databaseName + File.separator + databaseName + ".script");
    try {
      File scriptFile = new File(scriptFilePath);
      if (!scriptFile.exists()) {
        scriptFile.createNewFile();
      }

      // update table properties
      //      FileOutputStream fos = new FileOutputStream(scriptFilePath);
      BufferedWriter writer = new BufferedWriter(new FileWriter(scriptFilePath, true));
      Table table = tables.get(tableName);
      String buffer = "CREATE TABLE ".concat(tableName + '(');
      String primary = "";
      for (Column column : table.columns) {
        if (column.isPrimary()) primary = column.getName();
        buffer = buffer.concat(column.toCommand() + ',');
      }
      buffer = buffer.concat(Column.toPrimary(primary) + ")\n");
      writer.write(buffer);

      //      for (String tableName : tables.keySet()) {
      //        Table table = tables.get(tableName);
      //        String buffer = "CREATE TABLE ".concat(tableName + '(');
      //        String primary = "";
      //        for (Column column : table.columns) {
      //          if (column.isPrimary()) primary = column.getName();
      //          buffer = buffer.concat(column.toCommand() + ',');
      //        }
      //        buffer = buffer.concat(Column.toPrimary(primary) + ")\n");
      //        writer.write(buffer);
      //      }
      // writer.flush();
      writer.close();
      //      fos.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean recover() {
    String scriptFilePath =
        Global.MANAGER_DIR.concat(databaseName + File.separator + databaseName + ".script");
    try {
      File scriptFile = new File(scriptFilePath);
      if (scriptFile.exists()) {
        FileInputStream fis = new FileInputStream(scriptFilePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
          LogicalPlan plan = (LogicalPlan) LogicalGenerator.generate(line);
          String tableName = ((CreateTablePlan) plan).getTableName();
          int pk = ((CreateTablePlan) plan).getPrimary();
          // if has persist
          if (new File(
                  Global.TABLE_DIR.concat(databaseName + File.separator + tableName + ".table"))
              .exists()) {
            Table table =
                new Table(
                    this.databaseName,
                    tableName,
                    ((CreateTablePlan) plan).getColumns().toArray(new Column[0]),
                    pk);
            tables.put(tableName, table);
            table.recover();
            continue;
          } else {
            // if not has persist
            Column[] columns = ((CreateTablePlan) plan).getColumns().toArray(new Column[0]);
            columns[pk].setPrimary();
            Table table = new Table(this.databaseName, tableName, columns, pk);
            tables.put(tableName, table);
          }
        }
        reader.close();

        for (Table table : tables.values()) {
          table.recover();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    ArrayList<String> logs = logger.readLog();
    for (String log : logs) {
      try {
        System.out.println(log);
        LogicalPlan plan = (LogicalPlan) LogicalGenerator.generate(log);
        switch (plan.getType()) {
          case INSERT:
            ((InsertPlan) plan).doInsert(null, this);
            break;
          case DELETE:
            ((DeletePlan) plan).doDelete(null, this);
            break;
          case UPDATE:
            ((UpdatePlan) plan).doUpdate(null, this);
            break;
          default:
            System.out.println("fail to execute");
            break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return true;
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public String getDatabaseStructure() {
    String buffer = databaseName + '\n';
    for (Table table : tables.values()) {
      buffer = buffer.concat(table.getTableStructure() + '\n');
    }
    return buffer;
  }
}
