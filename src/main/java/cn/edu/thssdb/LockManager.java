package cn.edu.thssdb;

import cn.edu.thssdb.exception.TransactionOnProcessException;
import cn.edu.thssdb.schema.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockManager {

  public Long sessionId;

  public ConcurrentHashMap<Integer, ReentrantReadWriteLock> lockTable = new ConcurrentHashMap<>();
  private ArrayList<Table> tableList;

  // make this safe for concurrent access
  private AtomicBoolean transactionOnProcess = new AtomicBoolean(false);
  private Boolean transactionMode = false; // true for serializable, false for read committed

  public LockManager(Long sessionId) {
    this.sessionId = sessionId;
    this.tableList = new ArrayList<>();
  }

  public void recordLock(Integer recourseId, ReentrantReadWriteLock lock) {
    lockTable.put(recourseId, lock);
    //    System.out.println(
    //        recourseId + " Read: " + lock.getReadHoldCount() + " Write: " +
    // lock.getWriteHoldCount());
  }

  public void recordTable(Table table) {
    if (!tableList.contains(table)) tableList.add(table);
  }

  public void beginTransaction() {
    transactionOnProcess.set(true);
  }

  public void setTransactionMode(boolean mode) {
    if (transactionStarted()) {
      throw new TransactionOnProcessException();
    }
    transactionMode = mode;
    //    String text = "read committed";
    //    if (mode) {
    //      text = "Serializable";
    //    }
    //    System.out.println("Transaction mode now is set to: " + text);

  }

  // can't release read lock
  public void commit() {
    //    System.out.println("Transaction begin called: " + transactionState);
    for (Table table : tableList) {
      table.persist();
    }
    for (Integer key : lockTable.keySet()) {
      //      System.out.println(key);
      ReentrantReadWriteLock lock = lockTable.get(key);
      if (lock.getReadHoldCount() > 0) {
        lock.readLock().unlock();
        //        System.out.println("Commit release read lock. Read lock hold: " +
        // lock.getReadHoldCount());
      }
      if (lock.getWriteHoldCount() > 0) {
        lock.writeLock().unlock();
        //        System.out.println("Commit release write lock. Write lock hold: " +
        // lock.getWriteHoldCount());
      }
      //      System.out.println(key + " Read: " + lock.getReadHoldCount() + " Write: " +
      // lock.getWriteHoldCount());
    }
    lockTable.clear();
    tableList.clear();
    transactionOnProcess.set(false);
  }

  public boolean transactionStarted() {
    return transactionOnProcess.get();
  }

  public boolean isSerializable() {
    return transactionMode;
  }

  public String getMode() {
    return transactionMode ? "serializable" : "read committed";
  }
}
