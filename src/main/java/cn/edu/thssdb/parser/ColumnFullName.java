package cn.edu.thssdb.parser;

public class ColumnFullName {
  private String tableName = null;
  private String columnName;
  private Boolean isPK = false;

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

  public Boolean isPK() {
    return this.isPK;
  }

  public void setIsPrimary(Boolean isPrimary) {
    this.isPK = isPrimary;
  }
}
