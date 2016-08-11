package com.kiesoft.rest.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kiesoft.rest.auth.StatelessAuthentication;
import com.kiesoft.rest.auth.jwt.JWTToken;

public class JWTFilter extends OncePerRequestFilter {

	private final AuthenticationManager authenticationManager;

	public JWTFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Get the token from the request
		String token = request.getParameter("token");

		Authentication authentication;
		if (token != null) {
			// There is a token in the request. Check if it is valid
			authentication = new JWTToken(token);
		} else {
			// There is no token in the request, creating a guest object
			authentication = new StatelessAuthentication();
		}
		// Try to authenticate
		Authentication finalAuthentication=authenticationManager.authenticate(authentication);
		if( finalAuthentication != null ) {
			SecurityContextHolder.getContext().setAuthentication(finalAuthentication);
			filterChain.doFilter(request, response);
		}
	}

}