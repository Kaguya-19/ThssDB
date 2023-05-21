package cn.edu.thssdb.exception;

public class DatabaseNotSelectedException extends RuntimeException {
  @Override
  public String getMessage() {
    return "[Error] no database selected!";
  }
}
