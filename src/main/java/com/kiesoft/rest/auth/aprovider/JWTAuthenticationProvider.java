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

import com.kiesoft.rest.auth.StatelessAuthentication;
import com.kiesoft.rest.auth.UserCredentials;
import com.kiesoft.rest.auth.authority.DefaultGrantedAuthority;
import com.kiesoft.rest.auth.jwt.JWTToken;

public class JWTAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserCredentials userCredentials;

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(JWTToken.class);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		JWTToken jwtToken = (JWTToken) authentication;

		// Here you should get password from DB instead of this POC
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
		DefaultGrantedAuthority role = new DefaultGrantedAuthority(rolename);
		List<DefaultGrantedAuthority> roles = new ArrayList<>();
		roles.add(role);

		// Create the authentication object
		StatelessAuthentication validJWTToken = new StatelessAuthentication(jwtToken.getName(), password, roles);

		return validJWTToken;
	}

}