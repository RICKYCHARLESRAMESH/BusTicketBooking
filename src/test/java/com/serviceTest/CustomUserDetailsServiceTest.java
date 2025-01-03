package com.serviceTest;

import com.dao.UserDAO;
import com.model.Role;
import com.model.UserEntity;
import com.service.CustomUserDetailsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UserDAO userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private UserEntity mockUser;

    @BeforeEach
    public void setUp() {
        // Setting up a mock user with roles
        mockUser = new UserEntity();
        mockUser.setUsername("testUser");
        mockUser.setPassword("password");
        mockUser.setRoles(Collections.singletonList(new Role("ROLE_USER")));
    }

    @Test
    public void testLoadUserByUsername_Success() {
        // Mocking the repository to return a user when the username is "testUser"
        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(mockUser));

        // Calling the service method
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("testUser");

        // Verifying that the user is returned with the correct details
        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Mocking the repository to return empty for the given username
        Mockito.when(userRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());

        // Verifying that the service throws an exception when the user is not found
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("nonExistentUser");
        });
    }

    @Test
    public void testGetCount() {
        // Mocking the repository to return a specific count of users
        Mockito.when(userRepository.count()).thenReturn(5L);

        // Calling the service method
        Long count = customUserDetailsService.getCount();

        // Verifying that the count matches the mocked value
        assertEquals(5L, count);
    }
}
