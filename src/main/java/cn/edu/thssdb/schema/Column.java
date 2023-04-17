package cn.edu.thssdb.schema;

import cn.edu.thssdb.type.ColumnType;

public class Column implements Comparable<Column> {
  private String name;
  private ColumnType type;
  private int primary;
  private boolean notNull;
  private int maxLength;

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

  public String getName() { return name; }
  public void setPrimary(int primary) { this.primary = primary; }

  public static ColumnType getType(String type) {
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

  public String toString() {
    return name + ',' + type + ',' + primary + ',' + notNull + ',' + maxLength;
  }
}
