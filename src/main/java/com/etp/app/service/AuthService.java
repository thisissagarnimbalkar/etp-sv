package com.etp.app.service;

public interface AuthService {
	
	public String authenticateUser(String phoneNumber, String password) throws Exception;

}
