package cn.edu.thssdb.schema;

import cn.edu.thssdb.Global;
import cn.edu.thssdb.LockManager;
import cn.edu.thssdb.exception.getLockFailedException;
import cn.edu.thssdb.index.BPlusTree;
import cn.edu.thssdb.utils.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Table implements Iterable<Row> {
  private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private String databaseName;
  public String tableName;
  public ArrayList<Column> columns;
  public BPlusTree<Entry, Row> index;
  private int primaryIndex = 0;

  private String tablePath;

  public Table(String databaseName, String tableName, Column[] columns, int primaryIndex) {
    this.databaseName = databaseName;
    this.tableName = tableName;
    this.columns = new ArrayList<>(Arrays.asList(columns));
    this.index = new BPlusTree<>();
    this.primaryIndex = primaryIndex;
    // TODO
    this.tablePath = Global.TABLE_DIR + databaseName + File.separator + tableName + ".table";
  }

  private void persist() throws IOException {
    // TODO
    serialize();
  }

  private void load() {
    // TODO
    try {
      ArrayList<Row> rows = deserialize();
      for (Row row : rows) {
        index.put(row.getEntries().get(primaryIndex), row);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void recover() {
    // TODO
    if (new File(tablePath).exists()) {
      load();
    }
  }

  public void insert(LockManager lockManager, Row row) {
    if (!lock.isWriteLockedByCurrentThread()) {
//      System.out.println(this.hashCode() + " wait for write lock.");
//      System.out.println(this.hashCode() + " Read: " + lock.getReadHoldCount() + " Write: " + lock.getWriteHoldCount());
      if (lock.getReadHoldCount() > 0) {
        lock.readLock().unlock();
      }
      lock.writeLock().lock();
      lockManager.recordLock(this.hashCode(), lock);
//      System.out.println(this.hashCode() + " Insert get write lock. Write lock hold: " + lock.getWriteHoldCount());
    } else {
//      System.out.println(
//          "Insert has this write lock. Write lock hold: " + lock.getWriteHoldCount());
//      lockManager.recordLock(this.hashCode(), lock);
    }

    insertWithoutLock(row);
//    try {
//      System.out.println("Insert sleep 5 secs");
//      Thread.sleep(5000);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }

    /* release write lock when committed */
  }

  public void insertWithoutLock(Row row) {
    // check primary key
    //    if (index.get(row.getEntries().get(primaryIndex)) != null) {
    //      throw new RuntimeException("Primary Key Error");
    //    }
    index.put(row.getEntries().get(primaryIndex), row);
  }

  public void delete(LockManager lockManager, Row row) {
    if (!lock.isWriteLockedByCurrentThread()) {
//      System.out.println("Delete wait for write lock.");
      lock.writeLock().lock();
      lockManager.recordLock(this.hashCode(), lock);
//      System.out.println(this.hashCode() + " Delete get write lock. Write lock hold: " + lock.getWriteHoldCount());
    } else {
//      System.out.println(
//          "Delete has this write lock. Write lock hold: " + lock.getWriteHoldCount());
//      lockManager.recordLock(this.hashCode(), lock);
    }

    deleteWithoutLock(row);
//    try {
//      System.out.println("Delete sleep 5 secs");
//      Thread.sleep(5000);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }

    /* release write lock when committed */
  }

  public void deleteWithoutLock(Row row) {
    index.remove(row.getEntries().get(primaryIndex));
  }

  public void update(LockManager lockManager, Row oldRow, Row newRow) {
    if (!lock.isWriteLockedByCurrentThread()) {
//      System.out.println("Update wait for write lock.");
      lock.writeLock().lock();
      lockManager.recordLock(this.toString().hashCode(), lock);
//      System.out.println("Update get write lock. Write lock hold: " + lock.getWriteHoldCount());
    } else {
//      System.out.println(
//          "Update has this write lock. Write lock hold: " + lock.getWriteHoldCount());
    }

    deleteWithoutLock(oldRow);
    insertWithoutLock(newRow);
//    try {
////      System.out.println("Update sleep 5 secs");
//      Thread.sleep(5000);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }

    /* release write lock when committed */
  }

  public void updateWithoutLock(Row oldRow, Row newRow) {
    deleteWithoutLock(oldRow);
    insertWithoutLock(newRow);
  }

  public void useReadLock(LockManager lockManager, String user) {
    if (lock.getReadHoldCount() == 0) {
//      System.out.println(this.hashCode() + " wait for read lock.");
      lock.readLock().lock();
      lockManager.recordLock(this.hashCode(), lock);
//      System.out.println(this.hashCode() + " " + user + " get read lock. Read lock hold: " + lock.getReadHoldCount());
    } else {
//      System.out.println(
//              this.hashCode() + " " + user + " has this read lock. Read lock hold: " + lock.getReadHoldCount());
//      lockManager.recordLock(this.hashCode(), lock);
    }
  }

  public void releaseReadLockIfReadCommitted(LockManager lockManager, String user) {
    if (!(lockManager.transactionStarted() && lockManager.isSerializable())) {
      if (lock.getReadHoldCount() > 0) {
        lock.readLock().unlock();
//        System.out.println(this.hashCode() + " " + user + " release read lock. Read lock hold: " + lock.getReadHoldCount());
      }
    } else {
//      System.out.println("In serializable mode.");
    }
  }

  public void useWriteLock(LockManager lockManager, String user) {
    if (!lock.isWriteLockedByCurrentThread()) {
//      System.out.println(user + " wait for write lock.");
      lock.writeLock().lock();
      lockManager.recordLock(this.hashCode(), lock);
      System.out.println(this.hashCode() + " " + user + " get write lock. Write lock hold: " + lock.getWriteHoldCount());
    } else {
      System.out.println(
              this.hashCode() + " " + user + " has this write lock. Write lock hold: " + lock.getWriteHoldCount());
//      lockManager.recordLock(this.hashCode(), lock);
    }
  }

  private void serialize() throws IOException {
    // TODO
    File file = new File(tablePath);
    if (!file.exists()) {
      file.createNewFile();
    }
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    Iterator<Row> iterator = iterator();
    while (iterator.hasNext()) {
      Row row = iterator.next();
      objectOutputStream.writeObject(row);
    }
    objectOutputStream.close();
    fileOutputStream.close();
  }

  private ArrayList<Row> deserialize() throws IOException {
    // TODO
    ArrayList<Row> rows = new ArrayList<>();
    FileInputStream fileInputStream = new FileInputStream(tablePath);
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    try {
      while (true) {
        Row row = (Row) objectInputStream.readObject();
        rows.add(row);
      }
    } catch (EOFException | ClassNotFoundException e) {
      objectInputStream.close();
      fileInputStream.close();
      return rows;
    }
  }

  public ArrayList<Column> getColumns() {
    return columns;
  }

  public ArrayList<String> getColumnNames() {
    ArrayList<String> names = new ArrayList<>();
    for (Column column : columns) {
      names.add(column.getName());
    }
    return names;
  }

  public Column getColumnByName(String name) {
    for (Column column : columns) {
      if (column.getName().equals(name)) {
        return column;
      }
    }
    return null;
  }

  public String getTableName() {
    return tableName;
  }

  public int getPrimaryIndex() {
    return primaryIndex;
  }

  private class TableIterator implements Iterator<Row> {
    private Iterator<Pair<Entry, Row>> iterator;

    TableIterator(Table table) {
      this.iterator = table.index.iterator();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Row next() {
      return iterator.next().right;
    }
  }

  @Override
  public Iterator<Row> iterator() {
    return new TableIterator(this);
  }

//  @Override
//  public String toString() {
//    String buffer = "\t" + tableName;
//    for (Column column : columns) {
//      buffer = buffer.concat("\n\t\t" + column.toString());
//    }
//    return buffer;
//  }
}
