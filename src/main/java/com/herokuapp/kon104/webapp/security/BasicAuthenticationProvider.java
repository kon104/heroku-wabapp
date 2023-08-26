package com.herokuapp.kon104.webapp.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class BasicAuthenticationProvider implements AuthenticationProvider {

	@Value("${security.bauth.n}")
	private String name = null;

	@Value("${security.bauth.p}")
	private String pass = null;

	@Value("${security.bauth.algo}")
	private String algo = null;

	@Value("${security.bauth.digit}")
	private int digit = -1;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// {{{ public Authentication authenticate(Authentication authentication) throws AuthenticationException
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info(">>> Hello!!!");
		logger.info(">>> start: #" + new Object(){}.getClass().getEnclosingMethod().getName() + "()");
		System.out.println(">>> BasicAuthenticationProvider#authenticate()");

		String inputName = authentication.getName();
		String inputPass = authentication.getCredentials().toString();
		String hashName = this.convHash(inputName, this.algo, this.digit);
		String hashPass = this.convHash(inputPass, this.algo, this.digit);

		if (this.name.equals(hashName) && this.pass.equals(hashPass)) {
			return new UsernamePasswordAuthenticationToken(inputName, inputPass, authentication.getAuthorities());
		} else {
			throw new BadCredentialsException("Username and Password are not correct.");
		}
	}
	// }}}

	// {{{ public boolean supports(Class<?> authentication)
	@Override
	public boolean supports(Class<?> authentication) {
		System.out.println(">>> BasicAuthenticationProvider#supports()");
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	// }}}

	// {{{ private String convHash(String raw, String algo, int digit)
	private String convHash(String raw, String algo, int digit) {
		String format = String.format("%%0%dx", digit);
		String hash = "";
		try {
			MessageDigest digest = MessageDigest.getInstance(algo);
			digest.reset();
			digest.update(raw.getBytes("utf8"));
			hash = String.format(format, new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hash;
	}
	// }}}

}
