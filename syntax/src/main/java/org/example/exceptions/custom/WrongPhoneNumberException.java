package org.example.exceptions.custom;

/**
 * 错误手机号异常
 */
public class WrongPhoneNumberException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -7034997290745766939L;

    public WrongPhoneNumberException(String message) {
        super(message);
    }

    public WrongPhoneNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
