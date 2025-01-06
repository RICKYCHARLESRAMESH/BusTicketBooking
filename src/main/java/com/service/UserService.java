package com.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.dao.UserDAO;
import com.model.UserEntity;
 
/**
* UserService provides methods to manage User entities,
* including saving, retrieving, deleting, and checking for the existence of users.
*/
@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
 
    /**
     * Saves a new user entity to the database.
     *
     * @param user The UserEntity object to save.
     */
	public void saveUser(UserEntity user) {
		userDAO.save(user);
	}
 
    /**
     * Checks if a user exists by ID.
     *
     * @param id The ID of the user to check.
     * @return true if the user exists, false otherwise.
     */
	public boolean findById(long id) {
		return userDAO.findById(id).isPresent();
	}
    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete.
     */
	public void deleteUser(long id) {
		userDAO.deleteById(id);
	}
 
    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The UserEntity object if found.
     * @throws NoSuchElementException if the user is not found.
     */
    public UserEntity getUserById(long id) {
    	return userDAO.findById(id).get();
    }
}