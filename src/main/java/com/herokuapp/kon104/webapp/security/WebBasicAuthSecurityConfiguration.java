package com.herokuapp.kon104.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebBasicAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BasicAuthenticationProvider provider;

	// {{{ public void configure(WebSecurity web) throws Exception
	@Override
	public void configure(WebSecurity web) throws Exception {
		System.out.println( ">>> WebBasicAuthSecurityConfiguration#configure(WebSecurity)" );
	}
	// }}}

	// {{{ protected void configure(HttpSecurity http) throws Exception
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println( ">>> WebBasicAuthSecurityConfiguration#configure(HttpSecurity)" );
		http.antMatcher("/private/**");
		http.httpBasic();
		http.authorizeRequests().anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	// }}}

	// {{{ protected void configure(AuthenticationManagerBuilder auth) throws Exception
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println( ">>> WebBasicAuthSecurityConfiguration#configure(AuthenticationManagerBuilder)" );
		auth.authenticationProvider(provider);
	}
	// }}}

}

