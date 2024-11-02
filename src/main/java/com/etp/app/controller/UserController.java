package com.etp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.etp.app.entity.User;
import com.etp.app.service.UserService;
import com.etp.app.util.JwtUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/users") // Base URL for all user-related APIs
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtils jwtUtils;

    // Save a new user
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        String token = jwtUtils.generateJwtToken(savedUser.getPhoneNumber());
        
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Edit (update) a user
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") Integer id, @RequestBody User updatedUser) {
        Optional<User> existingUserOptional = userService.getUserById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            
            // Update fields
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setPassword(updatedUser.getPassword());

            User updatedSavedUser = userService.createUser(existingUser); // Re-saving the updated user
            return new ResponseEntity<>(updatedSavedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content on successful delete
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

