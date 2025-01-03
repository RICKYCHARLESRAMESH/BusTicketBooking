package com.exceptionTest;

import com.exception.CustomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomExceptionTest {

    @Test
    public void testCustomExceptionWithCodeAndMessage() {
        String code = "ERR001";
        String message = "This is a custom exception message";

        CustomException exception = new CustomException(code, message);

        // Assert that the exception message is as expected
        assertEquals(message, exception.getMessage());
        // Assert that the exception code is as expected
        assertEquals(code, exception.getCode());
    }

    @Test
    public void testCustomExceptionWithCodeMessageAndCause() {
        String code = "ERR002";
        String message = "This is another custom exception with a cause";
        Throwable cause = new RuntimeException("Root cause");

        CustomException exception = new CustomException(code, message, cause);

        // Assert that the exception message is as expected
        assertEquals(message, exception.getMessage());
        // Assert that the exception code is as expected
        assertEquals(code, exception.getCode());
        // Assert that the cause is correctly set
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testCustomExceptionToString() {
        String code = "ERR003";
        String message = "Custom exception for toString test";
        CustomException exception = new CustomException(code, message);

        // Assert the toString method output
        String expectedToString = "CustomException{code='ERR003', message='Custom exception for toString test'}";
        assertEquals(expectedToString, exception.toString());
    }
}
