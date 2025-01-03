package com.DaoTest;
 
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import com.dao.UserDAO;
import com.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.util.Optional;
 
@ExtendWith(MockitoExtension.class)
public class UserDAOTest {
 
    @Mock
    private UserDAO userDAO;
 
    private UserEntity userEntity;
 
    @BeforeEach
    public void setUp() {
        // Create a sample UserEntity object
        userEntity = new UserEntity();
        userEntity.setUser_id(1L);
        userEntity.setUsername("testUser");
        userEntity.setPassword("password123"); // Assuming there is a password field
    }
 
    @Test
    public void testFindByUsername() {
        // Prepare mock behavior
        when(userDAO.findByUsername("testUser")).thenReturn(Optional.of(userEntity));
 
        // Test method
        Optional<UserEntity> foundUser = userDAO.findByUsername("testUser");
 
        // Verify results
        assertTrue(foundUser.isPresent());
        assertEquals(userEntity.getUser_id(), foundUser.get().getUser_id());
        assertEquals(userEntity.getUsername(), foundUser.get().getUsername());
 
        // Verify mock interaction
        verify(userDAO, times(1)).findByUsername("testUser");
    }
 
    @Test
    public void testFindByUsername_NotFound() {
        // Prepare mock behavior for a non-existent user
        when(userDAO.findByUsername("nonExistentUser")).thenReturn(Optional.empty());
 
        // Test method
        Optional<UserEntity> foundUser = userDAO.findByUsername("nonExistentUser");
 
        // Verify results
        assertFalse(foundUser.isPresent());
 
        // Verify mock interaction
        verify(userDAO, times(1)).findByUsername("nonExistentUser");
    }
}
