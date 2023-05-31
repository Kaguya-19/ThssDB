package cn.edu.thssdb.exception;

public class TransactionOnProcessException extends RuntimeException{

    @Override
    public String getMessage() {
        return "[Error] Not allow to modify isolation level when transaction is on process!";
    }
}
