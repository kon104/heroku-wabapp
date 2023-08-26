package com.herokuapp.kon104.webapp.service;

import org.springframework.ui.Model;
import com.herokuapp.kon104.webapp.domain.YConnectUserInfoResponse;

/**
 * YConnect UserInfo Service Interface
 */
public interface YConnectUserInfoService
{
	public YConnectUserInfoResponse getAttribute(String url, String access_token);
	public void addModel(YConnectUserInfoResponse resp, Model model);
}
