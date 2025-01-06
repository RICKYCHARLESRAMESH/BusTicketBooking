package com.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
 
import com.exception.Response;
import com.model.Role;
import com.model.UserEntity;
import com.service.RoleService;
import com.service.UserService;
 
import java.util.ArrayList;
import java.util.List;
 
/**
* UserController is a REST controller responsible for managing user registrations.
* It supports registration for users, drivers, and admins.
*/
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {
 
    @Autowired
    private UserService userService;
 
    @Autowired
    private RoleService roleService;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    /**
     * Registers a new user.
     *
     * @param user The user entity containing registration details.
     * @return ResponseEntity containing the status and message of the registration.
     */
    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
 
        // Create and assign roles
        List<Role> assignedRoles = new ArrayList<>();
        Role userRole = new Role();
        userRole.setRole_name("ROLE_USER"); // Default role for new users
        assignedRoles.add(userRole);
        userRole.setUser(user);
 
        user.setRoles(assignedRoles);
 
        try {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("REGISTERSUCCESS", "User created successfully"));
        } catch (Exception e) {
            // Log the exception for better debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("REGISTERFAIL", "Error creating user"));
        }
    }
 
    /**
     * Registers a new driver.
     *
     * @param user The user entity containing registration details.
     * @return ResponseEntity containing the status and message of the registration.
     */
    @PostMapping("/driver/register")
    public ResponseEntity<?> registerDriver(@RequestBody UserEntity user) {
        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
 
        // Create and assign roles
        List<Role> assignedRoles = new ArrayList<>();
        Role driverRole = new Role();
        driverRole.setRole_name("ROLE_DRIVER"); // Role for Driver
        assignedRoles.add(driverRole);
        driverRole.setUser(user);
 
        user.setRoles(assignedRoles);
 
        try {
            // Save the user with the Driver role
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("REGISTERSUCCESS", "DRIVER created successfully"));
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("REGISTERFAIL", "Error creating DRIVER"));
        }
    }
 
    /**
     * Registers a new admin.
     *
     * @param user The user entity containing registration details.
     * @return ResponseEntity containing the status and message of the registration.
     */
    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@RequestBody UserEntity user) {
        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
 
        // Create and assign roles
        List<Role> assignedRoles = new ArrayList<>();
        Role adminRole = new Role();
        adminRole.setRole_name("ROLE_ADMIN"); // Role for Admin
        assignedRoles.add(adminRole);
        adminRole.setUser(user);
 
        user.setRoles(assignedRoles);
 
        try {
            // Save the user with the admin role
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("REGISTERSUCCESS", "Admin created successfully"));
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("REGISTERFAIL", "Error creating Admin"));
        }
    }
}