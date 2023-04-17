package cn.edu.thssdb.exception;

public class DuplicateDatabaseException extends RuntimeException{
    private String databaseName;
    public DuplicateDatabaseException(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public String getMessage() { return "Database " + databaseName + " has been created already!"; }
}
