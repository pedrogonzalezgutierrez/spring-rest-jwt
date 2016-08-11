package com.kiesoft.rest.auth.authority;

import org.springframework.security.core.GrantedAuthority;

public class DefaultAuthority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -683141658273439190L;
	private String role;

	public DefaultAuthority(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return this.role;
	}

}
