package com.filterTest;
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.Test;
import com.filter.JwtResponse;
class JwtResponseTest {
    @Test
    void testJwtResponseConstructor() {
        String expectedToken = "mockToken";
        // Create a JwtResponse instance with the expected token
        JwtResponse jwtResponse = new JwtResponse(expectedToken);
        // Assert that the token is correctly set through the constructor
        assertNotNull(jwtResponse);
        assertEquals(expectedToken, jwtResponse.getToken());
    }
    @Test
    void testGetToken() {
        String expectedToken = "mockToken";
        // Create JwtResponse with the token
        JwtResponse jwtResponse = new JwtResponse(expectedToken);
        // Assert that the getToken method returns the expected token
        assertEquals(expectedToken, jwtResponse.getToken());
    }
}