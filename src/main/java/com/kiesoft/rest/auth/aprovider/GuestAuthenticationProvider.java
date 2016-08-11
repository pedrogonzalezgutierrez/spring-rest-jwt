package com.kiesoft.rest.auth.aprovider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.kiesoft.rest.auth.StatelessAuthentication;

public class GuestAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// We expect that the Authentication is valid (there are just two constructors in StatelessAuthentication)
		StatelessAuthentication authenticationObject = (StatelessAuthentication) authentication;
		return authenticationObject;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(StatelessAuthentication.class);
	}

}