package com.kiesoft.auth.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiesoft.auth.StatelessAuthentication;
import com.kiesoft.auth.authority.DefaultGrantedAuthority;
import com.kiesoft.auth.jwt.JWTPayload;
import com.kiesoft.dto.role.RoleDTO;
import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.service.note.UserDTOService;

public class TokenJWTFilter extends OncePerRequestFilter {

	@Autowired
	private UserDTOService userDTOService;

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
				final UserDTO userDTO=userDTOService.findByUsername(claims.getUsername());
				
				final MacSigner signer = new MacSigner(userDTO.getPassword());
				try {
					JwtHelper.decodeAndVerify(token, signer);
					
					// Token is valid
					
					// Set up Roles
					List<DefaultGrantedAuthority> roles=new ArrayList<>();
					for( RoleDTO roleDTO : userDTO.getRoles() ) {
						roles.add(new DefaultGrantedAuthority(roleDTO.getRolename()));
					}
					
					// Try to authenticate
					StatelessAuthentication authentication=new StatelessAuthentication(userDTO.getUsername(), userDTO.getPassword(), roles);
					Authentication finalAuthentication=authenticationManager.authenticate(authentication);
					if( finalAuthentication != null ) {
						SecurityContextHolder.getContext().setAuthentication(finalAuthentication);
					}
					
					
				} catch (InvalidSignatureException e) {
					// Token is not valid. Log an exception
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