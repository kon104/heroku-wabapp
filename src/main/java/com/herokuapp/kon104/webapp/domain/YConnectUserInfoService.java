package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;
//	import java.util.HashMap;
//	import java.util.Map;

/**
 * YConnect UserInfo Service
 */
@Service
public class YConnectUserInfoService
{

	@Autowired
	private HttpRequestUtility hrUtil;

	// {{{ public YConnectUserInfoResponse getAttribute(String url, String access_token)
	public YConnectUserInfoResponse getAttribute(String url, String access_token)
	{
//		url = url + "?access_token={access_token}";
//		Map<String, String> vars = new HashMap<String, String>();
//		vars.put("access_token", access_token);
//		RestTemplate restTemplate = new RestTemplate();
//		YConnectUserInfoResponse resp = restTemplate.getForObject(url, YConnectUserInfoResponse.class, vars);

		HttpEntity<String> hEntity = hrUtil.createHttpEntityWithBearerAuth(access_token);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<YConnectUserInfoResponse> rEntity = restTemplate.exchange(url,
			HttpMethod.GET, hEntity, YConnectUserInfoResponse.class);
		YConnectUserInfoResponse resp  = rEntity.getBody();

		return resp;
	}
	// }}}

	// {{{ public void addModel(YConnectUserInfoResponse resp, Model model)
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
