package cn.edu.thssdb;

import cn.edu.thssdb.exception.TransactionOnProcessException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockManager {

  public Long sessionId;

  public Map<Integer, ReentrantReadWriteLock> lockTable = new HashMap<>();
  private Boolean transactionOnProcess = false;
  private Boolean transactionMode = false; // true for serializable, false for read committed

  public LockManager(Long sessionId) {
    this.sessionId = sessionId;
  }

  public void recordLock(Integer recourseId, ReentrantReadWriteLock lock) {
    lockTable.put(recourseId, lock);
    System.out.println(
        recourseId + " Read: " + lock.getReadHoldCount() + " Write: " + lock.getWriteHoldCount());
  }

  public void beginTransaction() {
    transactionOnProcess = true;
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
    transactionOnProcess = false;
  }

  public boolean transactionStarted() {
    return transactionOnProcess;
  }

  public boolean isSerializable() {
    return transactionMode;
  }

  public String getMode() {
    return transactionMode ? "serializable" : "read committed";
  }
}
