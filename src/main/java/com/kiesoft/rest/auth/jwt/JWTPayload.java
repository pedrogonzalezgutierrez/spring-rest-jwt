package com.kiesoft.rest.auth.jwt;

/**
 * Map of the claims of the JWT token
 * 
 * @author pedrola
 *
 */
public class JWTPayload {

	private String username;
	private String icsNumber;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIcsNumber() {
		return icsNumber;
	}

	public void setIcsNumber(String icsNumber) {
		this.icsNumber = icsNumber;
	}

}
