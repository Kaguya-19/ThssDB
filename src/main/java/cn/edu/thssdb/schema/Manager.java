package cn.edu.thssdb.schema;

import cn.edu.thssdb.Global;
import cn.edu.thssdb.exception.DatabaseNotExistException;
import cn.edu.thssdb.exception.DatabaseNotSelectedException;
import cn.edu.thssdb.exception.DuplicateDatabaseException;
import cn.edu.thssdb.plan.LogicalGenerator;
import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.plan.impl.CreateDatabasePlan;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Manager {
  // TODO: MetaData Should change in one database one file
  private HashMap<String, Database> databases;
  private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public static Manager getInstance() {
    return Manager.ManagerHolder.INSTANCE;
  }

  private String loadedDatabase;

  public Manager() {
    // TODO:Stroage by page
    databases = new HashMap<>();
    loadedDatabase = null;
    System.out.println("[DEBUG] " + "Recovering meta data");
    recover();
    System.out.println("[DEBUG] " + "Meta data recovered");
    for (Database db : databases.values()) {
      System.out.println(db.toString());
    }
  }

  public boolean createDatabaseIfNotExists(String databaseName) {
    if (!databases.containsKey(databaseName)) {
      Database database = new Database(databaseName);
      databases.put(databaseName, database);
      System.out.println("[DEBUG] " + "Database created");

      //      persist(null);

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
    if (!databases.containsKey(databaseName)) {
      throw new DatabaseNotExistException(databaseName);
    }
    databases.remove(databaseName);
    if (loadedDatabase.equals(databaseName)) loadedDatabase = null;
    System.out.println("[DEBUG] " + "Database dropped");
    //    persist(databaseName);
  }

  public void switchDatabase(String databaseName) {
    if (databases.containsKey(databaseName)) {
      loadedDatabase = databaseName;
      System.out.println("[DEBUG] " + "Database " + loadedDatabase + " is activated.");
    } else {
      throw new DatabaseNotExistException(databaseName);
    }
  }

  public Database getCurrentDatabase() {
    if (loadedDatabase == null) throw new DatabaseNotSelectedException();

    Database db = databases.get(loadedDatabase);
    if (db == null) throw new DatabaseNotExistException(loadedDatabase);
    return db;
  }

  public Database getDatabase(String databaseName) {
    // handle null database error
    if (databases.size() == 0) throw new DatabaseNotSelectedException();

    Database db = databases.get(databaseName);
    if (db == null) throw new DatabaseNotExistException(databaseName);
    return db;
  }

  //  TODO: should be called in savepoint

  private void persist(String delDB) {
    if (delDB != null && !delDB.isEmpty()) {
      removeDatabaseDir(delDB);
    } else {
      //      System.out.println("No db is dropped.");
    }
    // update manager.script
    String scriptFilePath = Global.MANAGER_DIR.concat("manager.script");
    try {
      File scriptFile = new File(scriptFilePath);
      if (scriptFile.exists()) {
        scriptFile.delete();
      }
      scriptFile.createNewFile();

      FileOutputStream fos = new FileOutputStream(scriptFilePath);
      OutputStreamWriter writer = new OutputStreamWriter(fos);
      // only add current db
      for (String db : databases.keySet()) {
        writer.write("create database ".concat(db + "\n"));
        // create a folder for database
        String dirPath = Global.MANAGER_DIR.concat(db);
        File dir = new File(dirPath);
        if (dir.exists()) {
          //          System.out.println("Database folder created.");
        } else {
          dir.mkdirs();
        }
        // create empty .script file
        String dbScriptFile = Global.MANAGER_DIR.concat(db + File.separator + db + ".script");
        File dbScript = new File(dbScriptFile);
        if (!dbScript.exists()) dbScript.createNewFile();
      }
      writer.close();
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void removeDatabaseDir(String databaseName) {
    String folderPath = Global.MANAGER_DIR.concat(databaseName);
    try {
      Path directory = Paths.get(folderPath);
      Files.walk(directory)
          .sorted(java.util.Comparator.reverseOrder())
          .map(Path::toFile)
          .forEach(File::delete);
      System.out.println("Folder and its contents deleted successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean recover() {
    //    restore meta data from persist data into memory
    String scriptFilePath = Global.MANAGER_DIR.concat("manager.script");
    try {
      File scriptFile = new File(scriptFilePath);
      if (scriptFile.exists()) {
        FileInputStream fis = new FileInputStream(scriptFilePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = reader.readLine()) != null) {
          LogicalPlan plan = (LogicalPlan) LogicalGenerator.generate(line);
          String dbName = ((CreateDatabasePlan) plan).getDatabaseName();
          createDatabaseIfNotExists(dbName);
          if (!databases.get(dbName).recover()) {
            // break down process and try to recover
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public void saveState() {
    persist(null);
    for (Database db : databases.values()) {
      db.saveState();
    }
  }

  private static class ManagerHolder {
    private static final Manager INSTANCE = new Manager();

    private ManagerHolder() {}
  }
}
