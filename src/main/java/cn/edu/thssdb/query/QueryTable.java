package cn.edu.thssdb.query;

import cn.edu.thssdb.LockManager;
import cn.edu.thssdb.parser.MultipleConditions;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Entry;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class QueryTable implements Iterator<Row> {

  private Iterator<Row> iterator;
  private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  public ArrayList<Row> resultTable = new ArrayList<>();

  public ArrayList<Column> originColumns = new ArrayList<>();
  public int originPrimaryKey = -1;
  public ArrayList<Column> resultColumns;

  private String originTableName;

  public QueryTable(LockManager lockManager) {}

  public QueryTable(LockManager lockManager, Table originTable) {
    // deadlock here
    //    ArrayList<Column> columns = new ArrayList<>();
    originColumns.addAll(originTable.getColumns());
    for (Column column : originColumns) {
      column.setTableName(originTable.getTableName());
    }
    originTableName = originTable.getTableName();
    originPrimaryKey = originTable.getPrimaryIndex();
    //    this.resultTable =
    //        new Table(
    //            null, originTableName, columns.toArray(new Column[0]),
    // originTable.getPrimaryIndex());
    this.useWriteLock(lockManager, "resultTable");
    // copy rows
    for (Row row : originTable) {
      Row newRow = new Row();
      newRow.addAll(row);
      resultTable.add(newRow);
      //      resultTable.insertWithoutLock(newRow);
    }
    resultColumns = originColumns;
    this.iterator = resultTable.iterator();
  }

  public QueryTable(LockManager lockManager, Table originTable, MultipleConditions conditions) {
    originColumns.addAll(originTable.getColumns());
    for (Column column : originColumns) {
      column.setTableName(originTable.getTableName());
    }
    originTableName = originTable.getTableName();
    originPrimaryKey = originTable.getPrimaryIndex();
    //    this.resultTable =
    //        new Table(
    //            null, originTableName, columns.toArray(new Column[0]),
    // originTable.getPrimaryIndex());
    this.useWriteLock(lockManager, "resultTable");
    if (originTable.getRowByPrimaryIndex(
            new Entry(
                (Comparable)
                    conditions.getValue(
                        originTable.getColumnByName(
                            originTable
                                .getColumns()
                                .get(originTable.getPrimaryIndex())
                                .getName()))))
        != null) {
      resultTable.add(
          originTable.getRowByPrimaryIndex(
              new Entry(
                  (Comparable)
                      conditions.getValue(
                          originTable.getColumnByName(
                              originTable
                                  .getColumns()
                                  .get(originTable.getPrimaryIndex())
                                  .getName())))));
    }
    resultColumns = originColumns;
    this.iterator = resultTable.iterator();
  }

  public void join(LockManager lockManager, QueryTable queryTable, MultipleConditions conditions) {
    // TODO
    ArrayList<Column> columns = new ArrayList<>();
    //    String originTableName = resultTable.getTableName();
    String joinTableName = queryTable.originTableName;
    //    ArrayList<Column> originColumns = resultTable.getColumns();
    ArrayList<Column> joinColumns = queryTable.originColumns;
    int joinPrimaryKey = queryTable.originPrimaryKey;
    for (Column column : joinColumns) {
      column.setTableName(joinTableName);
    }
    MetaInfo originMetaInfo = new MetaInfo(originTableName, originColumns, originPrimaryKey);
    MetaInfo joinMetaInfo = new MetaInfo(joinTableName, joinColumns, joinPrimaryKey);
    columns.addAll(originColumns);
    columns.addAll(joinColumns);
    QueryTable _resultTable = new QueryTable(lockManager);
    _resultTable.useWriteLock(lockManager, "join");
    // 1. is there a primary key then find by binary search

    // 2. filter rows
    for (Row originRow : resultTable) {
      for (Row joinRow : queryTable.resultTable) {
        if (conditions == null
            || conditions.check(originRow, joinRow, originMetaInfo, joinMetaInfo)) {
          Row newRow = new Row();
          newRow.addAll(originRow);
          newRow.addAll(joinRow);
          _resultTable.resultTable.add(newRow);
          //          _resultTable.insertWithoutLock(newRow);
        }
      }
    }
    resultTable = _resultTable.resultTable;
    resultColumns = columns;
    this.iterator = resultTable.iterator();
  }

  public void join(LockManager lockManager, Table table, MultipleConditions conditions) {
    // TODO multiple joins
    ArrayList<Column> columns = new ArrayList<>();
    //    String originTableName = resultTable.getTableName();
    String joinTableName = table.getTableName();
    //    ArrayList<Column> originColumns = resultTable.getColumns();
    ArrayList<Column> joinColumns = table.getColumns();
    int joinPrimaryKey = table.getPrimaryIndex();
    MetaInfo originMetaInfo = new MetaInfo(originTableName, originColumns, originPrimaryKey);
    MetaInfo joinMetaInfo = new MetaInfo(joinTableName, joinColumns, joinPrimaryKey);
    for (Column column : joinColumns) {
      column.setTableName(joinTableName);
    }
    columns.addAll(originColumns);
    columns.addAll(joinColumns);
    QueryTable _resultTable = new QueryTable(lockManager);
    _resultTable.useWriteLock(lockManager, "join");
    // 2. filter rows
    // 1. are all primary keys?
    Boolean isAllPrimaryKeys = false;
    // TODO: different sequence
    if (conditions != null) {
      isAllPrimaryKeys =
          conditions.isBothPK(
              originColumns.get(originPrimaryKey).getName(),
              joinColumns.get(joinPrimaryKey).getName());
    }

    if (isAllPrimaryKeys)
    // use binary search
    {
      for (Row row : resultTable) {
        if (table.getRowByPrimaryIndex(row.getEntries().get(originPrimaryKey)) != null) {
          Row newRow = new Row();
          newRow.addAll(row);
          newRow.addAll(table.getRowByPrimaryIndex(row.getEntries().get(originPrimaryKey)));
          _resultTable.resultTable.add(newRow);
        }
      }
    } else {
      for (Row originRow : resultTable) {
        for (Row joinRow : table) {
          if (conditions == null
              || conditions.check(originRow, joinRow, originMetaInfo, joinMetaInfo)) {
            Row newRow = new Row();
            newRow.addAll(originRow);
            newRow.addAll(joinRow);
            _resultTable.resultTable.add(newRow);
          }
        }
      }
    }
    resultTable = _resultTable.resultTable;
    resultColumns = columns;
    //    metaInfo = new MetaInfo(originTableName, originColumns);
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
    return resultColumns;
  }

  public ArrayList<String> getColumnNames() {
    ArrayList<String> names = new ArrayList<>();
    for (Column column : resultColumns) {
      names.add(column.getName());
    }
    return names;
  }

  public Column getColumnByName(String name) {
    for (Column column : resultColumns) {
      if (column.getName().equals(name)) {
        return column;
      }
    }
    return null;
  }

  //  public int getPrimaryIndex() {
  //    return resultTable.getPrimaryIndex();
  //  }

  public Iterator<Row> iterator() {
    return resultTable.iterator();
  }

  public void useWriteLock(LockManager lockManager, String user) {
    if (!lock.isWriteLockedByCurrentThread()) {
      //      System.out.println(user + " wait for write lock.");
      lock.writeLock().lock();
      lockManager.recordLock(this.hashCode(), lock);
      System.out.println(
          this.hashCode()
              + " "
              + user
              + " get write lock. Write lock hold: "
              + lock.getWriteHoldCount());
    } else {
      System.out.println(
          this.hashCode()
              + " "
              + user
              + " has this write lock. Write lock hold: "
              + lock.getWriteHoldCount());
      //      lockManager.recordLock(this.hashCode(), lock);
    }
  }
}
