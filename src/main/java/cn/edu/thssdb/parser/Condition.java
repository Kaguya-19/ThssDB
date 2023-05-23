package cn.edu.thssdb.parser;

import cn.edu.thssdb.query.MetaInfo;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;

public class Condition {
  private ColumnFullName c1;
  private ColumnFullName c2 = null;
  private Object value;
  private String op;

  // TODO:fix for complex expression
  public Condition(ColumnFullName c1, ColumnFullName c2, String op) {
    this.c1 = c1;
    this.c2 = c2;
    this.op = op;
  }

  public Condition(ColumnFullName c1, Object value, String op) {
    this.c1 = c1;
    this.value = value;
    this.op = op;
  }

  public boolean check(Row row, Table table) {
    if (c2 == null) {
      return checkLiteral(row, table);
    } else {
      return checkColumn(row, table);
    }
  }

  public boolean check(Row oriRow, Row joinRow, MetaInfo oriMetaInfo, MetaInfo joinMetaInfo) {
    if (c2 == null) {
      return checkLiteral(oriRow, joinRow, oriMetaInfo, joinMetaInfo);
    } else {
      return checkColumn(oriRow, joinRow, oriMetaInfo, joinMetaInfo);
    }
  }

  private boolean checkLiteral(Row row, Table table) {
    // TODO: set data structure for row
    int index = table.getColumns().indexOf(c1.getColumnName());
    Object v1 = row.getEntries().get(index).value;
    switch (op) {
      case "=":
        return v1.equals(value);
      case ">":
        return ((Comparable) v1).compareTo(value) > 0;
      case "<":
        return ((Comparable) v1).compareTo(value) < 0;
      case ">=":
        return ((Comparable) v1).compareTo(value) >= 0;
      case "<=":
        return ((Comparable) v1).compareTo(value) <= 0;
      case "<>":
        return !v1.equals(value);
      default:
        return false;
    }
  }

  private boolean checkLiteral(
      Row oriRow, Row joinRow, MetaInfo oriMetaInfo, MetaInfo joinMetaInfo) {
    String tableName = c1.getTableName();
    String columnName = c1.getColumnName();
    Object v1;
    if (oriMetaInfo.getTableName().equals(tableName)) {
      int index = oriMetaInfo.getColumns().indexOf(columnName);
      v1 = oriRow.getEntries().get(index).value;
    } else if (joinMetaInfo.getTableName().equals(tableName)) {
      int index = joinMetaInfo.getColumns().indexOf(columnName);
      v1 = joinRow.getEntries().get(index).value;
    } else {
      throw new RuntimeException("Table name not found");
    }
    switch (op) {
      case "=":
        return v1.equals(value);
      case ">":
        return ((Comparable) v1).compareTo(value) > 0;
      case "<":
        return ((Comparable) v1).compareTo(value) < 0;
      case ">=":
        return ((Comparable) v1).compareTo(value) >= 0;
      case "<=":
        return ((Comparable) v1).compareTo(value) <= 0;
      case "<>":
        return !v1.equals(value);
      default:
        return false;
    }
  }

  private boolean checkColumn(
      Row oriRow, Row joinRow, MetaInfo oriMetaInfo, MetaInfo joinMetaInfo) {
    String tableName1 = c1.getTableName();
    String columnName1 = c1.getColumnName();
    String tableName2 = c2.getTableName();
    String columnName2 = c2.getColumnName();
    Object v1, v2;
    if (oriMetaInfo.getTableName().equals(tableName1)) {
      int index = oriMetaInfo.getColumns().indexOf(columnName1);
      v1 = oriRow.getEntries().get(index).value;
    } else if (joinMetaInfo.getTableName().equals(tableName1)) {
      int index = joinMetaInfo.getColumns().indexOf(columnName1);
      v1 = joinRow.getEntries().get(index).value;
    } else {
      throw new RuntimeException("Table name not found");
    }
    if (oriMetaInfo.getTableName().equals(tableName2)) {
      int index = oriMetaInfo.getColumns().indexOf(columnName2);
      v2 = oriRow.getEntries().get(index).value;
    } else if (joinMetaInfo.getTableName().equals(tableName2)) {
      int index = joinMetaInfo.getColumns().indexOf(columnName2);
      v2 = joinRow.getEntries().get(index).value;
    } else {
      throw new RuntimeException("Table name not found");
    }
    switch (op) {
      case "=":
        return v1.equals(v2);
      case ">":
        return ((Comparable) v1).compareTo(v2) > 0;
      case "<":
        return ((Comparable) v1).compareTo(v2) < 0;
      case ">=":
        return ((Comparable) v1).compareTo(v2) >= 0;
      case "<=":
        return ((Comparable) v1).compareTo(v2) <= 0;
      case "<>":
        return !v1.equals(v2);
      default:
        return false;
    }
  }

  private boolean checkColumn(Row row, Table table) {
    int index1 = table.getColumns().indexOf(c1.getColumnName());
    int index2 = table.getColumns().indexOf(c2.getColumnName());
    Object v1 = row.getEntries().get(index1).value;
    Object v2 = row.getEntries().get(index2).value;
    switch (op) {
      case "=":
        return v1.equals(v2);
      case ">":
        return ((Comparable) v1).compareTo(v2) > 0;
      case "<":
        return ((Comparable) v1).compareTo(v2) < 0;
      case ">=":
        return ((Comparable) v1).compareTo(v2) >= 0;
      case "<=":
        return ((Comparable) v1).compareTo(v2) <= 0;
      case "<>":
        return !v1.equals(v2);
      default:
        return false;
    }
  }
}
