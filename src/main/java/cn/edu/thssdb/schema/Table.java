package cn.edu.thssdb.schema;

import cn.edu.thssdb.Global;
import cn.edu.thssdb.LockManager;
import cn.edu.thssdb.exception.KeyNotExistException;
import cn.edu.thssdb.index.BPlusTree;
import cn.edu.thssdb.index.BPlusTreeLeafNode;
import cn.edu.thssdb.index.PageCounter;
import cn.edu.thssdb.index.PageTreeIterator;
import cn.edu.thssdb.utils.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Table implements Iterable<Row> {
  public ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private String databaseName;
  public String tableName;
  public ArrayList<Column> columns;
  public BPlusTree<Entry, Row> index;
  private int primaryIndex = 0;

  public String tablePath;

  public Table(String databaseName, String tableName, Column[] columns, int primaryIndex) {
    this.databaseName = databaseName;
    this.tableName = tableName;
    this.columns = new ArrayList<>(Arrays.asList(columns));
    this.tablePath = Global.TABLE_DIR + databaseName + File.separator;
    this.primaryIndex = primaryIndex;
    this.index = new BPlusTree<>(this);

    // TODO

  }

  public Row getRowByPrimaryIndex(Entry key) {
    try {
      BPlusTreeLeafNode<Entry, Row> leafNode = index.getLeafNode(key);
      while (leafNode != null) {
        Integer pageID = leafNode.getPageId();
        ArrayList<Row> page = index.bufferManager.readPage(pageID);
        boolean found = false;
        for (Row row : page) {
          if (row.getEntries().get(primaryIndex).equals(key)) {
            found = true;
            return row;
          }
        }
        //        if (!found) break;
        leafNode = leafNode.getNext();
      }
      return null;
      //      System.out.println("[GET RESULT] " + rows.size());
    } catch (KeyNotExistException e) {
      //      e.printStackTrace();
      return null;
    }
  }

  public void persist(Long sessionId) {
    // TODO
    serialize(sessionId);
  }

  public void recover() {
    // TODO
    File dir = new File(tablePath);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    File tableFile = new File(tablePath.concat("_index"));
    if (!tableFile.exists()) {
      return;
    }
    try {
      FileInputStream fileInputStream = new FileInputStream(tableFile);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

      Object inputObject = objectInputStream.readObject();
      PageCounter res = (PageCounter) inputObject;
      objectInputStream.close();
      fileInputStream.close();
      for (Integer index : res.indexList) {
        ArrayList<Row> page = this.index.bufferManager.readPage(index);
        for (Row row : page) {
          Entry primary = row.getEntries().get(primaryIndex);
          this.index.put(primary, row);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void insert(LockManager lockManager, Row row) {
    if (lockManager != null) {
      if (!lock.isWriteLockedByCurrentThread()) {
        //      System.out.println(this.hashCode() + " wait for write lock.");
        //      System.out.println(this.hashCode() + " Read: " + lock.getReadHoldCount() + " Write:
        // "
        // + lock.getWriteHoldCount());
        if (lock.getReadHoldCount() > 0) {
          lock.readLock().unlock();
        }
        lock.writeLock().lock();
        lockManager.recordLock(this.hashCode(), lock);
        lockManager.recordTable(this);
        //      System.out.println(this.hashCode() + " Insert get write lock. Write lock hold: " +
        // lock.getWriteHoldCount());
      } else {
        //      System.out.println(
        //          "Insert has this write lock. Write lock hold: " + lock.getWriteHoldCount());
        //      lockManager.recordLock(this.hashCode(), lock);
      }
    }

    insertWithoutLock(row);
    //    try {
    //      System.out.println("Insert sleep 5 secs");
    //      Thread.sleep(5000);
    //    } catch (Exception e) {
    //      e.printStackTrace();
    //    }

    /* release write lock when committed */
    //    persist();
  }

  public void insertWithoutLock(Row row) {
    // check primary key
    //    if (index.get(row.getEntries().get(primaryIndex)) != null) {
    //      throw new RuntimeException("Primary Key Error");
    //    }
    index.put(row.getEntries().get(primaryIndex), row);
  }

  public void delete(LockManager lockManager, Row row) {
    if (lockManager != null) {
      if (!lock.isWriteLockedByCurrentThread()) {
        //      System.out.println("Delete wait for write lock.");
        lock.writeLock().lock();
        lockManager.recordLock(this.hashCode(), lock);
        lockManager.recordTable(this);
        //      System.out.println(this.hashCode() + " Delete get write lock. Write lock hold: " +
        // lock.getWriteHoldCount());
      } else {
        //      System.out.println(
        //          "Delete has this write lock. Write lock hold: " + lock.getWriteHoldCount());
        //      lockManager.recordLock(this.hashCode(), lock);
      }
    }

    deleteWithoutLock(row);
    //    try {
    //      System.out.println("Delete sleep 5 secs");
    //      Thread.sleep(5000);
    //    } catch (Exception e) {
    //      e.printStackTrace();
    //    }

    /* release write lock when committed */
    //    persist();
  }

  public void deleteWithoutLock(Row row) {
    index.remove(row.getEntries().get(primaryIndex));
  }

  public void update(LockManager lockManager, Row oldRow, Row newRow) {
    if (lockManager != null) {
      if (!lock.isWriteLockedByCurrentThread()) {
        //      System.out.println("Update wait for write lock.");
        lock.writeLock().lock();
        lockManager.recordLock(this.toString().hashCode(), lock);
        lockManager.recordTable(this);
        //      System.out.println("Update get write lock. Write lock hold: " +
        // lock.getWriteHoldCount());
      } else {
        //      System.out.println(
        //          "Update has this write lock. Write lock hold: " + lock.getWriteHoldCount());
      }
    }

    updateWithoutLock(oldRow, newRow);
    //    try {
    ////      System.out.println("Update sleep 5 secs");
    //      Thread.sleep(5000);
    //    } catch (Exception e) {
    //      e.printStackTrace();
    //    }

    /* release write lock when committed */
    //    persist();
  }

  public void updateWithoutLock(Row oldRow, Row newRow) {
    index.update(oldRow.getEntries().get(primaryIndex), newRow);
  }

  public void useReadLock(LockManager lockManager, String user) {
    if (!lock.readLock().tryLock()) {
      lock.readLock().lock();
      lockManager.recordLock(this.hashCode(), lock);
    }
    //    if (lock.getReadHoldCount() == 0) {
    //      //      System.out.println(this.hashCode() + " wait for read lock.");
    //      lock.readLock().lock();
    //      lockManager.recordLock(this.hashCode(), lock);
    //      //      System.out.println(this.hashCode() + " " + user + " get read lock. Read lock
    // hold: " +
    //      // lock.getReadHoldCount());
    //    } else {
    //      //      System.out.println(
    //      //              this.hashCode() + " " + user + " has this read lock. Read lock hold: " +
    //      // lock.getReadHoldCount());
    //      //      lockManager.recordLock(this.hashCode(), lock);
    //    }
  }

  public void releaseReadLockIfReadCommitted(LockManager lockManager, String user) {
    if (!(lockManager.transactionStarted() && lockManager.isSerializable())) {
      if (lock.getReadHoldCount() > 0) {
        lock.readLock().unlock();
        //        System.out.println(this.hashCode() + " " + user + " release read lock. Read lock
        // hold: " + lock.getReadHoldCount());
      }
    } else {
      //      System.out.println("In serializable mode.");
    }
  }

  private void serialize(Long sessionId) {
    // TODO
    if (!this.lock.isWriteLockedByCurrentThread()) {
      return;
    }
    try {
      index.bufferManager.writeAllDirty();
      File directory = new File(tablePath);
      if (!directory.exists()) {
        directory.mkdirs();
      }
      File tableFile = new File(tablePath.concat("_index"));
      if (!tableFile.exists()) {
        tableFile.createNewFile();
      }
      if (index.pageCounter.updated) {
        index.pageCounter.updated = false;
        FileOutputStream fileOutputStream = new FileOutputStream(tableFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
        objectOutputStream.writeObject(index.pageCounter);
        objectOutputStream.close();
        bufferedOutputStream.close();
        fileOutputStream.close();
      }
    } catch (Exception e) {
      //      System.out.println(e.getMessage());
      e.printStackTrace();
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
      this.iterator = new PageTreeIterator(index, table);
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

  public String getTableStructure() {
    String buffer = "\t" + tableName;
    for (Column column : columns) {
      buffer = buffer.concat("\n\t\t" + column.toString());
    }
    return buffer;
  }
}
