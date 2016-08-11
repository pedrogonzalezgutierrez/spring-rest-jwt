package com.kiesoft.rest.auth.jwt;

/**
 * Map of the claims of the JWT token
 * 
 */
public class JWTPayload {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
