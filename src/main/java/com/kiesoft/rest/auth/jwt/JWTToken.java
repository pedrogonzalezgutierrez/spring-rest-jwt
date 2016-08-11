package com.kiesoft.rest.auth.jwt;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Decode the token in order to make sure we are dealing with a JWT token If the
 * token is correct it will extract the claims (metadata) of the token, it means
 * populate username
 */
public class JWTToken implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8950274040320356798L;

	private String token;
	private String username;

	public JWTToken(String token) throws IOException {
		this.token = token;

		// Try to decode the token
		final Jwt jwt = JwtHelper.decode(token);

		// Extract claims
		ObjectMapper mapper = new ObjectMapper();
		JWTPayload claims = mapper.readValue(jwt.getClaims(), JWTPayload.class);
		this.username = claims.getUsername();
	}

	@Override
	public String getName() {
		return this.username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
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
	 * The identity of the principal being authenticated. In the case of an
	 * authentication request with username and password, this would be the
	 * username.
	 */
	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return false;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
