package com.herokuapp.kon104.webapp.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import com.herokuapp.kon104.webapp.domain.YConnectUserInfoResponse;

/**
 * YConnect UserInfo Service Implement Class
 */
@Service
public class YConnectUserInfoServiceImpl implements YConnectUserInfoService
{

	// {{{ public YConnectUserInfoResponse getAttribute(String url, String access_token)
	@Override
	public YConnectUserInfoResponse getAttribute(String url, String access_token)
	{
		url = url + "?access_token={access_token}";
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("access_token", access_token);
		RestTemplate restTemplate = new RestTemplate();
		YConnectUserInfoResponse resp = restTemplate.getForObject(url, YConnectUserInfoResponse.class, vars);

		return resp;
	}
	// }}}

}
