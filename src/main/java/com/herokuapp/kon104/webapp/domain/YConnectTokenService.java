package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;
import org.springframework.util.MultiValueMap;

/**
 * YConnect Token Service
 */
@Service
public class YConnectTokenService
{
	@Autowired
	private HttpRequestUtility hrUtil;

	// {{{ public YConnectTokenResponse generate(String url, String clientId, String clientSecret, String code, HttpServletRequest request)
	public YConnectTokenResponse generate(String url, String clientId, String clientSecret, String code, HttpServletRequest request)
	{
		String redirect = hrUtil.getURL(request);

		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
		param.add("grant_type", "authorization_code");
		param.add("client_id", clientId);
		param.add("client_secret", clientSecret);
		param.add("code", code);
		param.add("redirect_uri", redirect);
		RestTemplate restTemplate = new RestTemplate();
		YConnectTokenResponse resp = restTemplate.postForObject(url, param, YConnectTokenResponse.class);

		return resp;
	}
	// }}}

    // {{{ public void addModel(YConnectTokenResponse resp, Model model)
    public void addModel(YConnectTokenResponse resp, Model model)
	{
		model.addAttribute("access_token", resp.access_token);
		model.addAttribute("token_type", resp.token_type);
		model.addAttribute("refresh_token", resp.refresh_token);
		model.addAttribute("expires_in", resp.expires_in);
		model.addAttribute("id_token", resp.id_token);
	}
	// }}}

}
