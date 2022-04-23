package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

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

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "authorization_code");
		body.add("code", code);
		body.add("redirect_uri", redirect);

		HttpEntity<MultiValueMap<String, String>> hEntity = hrUtil.createHttpEntityWithBasicAuth(body, clientId, clientSecret);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<YConnectTokenResponse> rEntity = restTemplate.postForEntity(url, hEntity, YConnectTokenResponse.class);
		YConnectTokenResponse resp  = rEntity.getBody();

		return resp;
	}
	// }}}

	// {{{ public YConnectTokenResponse refresh(String url, String clientId, String clientSecret, String refresh_token)
	public YConnectTokenResponse refresh(String url, String clientId, String clientSecret, String refresh_token)
	{
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "refresh_token");
		body.add("refresh_token", refresh_token);

		HttpEntity<MultiValueMap<String, String>> hEntity = hrUtil.createHttpEntityWithBasicAuth(body, clientId, clientSecret);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<YConnectTokenResponse> rEntity = restTemplate.postForEntity(url, hEntity, YConnectTokenResponse.class);
		YConnectTokenResponse resp  = rEntity.getBody();

		return resp;
	}
	// }}}

    // {{{ public void addModel(YConnectTokenResponse resp, Model model)
    public void addModel(YConnectTokenResponse resp, Model model)
	{
		if (resp.access_token != null)	model.addAttribute("access_token", resp.access_token);
		if (resp.token_type != null)	model.addAttribute("token_type", resp.token_type);
		if (resp.refresh_token != null)	model.addAttribute("refresh_token", resp.refresh_token);
		if (resp.expires_in != null)	model.addAttribute("expires_in", resp.expires_in);
		if (resp.id_token != null)		model.addAttribute("id_token", resp.id_token);
	}
	// }}}

}
