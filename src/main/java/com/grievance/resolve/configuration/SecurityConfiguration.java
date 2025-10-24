package com.grievance.resolve.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers(
						"/v3/api-docs/**",
						"/swagger-ui/**",
						"/swagger-ui.html",
						"/api/users/verify",
						"/api/users/register",
						"/api/grievances/user/{userId}",
						"/api/auth/login/verify-otp",
						"/api/grievances/raise",
						"/api/auth/login/password"
						).permitAll()
		.anyRequest().authenticated());
		return http.build();
	}
	
}
