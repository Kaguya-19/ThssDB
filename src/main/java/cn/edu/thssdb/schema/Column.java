package cn.edu.thssdb.schema;

import cn.edu.thssdb.type.ColumnType;

public class Column implements Comparable<Column> {
  private String name;
  private ColumnType type;
  private int primary;
  private boolean notNull;
  private int maxLength;
  public String tableName;

  public Column(String name, ColumnType type, int primary, boolean notNull, int maxLength) {
    this.name = name;
    this.type = type;
    this.primary = primary;
    this.notNull = notNull;
    this.maxLength = maxLength;
  }

  @Override
  public int compareTo(Column e) {
    return name.compareTo(e.name);
  }

  public String getName() {
    return name;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setPrimary(int primary) {
    this.primary = primary;
  }

  public boolean isPrimary() {
    return primary == 1;
  }

  public static ColumnType str2ColType(String type) {
    type = type.toUpperCase();
    switch (type) {
      case "INT":
        return ColumnType.INT;
      case "LONG":
        return ColumnType.LONG;
      case "FLOAT":
        return ColumnType.FLOAT;
      case "DOUBLE":
        return ColumnType.DOUBLE;
      case "STRING":
        return ColumnType.STRING;
      default:
        return null;
    }
  }

  public ColumnType getType() {
    return type;
  }

  public String toString() {
    return name + ',' + type + ',' + primary + ',' + notNull + ',' + maxLength;
  }

  public String toCommand() {
    String buffer = name + " " + type.name();
    if (type == ColumnType.STRING) {
      buffer = buffer.concat("(" + maxLength + ")");
    }
    if (notNull) buffer = buffer.concat(" NOT NULL");
    return buffer;
  }

  public static String toPrimary(String columnName) {
    return !columnName.isEmpty() ? "PRIMARY KEY (" + columnName + ")" : "";
  }
}
