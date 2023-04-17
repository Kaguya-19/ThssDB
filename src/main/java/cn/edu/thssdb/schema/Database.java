package cn.edu.thssdb.schema;

import cn.edu.thssdb.exception.DuplicateTableException;
import cn.edu.thssdb.exception.TableNotExistException;
import cn.edu.thssdb.query.QueryResult;
import cn.edu.thssdb.query.QueryTable;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {

  private String databaseName;
  private HashMap<String, Table> tables;
  ReentrantReadWriteLock lock;

  public Database(String databaseName) {
    this.databaseName = databaseName;
    this.tables = new HashMap<>();
    this.lock = new ReentrantReadWriteLock();
//    recover();
  }

  private void persist() {
    // TODO
    String filename = "thssdb/data/test_meta";
    try {
      FileOutputStream fos = new FileOutputStream(filename);
      OutputStreamWriter writer = new OutputStreamWriter(fos);
      for (Table table : tables.values()) {
        for (Column column : table.columns) {
          writer.write(column.toString()+'\n');
        }
      }
      writer.close();
      fos.close();
    } catch (Exception e) {
      // TODO: throw exception
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
  }

  // Drop table.
  public void drop(String tableName) {
    if(!tables.containsKey(tableName)) {
      throw new TableNotExistException(tableName);
    }
    tables.remove(tableName);
    System.out.println("[DEBUG] " + "Table " + tableName + " is dropped.");
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
    this.persist();

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
