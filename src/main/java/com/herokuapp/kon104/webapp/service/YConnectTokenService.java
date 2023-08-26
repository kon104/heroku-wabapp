package com.herokuapp.kon104.webapp.service;

import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.YConnectTokenResponse;

/**
 * YConnect Token Service Interface
 */
public interface YConnectTokenService
{
	public YConnectTokenResponse generate(String url, String clientId, String clientSecret, String code, HttpServletRequest request);
    public void addModel(YConnectTokenResponse resp, Model model);
}
