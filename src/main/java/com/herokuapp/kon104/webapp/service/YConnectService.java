package com.herokuapp.kon104.webapp.service;

import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * YConnect Service Interface
 *
 */
public interface YConnectService
{
	public static final int max_age = 3600;

	public void mainV2(
			Model model,
			HttpServletRequest request,
			String mode, String clientId, String clientSecret,
			String nonce, String code,
			String access_token, String token_type, String refresh_token,
			String expires_in, String id_token);

}
