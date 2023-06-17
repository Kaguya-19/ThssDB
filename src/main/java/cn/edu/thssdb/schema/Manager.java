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
      System.out.println(db.getDatabaseStructure());
    }
  }

  public boolean createDatabaseIfNotExists(String databaseName, boolean record) {
    if (!databases.containsKey(databaseName)) {
      Database database = new Database(databaseName);
      databases.put(databaseName, database);
      System.out.println("[DEBUG] " + "Database created");
      if (record) persist(databaseName);

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
    dropDatabaseDir(databaseName);
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

  public String getAllDatabase() {
    String dbNameList = "";
    System.out.println("[DEBUG] " + "db count: " + databases.values().size());
    int i = 0;
    int n = databases.values().size() - 2;
    for (Database db : databases.values()) {
      dbNameList = dbNameList.concat(db.getDatabaseName());
      if (i < n) dbNameList = dbNameList + ", ";
    }
    if (dbNameList.length() == 0) {
      return "No databases.";
    }
    return "Databases: " + dbNameList;
  }

  // Called when modified
  private void persist(String databaseName) {
    // update manager.script
    String managerScriptPath = Global.MANAGER_DIR.concat("manager.script");
    try {
      File scriptFile = new File(managerScriptPath);
      if (!scriptFile.exists()) {
        scriptFile.createNewFile();
      }
      // FileOutputStream fos = new FileOutputStream(managerScriptPath);
      BufferedWriter writer = new BufferedWriter(new FileWriter(managerScriptPath, true));
      writer.write("CREATE DATABASE ".concat(databaseName + "\n"));

      //      String dirPath = Global.MANAGER_DIR.concat(databaseName);
      //      File dir = new File(dirPath);
      //      if (!dir.exists()) dir.mkdirs();

      String dbScriptPath =
          Global.MANAGER_DIR.concat(databaseName + File.separator + databaseName + ".script");
      File dbScript = new File(dbScriptPath);
      if (!dbScript.getParentFile().exists()) dbScript.getParentFile().mkdirs();
      if (!dbScript.exists()) dbScript.createNewFile();

      // only add current db
      //      for (String db : databases.keySet()) {
      //        writer.write("create database ".concat(db + "\n"));
      //        // create a folder for database
      //        String dirPath = Global.MANAGER_DIR.concat(db);
      //        File dir = new File(dirPath);
      //        if (dir.exists()) {
      //          //          System.out.println("Database folder created.");
      //        } else {
      //          dir.mkdirs();
      //        }
      //        // create empty .script file
      //        String dbScriptPath = Global.MANAGER_DIR.concat(db + File.separator + db +
      // ".script");
      //        File dbScript = new File(dbScriptPath);
      //        if (!dbScript.exists()) dbScript.createNewFile();
      //      }
      // writer.flush();
      writer.close();
      // fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void dropDatabaseDir(String databaseName) {
    String folderPath = Global.MANAGER_DIR.concat(databaseName);
    String tablePath = Global.TABLE_DIR.concat(databaseName);
    try {
      Path directory = Paths.get(folderPath);

      // delete directory recursively
      Files.walk(directory)
          .sorted(java.util.Comparator.reverseOrder())
          .map(Path::toFile)
          .forEach(File::delete);
      Path tableDir = Paths.get(tablePath);
      if (Files.exists((tableDir))) {
        Files.walk(tableDir)
            .sorted(java.util.Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
        //      Files.deleteIfExists(directory);
        Files.deleteIfExists(tableDir);
      }

      String managerScriptPath = Global.MANAGER_DIR.concat("manager.script");
      String pattern = "CREATE DATABASE ".concat(databaseName);
      File file = new File(managerScriptPath);
      File tempFile = new File(Global.MANAGER_DIR.concat("manager.temp"));
      if (tempFile.exists()) tempFile.delete();
      tempFile.createNewFile();

      BufferedReader reader = new BufferedReader(new FileReader(file));
      BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
      String currentLine;
      while ((currentLine = reader.readLine()) != null) {
        if (!currentLine.startsWith(pattern)) {
          writer.write(currentLine);
          writer.newLine();
        }
      }
      reader.close();
      writer.close();

      if (file.delete()) {
        if (!tempFile.renameTo(file)) {
          System.out.println("Failed to rename the temp file.");
        }
      } else {
        System.out.println("Failed to delete the original file.");
      }

      System.out.println("Folder and its contents deleted successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean recover() {
    //    restore meta data from persist data into memory
    String scriptFilePath = Global.MANAGER_DIR.concat("manager.script");
    try {
      File scriptFile = new File(scriptFilePath);
      File parentDir = scriptFile.getParentFile();
      if (!parentDir.exists()) {
        parentDir.mkdirs();
      }
      if (scriptFile.exists()) {
        FileInputStream fis = new FileInputStream(scriptFilePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = reader.readLine()) != null) {
          LogicalPlan plan = (LogicalPlan) LogicalGenerator.generate(line);
          String dbName = ((CreateDatabasePlan) plan).getDatabaseName();
          createDatabaseIfNotExists(dbName, false);
          if (!databases.get(dbName).recover()) {
            // break down process and try to recover
          }
        }
        reader.close();

        for (Database db : databases.values()) {
          db.recover();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  private static class ManagerHolder {
    private static final Manager INSTANCE = new Manager();

    private ManagerHolder() {}
  }
}
