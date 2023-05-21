package cn.edu.thssdb.parser;

public class ColumnFullName {
  private String tableName = null;
  private String columnName;

  ColumnFullName(String columnName, String tableName) {
    this.tableName = tableName;
    this.columnName = columnName;
  }

  public String getTableName() {
    return this.tableName;
  }

  public String getColumnName() {
    return this.columnName;
  }
}
