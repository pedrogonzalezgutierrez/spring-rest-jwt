package com.kiesoft.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiesoft.auth.StatelessAuthentication;
import com.kiesoft.auth.jwt.JWTPayload;

public class TokenFilterJWT extends OncePerRequestFilter {

	private AuthenticationManager authenticationManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// Get the token from the request
		String token = request.getParameter("token");

		if (token != null) {
			// There is a token in the request
			
			// Check if the token is valid
			
			// Extract claims
			final Jwt jwt = JwtHelper.decode(token);
			final ObjectMapper mapper = new ObjectMapper();
			final JWTPayload claims = mapper.readValue(jwt.getClaims(), JWTPayload.class);
			
			if( claims.getUsername() != null ) {
				
				// Try to authenticate
				Authentication finalAuthentication=authenticationManager.authenticate(new StatelessAuthentication(claims.getUsername(), token));
				if( finalAuthentication != null ) {
					SecurityContextHolder.getContext().setAuthentication(finalAuthentication);
				}
				
			}
			
		}
		
		// Keep going in the Filter Chain
		filterChain.doFilter(request, response);
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	

}