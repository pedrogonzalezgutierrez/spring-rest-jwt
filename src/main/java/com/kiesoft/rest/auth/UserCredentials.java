package com.kiesoft.rest.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * This should be stored in DB. Since this is a POC I will leave here the credentials
 * 
 */
public class UserCredentials {

	private Map<String, String> userSecret = new HashMap<>();
	private Map<String, String> userRoles = new HashMap<>();

	public UserCredentials() {

		userSecret.put("admin", "admin");
		userRoles.put("admin", "ROLE_ADMIN");

		userSecret.put("editor", "editor");
		userRoles.put("editor", "ROLE_EDITOR");

		userSecret.put("staff", "staff");
		userRoles.put("staff", "ROLE_STAFF");

	}

	public Map<String, String> getUserSecret() {
		return userSecret;
	}

	public Map<String, String> getUserRoles() {
		return userRoles;
	}
}
