package cn.edu.thssdb.schema;

import cn.edu.thssdb.Global;
import cn.edu.thssdb.exception.DuplicateTableException;
import cn.edu.thssdb.exception.TableNotExistException;
import cn.edu.thssdb.query.QueryResult;
import cn.edu.thssdb.query.QueryTable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {

  private String databaseName;// check legal name
  private HashMap<String, Table> tables;
  ReentrantReadWriteLock lock;

  public Database(String databaseName) {
    this.databaseName = databaseName;
    this.tables = new HashMap<>();
    this.lock = new ReentrantReadWriteLock();
//    recover();
  }

  private void persist(String delTable) {

    if(delTable != null && !delTable.isEmpty()) {
//      removeTableFile(delTable);
    } else {
      System.out.println("No table is dropped.");
    }
    // update manager.script
    String scriptFilePath = Global.MANAGER_DIR.concat(databaseName + File.separator + databaseName + ".script");
    try {
      File scriptFile = new File(scriptFilePath);
      if(scriptFile.exists()) {
        scriptFile.delete();
      }
      scriptFile.createNewFile();

      FileOutputStream fos = new FileOutputStream(scriptFilePath);
      OutputStreamWriter writer = new OutputStreamWriter(fos);
      // only add current db
      for(String tableName: tables.keySet()) {
        Table table = tables.get(tableName);
        String buffer = "CREATE TABLE ".concat(tableName + '(');
        String primary = "";
        for(Column column: table.columns) {
          if(column.isPrimary()) primary = column.getName();
          buffer = buffer.concat(column.toCommand() + ',');
        }
        buffer = buffer.concat(Column.toPrimary(primary));
        buffer = buffer.concat(")\n");
        System.out.println("[DEBUG] " + buffer);
        writer.write(buffer);
        // TODO: create or modify table file
      }
      writer.close();
      fos.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Create table.
  public void create(String tableName, Column[] columns) {
    if(tables.containsKey(tableName)) {
      throw new DuplicateTableException(tableName);
    }
    Table table = new Table(this.databaseName, tableName, columns);
    tables.put(tableName, table);
    System.out.println("[DEBUG] " + "Table " + tableName + " is created.");
    persist(null);
  }

  // Drop table.
  public void drop(String tableName) {
    if(!tables.containsKey(tableName)) {
      throw new TableNotExistException(tableName);
    }
    tables.remove(tableName);
    System.out.println("[DEBUG] " + "Table " + tableName + " is dropped.");
    persist(tableName);
  }

  public String select(QueryTable[] queryTables) {
    // TODO
    QueryResult queryResult = new QueryResult(queryTables);
    return null;
  }

  private void recover() {
    // TODO

    // check existence

    // if existed, recover

    // if not, create one
//    this.persist();

  }

  public String getTables() {
    String tableNameList = "";
    System.out.println("[DEBUG] " + "Table count: " + tables.values().size());
    int i = 0;
    int n = tables.values().size() - 2;
    for(Table table: tables.values()) {
      tableNameList = tableNameList.concat(table.tableName);
      if(i < n)
        tableNameList = tableNameList + ", ";
    }
    if (tableNameList.length() == 0) {
      return "No tables.";
    }
    return "Tables: " + tableNameList;
  }

  public void quit() {
    // TODO: release lock
  }

  public String getDatabaseName() { return databaseName; }


}
