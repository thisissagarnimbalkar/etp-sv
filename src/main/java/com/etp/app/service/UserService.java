package com.etp.app.service;

import java.util.Optional;

import com.etp.app.entity.User;

public interface UserService {
	
	// Method to create a new user
    User createUser(User user);

    // Method to retrieve a user by their ID
    Optional<User> getUserById(Integer id);

    // Method to retrieve a user by their email
    User getUserByEmail(String email);

    // Method to delete a user by their ID
    void deleteUser(Integer id);
	
}
