package com.etp.app.serviceImpl;

import org.springframework.stereotype.Service;

import com.etp.app.entity.User;
import com.etp.app.repository.UserRepository;
import com.etp.app.service.AuthService;
import com.etp.app.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public String authenticateUser(String phoneNumber, String password) throws Exception{
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        System.out.println("userOptional = "+ userOptional.toString());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("user = "+ user.getPassword() + "   " + password);
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Generate JWT token
                return jwtUtils.generateJwtToken(phoneNumber);
            }
        }
        throw new Exception("Invalid credentials");
    }

}
