package com.kiesoft.auth.aprovider;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.MacSigner;

import com.kiesoft.auth.StatelessAuthentication;
import com.kiesoft.auth.authority.DefaultGrantedAuthority;
import com.kiesoft.dto.role.RoleDTO;
import com.kiesoft.dto.user.UserDTO;
import com.kiesoft.exceptions.InvalidTokenException;
import com.kiesoft.service.user.UserDTOService;

public class AuthenticationProviderJWT implements AuthenticationProvider {
	
	@Autowired
	private UserDTOService userDTOService;

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(StatelessAuthentication.class);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		StatelessAuthentication statelessAuthentication = (StatelessAuthentication) authentication;
		
		final UserDTO userDTO=userDTOService.findByUsername(statelessAuthentication.getName());
		
		final MacSigner signer = new MacSigner(userDTO.getPassword());
		try {
			JwtHelper.decodeAndVerify(statelessAuthentication.getToken(), signer);
			
			// Token is valid
			
			// Set up Roles
			Collection<DefaultGrantedAuthority> roles=new ArrayList<>();
			for( RoleDTO roleDTO : userDTO.getRoles() ) {
				roles.add(new DefaultGrantedAuthority(roleDTO.getRolename()));
			}
			
			return new StatelessAuthentication(userDTO.getUsername(), userDTO.getPassword(), roles);
			
		} catch (InvalidSignatureException e) {
			throw new InvalidTokenException(statelessAuthentication.getToken());
		}
		
	}

}