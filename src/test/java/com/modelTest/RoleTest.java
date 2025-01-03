package com.modelTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Role;
import com.model.UserEntity;

public class RoleTest {

    private Role role;
    private UserEntity userEntity;

    @BeforeEach
    public void setup() {
        userEntity = new UserEntity(); // Assuming UserEntity class has a default constructor
        role = new Role("Admin");
        role.setUser(userEntity); // Setting a UserEntity to the role
    }

    @Test
    public void testGetRoleId() {
        role.setRole_id(1);
        assertEquals(1, role.getRole_id());
    }

    @Test
    public void testSetRoleId() {
        role.setRole_id(2);
        assertEquals(2, role.getRole_id());
    }

    @Test
    public void testGetRoleName() {
        assertEquals("Admin", role.getRole_name());
    }

    @Test
    public void testSetRoleName() {
        role.setRole_name("User");
        assertEquals("User", role.getRole_name());
    }

    @Test
    public void testGetUser() {
        assertEquals(userEntity, role.getUser());
    }

    @Test
    public void testSetUser() {
        UserEntity newUserEntity = new UserEntity();
        role.setUser(newUserEntity);
        assertEquals(newUserEntity, role.getUser());
    }
}
