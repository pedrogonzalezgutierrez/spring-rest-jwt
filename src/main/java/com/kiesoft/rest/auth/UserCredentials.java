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
		// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluIn0.ZMyI-tinnRdVQJ_XQoaidj3VL7LobfIWRHDE_HSjizo

		userSecret.put("editor", "editor");
		userRoles.put("editor", "ROLE_EDITOR");
		// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImVkaXRvciJ9.QsNuh2EmAjhGA1ISOeot6jeJoxipe1kD5WoJErX9uio

		userSecret.put("staff", "staff");
		userRoles.put("staff", "ROLE_STAFF");
		// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InN0YWZmIn0.2Q11SCPr1vaMiiz58oJGwmYJV5BDyBQH1XkSkwZeXEQ

	}

	public Map<String, String> getUserSecret() {
		return userSecret;
	}

	public Map<String, String> getUserRoles() {
		return userRoles;
	}
}
