package com.kiesoft.rest.auth.aprovider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.MacSigner;

import com.kiesoft.rest.auth.BMJAuthentication;
import com.kiesoft.rest.auth.JWTToken;
import com.kiesoft.rest.auth.UserCredentials;
import com.kiesoft.rest.auth.authority.DefaultAuthority;

public class JWTAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserCredentials userCredentials;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		JWTToken jwtToken = (JWTToken) authentication;

		// Get password from DB
		String password = userCredentials.getUserSecret().get(jwtToken.getName());

		// Check if the signature is signed with this secret/password
		if (password != null) {
			MacSigner signer = new MacSigner(password);
			try {
				JwtHelper.decodeAndVerify(jwtToken.getToken(), signer);
			} catch (InvalidSignatureException e) {
				return null;
			}
		}

		// Token is valid!

		// Extract roles from DB
		String rolename = userCredentials.getUserRoles().get(jwtToken.getName());
		DefaultAuthority role = new DefaultAuthority(rolename);
		List<DefaultAuthority> roles = new ArrayList<>();
		roles.add(role);

		// Create the authentication object
		BMJAuthentication validJWTToken = new BMJAuthentication(jwtToken.getName(), password, roles);

		return validJWTToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(JWTToken.class);
	}

}