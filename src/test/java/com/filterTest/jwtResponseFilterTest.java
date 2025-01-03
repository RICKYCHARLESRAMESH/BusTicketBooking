package com.filterTest;
import org.junit.jupiter.api.Test;
 
import com.filter.JwtResponse;
 
import static org.junit.jupiter.api.Assertions.*;
 
public class jwtResponseFilterTest {
 
    @Test
    public void testJwtResponseConstructorAndGetter() {
        // Arrange
        String expectedToken = "sample.jwt.token";
 
        // Act
        JwtResponse jwtResponse = new JwtResponse(expectedToken);
        String actualToken = jwtResponse.getToken();
 
        // Assert
        assertNotNull(jwtResponse); // Ensure the object is created
        assertEquals(expectedToken, actualToken); // Check if the token is set correctly
    }
}