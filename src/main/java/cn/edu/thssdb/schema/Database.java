package cn.edu.thssdb.schema;

import cn.edu.thssdb.Global;
import cn.edu.thssdb.exception.DuplicateTableException;
import cn.edu.thssdb.exception.TableNotExistException;
import cn.edu.thssdb.plan.LogicalGenerator;
import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.plan.impl.CreateTablePlan;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {

  private String databaseName; // check legal name
  private HashMap<String, Table> tables;
  ReentrantReadWriteLock lock;

  public Database(String databaseName) {
    this.databaseName = databaseName;
    this.tables = new HashMap<>();
    this.lock = new ReentrantReadWriteLock();
    //    recover();
  }

  // Create table.
  public void create(String tableName, Column[] columns, int primaryIndex) {
    if (tables.containsKey(tableName)) {
      // Duplicate table name.
      throw new DuplicateTableException(tableName);
    }
    Table table = new Table(this.databaseName, tableName, columns, primaryIndex);
    tables.put(tableName, table);
    System.out.println("[DEBUG] " + "Table " + tableName + " is created.");
  }

  // Drop table.
  public void drop(String tableName) {
    if (!tables.containsKey(tableName)) {
      throw new TableNotExistException(tableName);
    }
    tables.remove(tableName);
    System.out.println("[DEBUG] " + "Table " + tableName + " is dropped.");
  }

  //  public String select(QueryTable[] queryTables) {
  //    // TODO
  //    QueryResult queryResult = new QueryResult(queryTables);
  //    return null;
  //  }

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

  public void saveState() {
    persist(null);
  }

  private void persist(String delTable) {

    if (delTable != null && !delTable.isEmpty()) {
      //      removeTableFile(delTable);
    } else {
      System.out.println("No table is dropped.");
    }

    // update manager.script
    String scriptFilePath =
        Global.MANAGER_DIR.concat(databaseName + File.separator + databaseName + ".script");
    try {
      File scriptFile = new File(scriptFilePath);
      if (scriptFile.exists()) {
        scriptFile.delete();
      }
      scriptFile.createNewFile();

      // update table properties
      FileOutputStream fos = new FileOutputStream(scriptFilePath);
      OutputStreamWriter writer = new OutputStreamWriter(fos);
      for (String tableName : tables.keySet()) {
        Table table = tables.get(tableName);
        String buffer = "CREATE TABLE ".concat(tableName + '(');
        String primary = "";
        for (Column column : table.columns) {
          if (column.isPrimary()) primary = column.getName();
          buffer = buffer.concat(column.toCommand() + ',');
        }
        buffer = buffer.concat(Column.toPrimary(primary) + ")\n");
        writer.write(buffer);
        // TODO: create or modify table file
      }
      writer.close();
      fos.close();

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
          create(tableName, ((CreateTablePlan) plan).getColumns().toArray(new Column[0]), pk);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public String getDatabaseName() {
    return databaseName;
  }

  @Override
  public String toString() {
    String buffer = databaseName + '\n';
    for (Table table : tables.values()) {
      buffer = buffer.concat(table.toString() + '\n');
    }
    return buffer;
  }
}
