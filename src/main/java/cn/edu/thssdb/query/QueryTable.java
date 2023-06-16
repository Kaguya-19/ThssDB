package cn.edu.thssdb.query;

import cn.edu.thssdb.LockManager;
import cn.edu.thssdb.parser.MultipleConditions;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;

import java.util.ArrayList;
import java.util.Iterator;

public class QueryTable implements Iterator<Row> {

  private Iterator<Row> iterator;
  public Table resultTable;

  public QueryTable(LockManager lockManager, Table originTable) {
    // deadlock here
    ArrayList<Column> columns = new ArrayList<>();
    columns.addAll(originTable.getColumns());
    for (Column column : columns) {
      column.setTableName(originTable.getTableName());
    }
    String originTableName = originTable.getTableName();
    this.resultTable =
        new Table(
            null, originTableName, columns.toArray(new Column[0]), originTable.getPrimaryIndex());
    resultTable.useWriteLock(lockManager, "resultTable");
    // copy rows
    for (Row row : originTable) {
      Row newRow = new Row();
      newRow.addAll(row);
      resultTable.insertWithoutLock(newRow);
    }

    this.iterator = resultTable.iterator();
  }

  public QueryTable(
      LockManager lockManager, Table originTable, Table joinTable, MultipleConditions conditions) {
    // 1. join two tables
    ArrayList<Column> columns = new ArrayList<>();
    String originTableName = originTable.getTableName();
    String joinTableName = joinTable.getTableName();
    ArrayList<Column> originColumns = originTable.getColumns();
    ArrayList<Column> joinColumns = joinTable.getColumns();
    MetaInfo originMetaInfo = new MetaInfo(originTableName, originColumns);
    MetaInfo joinMetaInfo = new MetaInfo(joinTableName, joinColumns);
    columns.addAll(originColumns);
    columns.addAll(joinColumns);
    for (int i = 0; i < originTable.getColumns().size(); i++) {
      columns.get(i).setTableName(originTableName);
    }
    for (int i = originTable.getColumns().size(); i < columns.size(); i++) {
      columns.get(i).setTableName(joinTableName);
    }

    resultTable =
        new Table(null, originTableName + "_" + joinTableName, columns.toArray(new Column[0]), 0);
    // 2. filter rows
    // TODO:optimize O(n^2) is unacceptable
    for (Row originRow : originTable) {
      for (Row joinRow : joinTable) {
        if (conditions.check(originRow, joinRow, originMetaInfo, joinMetaInfo)) {
          Row newRow = new Row();
          newRow.addAll(originRow);
          newRow.addAll(joinRow);
          resultTable.insert(lockManager, newRow);
        }
      }
    }
    this.iterator = resultTable.iterator();
  }

  public void join(LockManager lockManager, Table table, MultipleConditions conditions) {
    // TODO
    ArrayList<Column> columns = new ArrayList<>();
    String originTableName = resultTable.getTableName();
    String joinTableName = table.getTableName();
    ArrayList<Column> originColumns = resultTable.getColumns();
    ArrayList<Column> joinColumns = table.getColumns();
    MetaInfo originMetaInfo = new MetaInfo(originTableName, originColumns);
    MetaInfo joinMetaInfo = new MetaInfo(joinTableName, joinColumns);
    columns.addAll(originColumns);
    columns.addAll(joinColumns);
    Table _resultTable =
        new Table(null, originTableName + "_" + joinTableName, columns.toArray(new Column[0]), 0);
    _resultTable.useWriteLock(lockManager, "join");
    // 2. filter rows
    for (Row originRow : resultTable) {
      for (Row joinRow : table) {
        if (conditions.check(originRow, joinRow, originMetaInfo, joinMetaInfo)) {
          Row newRow = new Row();
          newRow.addAll(originRow);
          newRow.addAll(joinRow);
          _resultTable.insertWithoutLock(newRow);
        }
      }
    }
    resultTable = _resultTable;
    this.iterator = resultTable.iterator();
  }

  @Override
  public boolean hasNext() {
    // TODO
    return resultTable.iterator().hasNext();
  }

  @Override
  public Row next() {
    // TODO
    return resultTable.iterator().next();
  }

  public ArrayList<Column> getColumns() {
    return resultTable.columns;
  }

  public ArrayList<String> getColumnNames() {
    ArrayList<String> names = new ArrayList<>();
    for (Column column : resultTable.columns) {
      names.add(column.getName());
    }
    return names;
  }

  public Column getColumnByName(String name) {
    for (Column column : resultTable.columns) {
      if (column.getName().equals(name)) {
        return column;
      }
    }
    return null;
  }

  public int getPrimaryIndex() {
    return resultTable.getPrimaryIndex();
  }

  public Iterator<Row> iterator() {
    return resultTable.iterator();
  }
}
