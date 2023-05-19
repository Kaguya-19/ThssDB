package cn.edu.thssdb.exception;

public class DatabaseNotExistException extends RuntimeException{
    private String databaseName;
    public DatabaseNotExistException(String databaseName) {
        this.databaseName = databaseName;
    }
    @Override
    public String getMessage() { return "[Error] database " + databaseName + " is not existed!"; }
}
