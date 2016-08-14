package com.kiesoft.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

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
	private Collection<? extends GrantedAuthority> roles = new ArrayList<>();

	/**
	 * Is authenticated?
	 */
	private Boolean isAuthenticated;
	
	/**
	 * User token for this request
	 */
	private String token;

	/**
	 * Use this constructor when you want to authenticate by username and token
	 * @param name
	 */
	public StatelessAuthentication(String name, String token) {
		this.name = name;
		this.token = token;
		this.isAuthenticated = Boolean.FALSE;
	}

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
		this.roles = collection;
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
		return token;
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
	
	public String getToken() {
		return token;
	}

	/**
	 * If you want to deauthenticate the user on the fly
	 */
	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}
}
