package cn.edu.thssdb;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Logger {
  private String logPath;
  FileWriter writer;

  public Logger(String dirPath, String dbName) {
    this.logPath = dirPath + dbName + ".log";
    try {
      File logDir = new File(dirPath);
      if (!logDir.exists()) logDir.mkdirs();

      File logFile = new File(logPath);
      if (!logFile.exists()) logFile.createNewFile();
      writer = new FileWriter(logFile, true);
      // System.out.println("init log");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void writeLog(String log) {
    try {
      writer.write(log + '\n');
      writer.flush();
      // System.out.println("write log " + log);
    } catch (IOException e) {
      System.out.println("write log error");
      e.printStackTrace();
    }
  }

  public ArrayList<String> readLog() {
    String line = "";
    ArrayList<String> logString = new ArrayList<>();
    try {
      InputStreamReader reader = new InputStreamReader(Files.newInputStream(Paths.get(logPath)));
      BufferedReader bufferedReader = new BufferedReader(reader);
      while ((line = bufferedReader.readLine()) != null) {
        logString.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return logString;
  }

  public void cleanLog() {
    try {
      writer.close();
      writer = new FileWriter(logPath);
      writer.write("");
      writer.close();
      writer = new FileWriter(logPath, true);
      // clear the file
      // System.out.println("clean log");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void closeLog() {
    try {
      writer.close();
      writer = null;
    } catch (IOException e) {

    }
  }
}
