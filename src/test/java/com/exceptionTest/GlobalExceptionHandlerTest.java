package com.exceptionTest;

import com.exception.CustomException;
import com.exception.GlobalExceptionHandler;
import com.exception.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleCustomException() {
        // Create a mock CustomException with a code and message
        CustomException customException = new CustomException("ERR001", "Test error message");

        // Call the handleCustomException method
        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleCustomException(customException);

        // Verify that the status is BAD_REQUEST (400)
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        // Verify that the response body is a Response with the correct code and message
        Response response = (Response) responseEntity.getBody();
        assertEquals("ERR001", response.getCode());
        assertEquals("Test error message", response.getMessage());
    }

    @Test
    public void testHandleGenericException() {
        // Create a mock generic exception
        Exception exception = new Exception("Generic exception");

        // Call the handleGenericException method
        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleGenericException(exception);

        // Verify that the status is INTERNAL_SERVER_ERROR (500)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

        // Verify that the response body is a Response with code "SERVER_ERROR" and the generic message
        Response response = (Response) responseEntity.getBody();
        assertEquals("SERVER_ERROR", response.getCode());
        assertEquals("An unexpected error occurred", response.getMessage());
    }
}
