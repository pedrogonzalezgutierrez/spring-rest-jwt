package com.kiesoft.auth.aprovider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.kiesoft.auth.StatelessAuthentication;

public class JWTAuthenticationProvider implements AuthenticationProvider {

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(StatelessAuthentication.class);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return (StatelessAuthentication) authentication;
	}

}