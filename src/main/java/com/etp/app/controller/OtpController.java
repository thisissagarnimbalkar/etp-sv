package com.etp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etp.app.serviceImpl.OtpService;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/otp")
public class OtpController {
	
	
	private final OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send")
    public String sendVerification(@RequestParam String phoneNumber, @RequestParam String channel) {
    	System.out.println("Controller Phone Number " + phoneNumber);
        return otpService.sendVerification(phoneNumber, channel);
    }
    
    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String phoneNumber, @RequestParam String code) {
    	System.out.println("Controller code " + code);
    	System.out.println("Controller Phone Number " + phoneNumber);
        return otpService.verifyOtp(phoneNumber, code);
    }
    
}
