package com.kiesoft.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kiesoft.auth.filter.TokenJWTFilter;

@Configuration
@EnableJpaRepositories(basePackages = "com.kiesoft.repository")
@EntityScan(basePackages = { "com.kiesoft.jpa" })
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
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
	
	@Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }	
	
	@Override
    public void configure(WebSecurity webSecurity) throws Exception
    {
        webSecurity
            .ignoring()
                // All of Spring Security will ignore these requests
                .antMatchers(HttpMethod.POST, "/user");
    }
	
	@Bean
	public TokenJWTFilter tokenJWTFilter() throws Exception {
		TokenJWTFilter tokenJWTFilter = new TokenJWTFilter();
		tokenJWTFilter.setAuthenticationManager(authenticationManager());
	  return tokenJWTFilter;
	}

}