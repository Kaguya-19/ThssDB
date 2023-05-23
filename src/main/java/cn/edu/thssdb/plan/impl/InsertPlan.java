package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Entry;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;
import cn.edu.thssdb.type.ColumnType;

import java.util.ArrayList;

public class InsertPlan extends LogicalPlan {

  private String tableName;
  private ArrayList<String> columnNames;
  private ArrayList<ArrayList<String>> valuess;

  public InsertPlan(
      String tableName, ArrayList<String> columnNames, ArrayList<ArrayList<String>> valuess) {
    super(LogicalPlanType.INSERT);
    this.tableName = tableName;
    this.columnNames = columnNames;
    this.valuess = valuess;
  }

  public String getTableName() {
    return tableName;
  }

  public ArrayList<String> getColumnNames() {
    return columnNames;
  }

  public void doInsert(Database database) {
    Table table = database.getTableByName(tableName);

    for (ArrayList<String> valueNames : valuess) {
      ArrayList<Entry> entries = new ArrayList<>();
      if (columnNames == null
          || columnNames.size()
              == 0) { // INSERT INTO table_name VALUES (value1, value2, value3, ...)
        if (valueNames.size() != table.getColumns().size()) {
          throw new RuntimeException("Length Error");
        }
        for (int i = 0; i < valueNames.size(); i++) {
          // check primary key
          entries.add(
              new Entry(getEntryByType(valueNames.get(i), table.getColumns().get(i).getType())));
        }
      } else { // INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2,
        // value3, ...)
        // length check

        // given column names length check
        if (valueNames.size() != columnNames.size()) {
          throw new RuntimeException("Length Error");
        }
        if (columnNames.size() > table.getColumns().size()) {
          throw new RuntimeException("Length Error");
        }

        // default value is null
        // give value to the column
        // if the column is not given, use default value

        // check if the column name is valid
        for (String columnName : columnNames) {
          if (!table.getColumnNames().contains(columnName)) {
            throw new RuntimeException("Column Error");
          }
          if (valueNames.get(columnNames.indexOf(columnName)) == null) {
            entries.add(new Entry(null));
          } else {
            entries.add(
                new Entry(
                    getEntryByType(
                        valueNames.get(columnNames.indexOf(columnName)),
                        table.getColumnByName(columnName).getType())));
          }
        }
      }
      Row row = new Row(entries.toArray(new Entry[0]));
      table.insert(row);
    }
  }

  @Override
  public String toString() {
    return "InsertPlan{" + "tableName='" + tableName + '\'' + '}';
  }

  private Comparable getEntryByType(String value, ColumnType type) {
    try {
      switch (type) {
        case INT:
          return Integer.parseInt(value);
        case LONG:
          return Long.parseLong(value);
        case FLOAT:
          return Float.parseFloat(value);
        case DOUBLE:
          return Double.parseDouble(value);
        case STRING:
          // null
          if (value.equals("null")) {
            return null;
          }
          // delete the ' and "
          return value.substring(1, value.length() - 1);
        default:
          return null;
      }
    } catch (Exception e) {
      throw new RuntimeException("Type Error");
    }
  }
}
