package com.etp.app.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.etp.app.serviceImpl.OtpService;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Service
public class OtpServiceImpl implements OtpService{

	private final RestTemplate restTemplate;

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.service.sid}")
    private String serviceSid;

    public OtpServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String sendVerification(String phoneNumber, String channel) {
        String url = String.format("https://verify.twilio.com/v2/Services/%s/Verifications", serviceSid);
        // Prepare the request body using MultiValueMap
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("To", phoneNumber);
        requestBody.add("Channel", channel);
        System.out.println("phonenumber " + phoneNumber);
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(accountSid, authToken);
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        // Create the request entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send the request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response.getBody();
    }
    
    @Override
    public String verifyOtp(String phoneNumber, String code) {
    	try {
			phoneNumber = URLDecoder.decode(phoneNumber, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("phone no after replacing "+ phoneNumber);
    	String url = String.format("https://verify.twilio.com/v2/Services/%s/Verifications/%s", serviceSid, phoneNumber);

        // Prepare the request body using MultiValueMap
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("Code", code);
        requestBody.add("Status", "approved");  // Add the required Status parameter

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(accountSid, authToken);
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        // Create the request entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send the request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response.getBody();
    }

}
