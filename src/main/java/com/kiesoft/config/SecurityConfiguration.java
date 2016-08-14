package com.kiesoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kiesoft.auth.aprovider.AuthenticationProviderJWT;
import com.kiesoft.auth.filter.TokenFilterJWT;

@Configuration
@EnableJpaRepositories(basePackages = "com.kiesoft.repository")
@EntityScan(basePackages = { "com.kiesoft.jpa" })
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/*
	 * Requests secured
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		// No session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// No CSRF
		http.csrf().disable();
		
        http
        	.addFilterBefore(tokenJWTFilter(), UsernamePasswordAuthenticationFilter.class)
        	.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user").hasRole("ADMIN");
    }
	
	/*
	 * All of Spring Security will ignore these requests 
	 */
	@Override
	public void configure(WebSecurity webSecurity) throws Exception
	{
		webSecurity
		.ignoring()
			.antMatchers(HttpMethod.POST, "/user");
	}
	
	/*
	 * Set JWT Authentication Provider into AuthenticationManager 
	 */
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProviderJWT());
	}	
	
	
	/*
	 * JWT Token Filter
	 */
	@Bean
	public TokenFilterJWT tokenJWTFilter() throws Exception {
		TokenFilterJWT tokenJWTFilter = new TokenFilterJWT();
		tokenJWTFilter.setAuthenticationManager(authenticationManager());
		return tokenJWTFilter;
	}
	
	/*
	 * JWT Authentication Provider 
	 */
	@Bean
	public AuthenticationProviderJWT authenticationProviderJWT() throws Exception {
		return new AuthenticationProviderJWT();
	}

}