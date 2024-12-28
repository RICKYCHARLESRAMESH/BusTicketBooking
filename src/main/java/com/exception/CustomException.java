package com.exception;

public class CustomException extends RuntimeException {

    private String code; // Unique code for the exception type
    private String message; // Detailed message for the client

    // Constructors

    /**
     * Constructor with code and message.
     *
     * @param code    the error code
     * @param message the detailed error message
     */
    public CustomException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * Constructor with code, message, and cause.
     *
     * @param code    the error code
     * @param message the detailed error message
     * @param cause   the root cause of the exception
     */
    public CustomException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    // Getters

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    // Optional: Override toString for debugging
    @Override
    public String toString() {
        return "CustomException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
