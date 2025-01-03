package com.serviceTest;

import com.dao.RoleDAO;
import com.model.Role;
import com.service.RoleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleDAO roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Role mockRole;

    @BeforeEach
    public void setUp() {
        // Setting up a mock Role object
        mockRole = new Role();
        mockRole.setRole_name("ROLE_USER");
    }

    @Test
    public void testFindByRoleName_Success() {
        // Mocking the repository to return the role when the role name is "ROLE_USER"
        Mockito.when(roleRepository.findByRoleName("ROLE_USER")).thenReturn(mockRole);

        // Calling the service method
        Role role = roleService.findByRoleName("ROLE_USER");

        // Verifying that the role is returned correctly
        assertNotNull(role);
        assertEquals("ROLE_USER", role.getRole_name());
    }

    @Test
    public void testFindByRoleName_RoleNotFound() {
        // Mocking the repository to return null when the role name is "ROLE_ADMIN"
        Mockito.when(roleRepository.findByRoleName("ROLE_ADMIN")).thenReturn(null);

        // Calling the service method
        Role role = roleService.findByRoleName("ROLE_ADMIN");

        // Verifying that the role is null since it was not found
        assertNull(role);
    }

    @Test
    public void testSaveRole() {
        // The save method is void, so we do not need to mock its return value
        // Simply verify that it is called when the service method is invoked
        roleService.saveRole(mockRole);

        // Verifying that the save method was called once
        Mockito.verify(roleRepository, Mockito.times(1)).save(mockRole);
    }

    @Test
    public void testExistsByRoleName_Exists() {
        // Mocking the repository to return the mockRole when the role name is "ROLE_USER"
        Mockito.when(roleRepository.findByRoleName("ROLE_USER")).thenReturn(mockRole);

        // Calling the service method
        boolean exists = roleService.existsByRoleName("ROLE_USER");

        // Verifying that the role exists
        assertTrue(exists);
    }

    @Test
    public void testExistsByRoleName_NotExists() {
        // Mocking the repository to return null when the role name is "ROLE_ADMIN"
        Mockito.when(roleRepository.findByRoleName("ROLE_ADMIN")).thenReturn(null);

        // Calling the service method
        boolean exists = roleService.existsByRoleName("ROLE_ADMIN");

        // Verifying that the role does not exist
        assertFalse(exists);
    }
}
