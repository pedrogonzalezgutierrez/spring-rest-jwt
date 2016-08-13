package com.kiesoft.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.kiesoft.auth.authority.DefaultGrantedAuthority;

public class StatelessAuthentication implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3951505989870223827L;

	/**
	 * User name
	 */
	private String name;

	/**
	 * User password
	 */
	private String password;

	/**
	 * User roles
	 */
	private List<DefaultGrantedAuthority> roles = new ArrayList<>();

	/**
	 * Is authenticated?
	 */
	private Boolean isAuthenticated;

	/**
	 * Use this constructor for populating a User with Roles
	 * 
	 * @param name
	 * @param password
	 * @param collection
	 */
	public StatelessAuthentication(String name, String password, Collection<? extends GrantedAuthority> collection) {
		this.name = name;
		this.password = password;
		List<DefaultGrantedAuthority> roles = new ArrayList<>();
		for (GrantedAuthority grantedAuthority : collection) {
			roles.add(new DefaultGrantedAuthority(grantedAuthority.getAuthority()));
		}
		this.roles = roles;
		this.isAuthenticated = Boolean.TRUE;
	}

	/**
	 * username
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Roles
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	/**
	 * The credentials that prove the principal is correct. This is usually a
	 * password, but could be anything relevant to the AuthenticationManager
	 */
	@Override
	public Object getCredentials() {
		return password;
	}

	/**
	 * Stores additional details about the authentication request. These might
	 * be an IP address, certificate serial number or any other metadata
	 */
	@Override
	public Object getDetails() {
		return null;
	}

	/**
	 * The identity of the principal being authenticated.
	 */
	@Override
	public Object getPrincipal() {
		return name;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	/**
	 * If you want to deauthenticate the user on the fly
	 */
	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}
}