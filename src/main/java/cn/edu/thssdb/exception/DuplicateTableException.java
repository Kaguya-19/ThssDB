package cn.edu.thssdb.exception;

public class DuplicateTableException extends RuntimeException {
  private String tableName;

  public DuplicateTableException(String tableName) {
    this.tableName = tableName;
  }

  @Override
  public String getMessage() {
    return "Table " + tableName + " has been created already!";
  }
}
