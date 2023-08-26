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

	// {{{ public void addModel(YConnectUserInfoResponse resp, Model model)
	@Override
	public void addModel(YConnectUserInfoResponse resp, Model model)
	{
		model.addAttribute("sub", resp.sub);
		model.addAttribute("name", resp.name);
		model.addAttribute("given_name", resp.given_name);
		model.addAttribute("family_name", resp.family_name);
		model.addAttribute("gender", resp.gender);
		model.addAttribute("zoneinfo", resp.zoneinfo);
		model.addAttribute("locale", resp.locale);
		model.addAttribute("birthdate", resp.birthdate);
		model.addAttribute("nickname", resp.nickname);
		model.addAttribute("picture", resp.picture);
		model.addAttribute("email", resp.email);
		model.addAttribute("email_verified", resp.email_verified);
	}
	// }}}

}
