package cn.edu.thssdb.exception;

public class getLockFailedException extends RuntimeException{
    @Override
    public String getMessage() {
        return "[Error] Unknown get lock exception!";
    }
}
