package com.etp.app.serviceImpl;

public interface OtpService {

//	void sendVerification(String phoneNumber);

	String sendVerification(String phoneNumber, String channel);
	
	String verifyOtp(String phoneNumber, String code);

}
