package cn.y.java.exceptions.custom_exception;

/**
 * 错误年龄异常
 */
public class WrongAgeException extends RuntimeException{

    @java.io.Serial
    private static final long serialVersionUID = -7034897290745766939L;

    public WrongAgeException(String message) {
        super(message);
    }

    public WrongAgeException(String message, Throwable cause) {
        super(message, cause);
    }
}
