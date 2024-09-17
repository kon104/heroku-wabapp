package com.herokuapp.kon104.webapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebBasicAuthSecurityConfiguration
{

	private BasicAuthenticationProvider provider;

	// {{{ public WebBasicAuthSecurityConfiguration(BasicAuthenticationProvider provider)
	public WebBasicAuthSecurityConfiguration(BasicAuthenticationProvider provider)
	{
		this.provider = provider;
	}
	// }}}

	// {{{ public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(this.provider);
		AuthenticationManager manager = builder.build();

		http
			.authenticationManager(manager)
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/private/**").authenticated()
				.anyRequest().permitAll()
			)
			.httpBasic();

		return http.build();
	}
	// }}}

}

