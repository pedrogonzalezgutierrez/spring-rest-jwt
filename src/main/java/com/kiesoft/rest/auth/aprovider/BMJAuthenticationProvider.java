package com.kiesoft.rest.auth.aprovider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.kiesoft.rest.auth.BMJAuthentication;

public class BMJAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// We expect that the Authentication is valid (there are just two
		// constructors)
		BMJAuthentication authenticationObject = (BMJAuthentication) authentication;
		return authenticationObject;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(BMJAuthentication.class);
	}

}