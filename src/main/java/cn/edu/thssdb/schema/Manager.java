package cn.edu.thssdb.schema;

import cn.edu.thssdb.Global;
import cn.edu.thssdb.exception.DatabaseNotExistException;
import cn.edu.thssdb.exception.DatabaseNotSelectedException;
import cn.edu.thssdb.exception.DuplicateDatabaseException;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Manager {
  private HashMap<String, Database> databases;
  private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public static Manager getInstance() {
    return Manager.ManagerHolder.INSTANCE;
  }

  private String loadedDatabase;

  public Manager() {
    // TODO
    databases = new HashMap<>();
    loadedDatabase = null;
  }

  public boolean createDatabaseIfNotExists(String databaseName) {
    if(!databases.containsKey(databaseName)) {
      Database database = new Database(databaseName);
      databases.put(databaseName, database);
      System.out.println("[DEBUG] " + "Database created");
      if (loadedDatabase == null) {
        switchDatabase(databaseName);
        return true;
      }
    } else {
      System.out.println("[DEBUG] " + "Database existed!");
      throw new DuplicateDatabaseException(databaseName);
    }
    return false;
  }

  public void deleteDatabase(String databaseName) {
    if(!databases.containsKey(databaseName)) {
      throw new DatabaseNotExistException(databaseName);
    }
    databases.remove(databaseName);
    if(loadedDatabase.equals(databaseName))
      loadedDatabase = null;
    System.out.println("[DEBUG] " + "Database dropped");
    // remove file of database
//    this.persist();
  }

  public void switchDatabase(String databaseName) {
    if(databases.containsKey(databaseName)) {
      loadedDatabase = databaseName;
      System.out.println("[DEBUG] " + "Database " + loadedDatabase + " is activated.");
    } else {
      throw new DatabaseNotExistException(databaseName);
    }
  }

  public Database getCurrentDatabase() {
    if(loadedDatabase == null)
      throw new DatabaseNotSelectedException();

    Database db = databases.get(loadedDatabase);
    if(db == null) throw new DatabaseNotExistException(loadedDatabase);
    return db;
  }

  public Database getDatabase(String databaseName) {
    // handle null database error
    if(databases.size() == 0)
      throw new DatabaseNotSelectedException();

    Database db = databases.get(databaseName);
    if(db == null) throw new DatabaseNotExistException(databaseName);
    return db;
  }

  private static class ManagerHolder {
    private static final Manager INSTANCE = new Manager();

    private ManagerHolder() {}
  }

  private void persist() {
    // overwrite every single time when modified, to be improved
    String filename = getManagerDataFile();
    try {
      FileOutputStream fos = new FileOutputStream(filename);
      OutputStreamWriter writer = new OutputStreamWriter(fos);
      for(String databaseName: databases.keySet()) {
        writer.write(databaseName+'\n');
      }
      writer.close();
      fos.close();
    } catch (Exception e) {
      // TODO: throw exception
    }
  }

  public static String getManagerDataFile() {
    return Global.DB_DIR + File.separator + "Manager";
  }
}
