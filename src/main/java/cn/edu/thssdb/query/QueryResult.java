package cn.edu.thssdb.query;

import cn.edu.thssdb.LockManager;
import cn.edu.thssdb.parser.ColumnFullName;
import cn.edu.thssdb.parser.MultipleConditions;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class QueryResult {

  private ArrayList<Integer> index;
  private String msg;
  private QueryTable resultTable;
  private ArrayList<Column> resultColumns;

  public QueryResult(
      LockManager lockManager,
      QueryTable queryTable,
      ArrayList<ColumnFullName> resultColumns,
      MultipleConditions conditions) {
    // TODO
    this.index = maskColumns(queryTable, resultColumns);
    this.resultTable = new QueryTable(lockManager);

    resultTable.useWriteLock(lockManager, "QueryTable");
    for (Iterator<Row> iterator = queryTable.iterator(); iterator.hasNext(); ) {
      //      ArrayList<Entry> entries = new ArrayList<>();
      Row row = iterator.next();
      if (conditions != null) {
        if (!conditions.check(row, queryTable.resultTable, queryTable.resultColumns)) {
          continue;
        }
      }
      //      for (Integer integer : maskIndex) {
      //        entries.add(row.getEntries().get(integer));
      //      }
      //      Row newRow = new Row(entries.toArray(new Entry[0]));
      resultTable.resultTable.add(row);
    }

    //    /* for debug use */
    //    try {
    //      //      System.out.println("Select sleep 5 secs");
    //      Thread.sleep(5000);
    //    } catch (Exception e) {
    //      e.printStackTrace();
    //    }
  }

  private ArrayList<Integer> maskColumns(
      QueryTable queryTable, ArrayList<ColumnFullName> resultColumns) {

    ArrayList<Column> columns = queryTable.getColumns();
    this.resultColumns = columns;
    ArrayList<Integer> index = new ArrayList<>();
    if (resultColumns == null
        || resultColumns.size() == 0
        || resultColumns.get(0).getColumnName().equals("*")) {
      for (int i = 0; i < columns.size(); i++) {
        index.add(i);
      }
    } else {
      // TODO:optimize O(n^2) is ugly
      for (ColumnFullName resultColumnName : resultColumns) {
        boolean found = false;

        for (Column queryTableColumn : columns) {
          if (resultColumnName.getTableName() != null) {
            if (resultColumnName.getTableName().equals(queryTableColumn.getTableName())) {

              if (resultColumnName.getColumnName().equals("*")
                  || resultColumnName.getColumnName().equals(queryTableColumn.getName())) {
                index.add(columns.indexOf(queryTableColumn));
                found = true;
              }
            }
          } else if (queryTableColumn.getName().equals(resultColumnName.getColumnName())) {
            index.add(columns.indexOf(queryTableColumn));
            found = true;
          }
        }
        if (!found) {
          throw new RuntimeException("Column not found!");
        }
      }
      // if column with same name exists, throw exception
      ArrayList<String> columnNames = queryTable.getColumnNames();
      // TODO:optimize O(n^2) is ugly
      for (int i = 0; i < columnNames.size(); i++) {
        for (int j = i + 1; j < columnNames.size(); j++) {
          // TODO: allow same name in different tables
          // if (columnNames.get(i).equals(columnNames.get(j))) {
          //   throw new RuntimeException("Column with same name exists!");
          // }
        }
      }
    }

    return index;
  }

  public static Row combineRow(LinkedList<Row> rows) {
    // TODO
    return null;
  }

  public Row generateQueryRecord(Row row) {
    // TODO
    return null;
  }

  public List<List<String>> getRowList() {
    List<List<String>> rows = new ArrayList<>();
    for (Row row : resultTable.resultTable) {
      List<String> rowString = new ArrayList<>();
      for (int i : index) {
        rowString.add(row.getEntries().get(i).toString());
      }
      rows.add(rowString);
    }
    return rows;
  }

  public List<String> getColumnsList() {
    List<String> columns = new ArrayList<>();

    for (int i : index) {
      columns.add(resultColumns.get(i).getName());
    }
    return columns;
  }

  public String toString() {
    // Print Column Names
    StringBuilder stringBuilder = new StringBuilder();
    getColumnsList().forEach(column -> stringBuilder.append(column).append(", "));
    stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
    stringBuilder.append("\n");
    getRowList()
        .forEach(
            row ->
                stringBuilder
                    .append(row.toString().substring(1, row.toString().length() - 1))
                    .append("\n"));
    return stringBuilder.toString();
  }
}
