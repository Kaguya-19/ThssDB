package cn.edu.thssdb;

import java.io.File;

public class Global {
  public static String DB_DIR = "thssdb";
  public static String MANAGER_DIR = DB_DIR + File.separator + "data" + File.separator;
  public static String TABLE_DIR = DB_DIR + File.separator + "table" + File.separator;
  public static String TRANSACTION_MODE = "read committed";
}
