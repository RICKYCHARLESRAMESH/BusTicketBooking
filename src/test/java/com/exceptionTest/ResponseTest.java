package com.exceptionTest;

import com.exception.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {

    @Test
    public void testConstructorAndGetters() {
        // Create an instance of Response using the constructor
        Response response = new Response("ERR001", "Test error message");

        // Verify that the code and message are set correctly via the constructor
        assertEquals("ERR001", response.getCode());
        assertEquals("Test error message", response.getMessage());
    }

    @Test
    public void testSetters() {
        // Create an instance of Response without setting values
        Response response = new Response(null, null);

        // Set values using the setters
        response.setCode("ERR002");
        response.setMessage("Another test error message");

        // Verify that the values are set correctly using the setters
        assertEquals("ERR002", response.getCode());
        assertEquals("Another test error message", response.getMessage());
    }
}
