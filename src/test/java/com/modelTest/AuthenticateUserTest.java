package com.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.model.AuthenticateUser;

public class AuthenticateUserTest {

    @Test
    void testDefaultConstructor() {
        AuthenticateUser user = new AuthenticateUser();
        assertNotNull(user, "The AuthenticateUser object should be created successfully.");
    }

    @Test
    void testGettersAndSetters() {
        AuthenticateUser user = new AuthenticateUser();

        user.setUsername("testUser");
        user.setPassword("testPassword123");
        user.setRole("Admin");

        assertEquals("testUser", user.getUsername(), "The username should be set and retrieved correctly.");
        assertEquals("testPassword123", user.getPassword(), "The password should be set and retrieved correctly.");
        assertEquals("Admin", user.getRole(), "The role should be set and retrieved correctly.");
    }

    @Test
    void testEmptyValues() {
        AuthenticateUser user = new AuthenticateUser();

        user.setUsername("");
        user.setPassword("");
        user.setRole("");

        assertEquals("", user.getUsername(), "The username should allow empty values.");
        assertEquals("", user.getPassword(), "The password should allow empty values.");
        assertEquals("", user.getRole(), "The role should allow empty values.");
    }

    @Test
    void testNullValues() {
        AuthenticateUser user = new AuthenticateUser();

        user.setUsername(null);
        user.setPassword(null);
        user.setRole(null);

        assertNull(user.getUsername(), "The username should allow null values.");
        assertNull(user.getPassword(), "The password should allow null values.");
        assertNull(user.getRole(), "The role should allow null values.");
    }
}
