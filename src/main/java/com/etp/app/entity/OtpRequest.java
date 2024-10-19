package com.etp.app.entity;

public class OtpRequest {
	
	private String to; // phone number
    private String channel; // e.g., "sms"

    // Constructors
    public OtpRequest() {}

    public OtpRequest(String to, String channel) {
        this.to = to;
        this.channel = channel;
    }

    // Getters and Setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
