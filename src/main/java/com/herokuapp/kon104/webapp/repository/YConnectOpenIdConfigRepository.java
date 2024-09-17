package com.herokuapp.kon104.webapp.repository;

import com.herokuapp.kon104.webapp.entity.YConnectOpenIdConfigResponse;

/**
 * YConnect OpenID Configuration Repository Interface
 */
public interface YConnectOpenIdConfigRepository
{
	public static final String URL = "https://auth.login.yahoo.co.jp/yconnect/v2/.well-known/openid-configuration";
	public YConnectOpenIdConfigResponse discovery();
}
