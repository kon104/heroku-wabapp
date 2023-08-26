package com.herokuapp.kon104.webapp.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.herokuapp.kon104.webapp.domain.YConnectTokenResponse;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * YConnect Token Service Class
 */
@Service
public class YConnectTokenServiceImpl implements YConnectTokenService
{
	private RestTemplate restTemplate;
	private HttpRequestUtility hrUtil;

	// {{{ public YConnectTokenServiceImpl(RestTemplate restTemplate, HttpRequestUtility hrUtil)
	public YConnectTokenServiceImpl(RestTemplate restTemplate, HttpRequestUtility hrUtil)
	{
		this.restTemplate = restTemplate;
		this.hrUtil = hrUtil;
	}
	// }}}

	// {{{ public YConnectTokenResponse generate(String url, String clientId, String clientSecret, String code, HttpServletRequest request)
	@Override
	public YConnectTokenResponse generate(String url, String clientId, String clientSecret, String code, HttpServletRequest request)
	{
		String redirect = hrUtil.getURL(request);

		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
		param.add("grant_type", "authorization_code");
		param.add("client_id", clientId);
		param.add("client_secret", clientSecret);
		param.add("code", code);
		param.add("redirect_uri", redirect);
		YConnectTokenResponse resp = this.restTemplate.postForObject(url, param, YConnectTokenResponse.class);

		return resp;
	}
	// }}}

}
