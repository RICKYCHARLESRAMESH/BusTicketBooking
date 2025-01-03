package com.DaoTest;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import com.dao.RoleDAO;
import com.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
@ExtendWith(MockitoExtension.class)
public class RoleDAOTest {
 
    @Mock
    private RoleDAO roleDAO;
 
    private Role role;
 
    @BeforeEach
    public void setUp() {
        // Create a sample Role object
        role = new Role();
        role.setRole_id(1L);
        role.setRole_name("ADMIN");
    }
 
    @Test
    public void testFindByRoleName() {
        // Prepare mock behavior
        when(roleDAO.findByRoleName("ADMIN")).thenReturn(role);
 
        // Test method
        Role foundRole = roleDAO.findByRoleName("ADMIN");
 
        // Verify results
        assertNotNull(foundRole);
        assertEquals(role.getRole_id(), foundRole.getRole_id());
        assertEquals(role.getRole_name(), foundRole.getRole_name());
 
        // Verify mock interaction
        verify(roleDAO, times(1)).findByRoleName("ADMIN");
    }
 
    @Test
    public void testFindByRoleName_NotFound() {
        // Prepare mock behavior for a non-existent role
        when(roleDAO.findByRoleName("NON_EXISTENT_ROLE")).thenReturn(null);
 
        // Test method
        Role foundRole = roleDAO.findByRoleName("NON_EXISTENT_ROLE");
 
        // Verify results
        assertNull(foundRole);
 
        // Verify mock interaction
        verify(roleDAO, times(1)).findByRoleName("NON_EXISTENT_ROLE");
    }
}
