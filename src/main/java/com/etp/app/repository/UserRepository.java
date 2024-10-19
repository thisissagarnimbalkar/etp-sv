package com.etp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etp.app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    // You can define custom query methods here if needed
    User findByEmail(String email); // Example of a custom query method

}
