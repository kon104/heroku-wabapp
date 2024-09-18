package com.herokuapp.kon104.webapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import com.herokuapp.kon104.webapp.entity.YConnectUserInfoResponse;

/**
 * YConnect UserInfo Repository Implement Class
 */
@Repository
public class YConnectUserInfoRepositoryImpl implements YConnectUserInfoRepository
{
	private final RestTemplate restTemplate;

	// {{{ public YConnectUserInfoRepositoryImpl(RestTemplate restTemplate)
	public YConnectUserInfoRepositoryImpl(RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}
	// }}}

	// {{{ public YConnectUserInfoResponse getAttribute(String url, String access_token)
	@Override
	public YConnectUserInfoResponse getAttribute(String url, String access_token)
	{
		url = url + "?access_token={access_token}";
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("access_token", access_token);
		YConnectUserInfoResponse resp = this.restTemplate.getForObject(url, YConnectUserInfoResponse.class, vars);

		return resp;
	}
	// }}}

}
