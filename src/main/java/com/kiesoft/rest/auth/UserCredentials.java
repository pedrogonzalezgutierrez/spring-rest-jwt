package com.kiesoft.rest.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * This should be stored in DB. Since this is a POC I will leave here for
 * simplify Here we have usernames and its related secret (password) and ROL
 * 
 * @author pedrola
 *
 */
public class UserCredentials {

	private Map<String, String> userSecret = new HashMap<>();
	private Map<String, String> userRoles = new HashMap<>();

	public UserCredentials() {

		userSecret.put("pedrola", "this_is_a_SECRET");
		userRoles.put("pedrola", "ROLE_ADMIN");

		userSecret.put("docato", "so_rubbish!");
		userRoles.put("docato", "ROLE_EDITOR");

		userSecret.put("androidAPP", "another_SECRET");
		userRoles.put("androidAPP", "ROLE_STAFF");

		userSecret.put("guest", "gu3st-US3R");
		userRoles.put("guest", "ROLE_GUEST");

	}

	public Map<String, String> getUserSecret() {
		return userSecret;
	}

	public Map<String, String> getUserRoles() {
		return userRoles;
	}
}
