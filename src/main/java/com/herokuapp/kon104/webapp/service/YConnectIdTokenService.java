package com.herokuapp.kon104.webapp.service;

import java.util.Map;

/**
 * YConnect IdToken Service Interface
 */
public interface YConnectIdTokenService
{
	public static final String URL_PUBLIC_KEYS = "https://auth.login.yahoo.co.jp/yconnect/v2/public-keys";

	public Map<String, Boolean> verify(String idtoken, String issuer, String clientId, String nonce, String access_token, int max_age);

}
