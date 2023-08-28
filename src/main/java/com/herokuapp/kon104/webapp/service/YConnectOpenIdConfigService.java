package com.herokuapp.kon104.webapp.service;

import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.YConnectOpenIdConfigResponse;

/**
 * YConnect OpenID Configuration Service Interface
 */
public interface YConnectOpenIdConfigService
{
	public String generateNonce();
	public String makeAuthUrl(YConnectOpenIdConfigResponse resp, HttpServletRequest request, String clientId, String nonce, int max_age);
}
