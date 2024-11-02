package com.etp.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password="
				+ password + "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique user ID

    @Column(name = "name", nullable = false)
    private String name; // User's name

    @Column(name = "phoneno", nullable = false)
    private String phoneNumber; // User's phone number

    @Column(name = "email", nullable = false)
    private String email; // User's email address

    @Column(name = "password", nullable = false)
    private String password; // User's password

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {  // Corrected this method name
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {  // Corrected this method name
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
