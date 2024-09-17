package com.herokuapp.kon104.webapp.repository;

import jakarta.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.entity.YConnectTokenResponse;

/**
 * YConnect Token Repository Interface
 */
public interface YConnectTokenRepository
{
	public YConnectTokenResponse generate(String url, String clientId, String clientSecret, String code, HttpServletRequest request);
}
