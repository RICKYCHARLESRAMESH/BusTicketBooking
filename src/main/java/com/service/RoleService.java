package com.service;
 
import com.dao.RoleDAO;
import com.model.Role;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
/**
* RoleService provides methods to manage Role entities,
* including finding, saving, and checking for role existence.
*/
@Service
public class RoleService {
 
    @Autowired
    private RoleDAO roleRepository;
 
    /**
     * Finds a role by its name.
     *
     * @param roleName The name of the role to find.
     * @return The Role object if found, or null if not found.
     */
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
 
    /**
     * Saves a new role to the database.
     *
     * @param role The Role object to be saved.
     */
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
 
    /**
     * Checks if a role with the given name already exists.
     *
     * @param roleName The name of the role to check.
     * @return True if the role exists, otherwise false.
     */
    public boolean existsByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName) != null;
    }
}
