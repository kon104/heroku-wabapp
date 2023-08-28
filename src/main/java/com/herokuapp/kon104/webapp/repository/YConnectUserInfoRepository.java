package com.herokuapp.kon104.webapp.repository;

import org.springframework.ui.Model;
import com.herokuapp.kon104.webapp.domain.YConnectUserInfoResponse;

/**
 * YConnect UserInfo Repository Interface
 */
public interface YConnectUserInfoRepository
{
	public YConnectUserInfoResponse getAttribute(String url, String access_token);
}
