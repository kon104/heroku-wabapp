package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * YConnect OpenID Configuration Service
 */
@Service
public class YConnectOpenIdConfigService
{
	@Autowired
	private HttpRequestUtility hrUtil;

	public static final String URL = "https://auth.login.yahoo.co.jp/yconnect/v2/.well-known/openid-configuration";

	// {{{ public YConnectOpenIdConfigResponse discovery()
	public YConnectOpenIdConfigResponse discovery()
	{
		RestTemplate restTemplate = new RestTemplate();
		YConnectOpenIdConfigResponse resp = restTemplate.getForObject(URL, YConnectOpenIdConfigResponse.class);
		return resp;
	}
	// }}}

	// {{{ public String generateNonce()
	public String generateNonce()
	{
		String str = String.valueOf(System.currentTimeMillis() / 1000L);
		if (false) {
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("SHA-256");
			} catch (Exception e) {
			}
			byte[] digest = md.digest(str.getBytes());
			str = String.format("%040x", new BigInteger(1, digest));
		}
		return str;
	}
	// }}}

	// {{{ public String makeAuthUrl(HttpServletRequest request, String clientId, String nonce, int max_age)
	public String makeAuthUrl(YConnectOpenIdConfigResponse resp, HttpServletRequest request, String clientId, String nonce, int max_age)
	{
		String redirect = null;
		try {
			redirect = URLEncoder.encode(hrUtil.getURL(request), "utf-8");
		} catch(Exception e) {
		}
		
		String url = resp.authorization_endpoint
			+ "?response_type=code"
			+ "&client_id=" + clientId
			+ "&redirect_uri=" + redirect
			+ "&scope=openid+profile+email+address"
			+ "&nonce=" + nonce
			+ "&max_age=" + Integer.toString(max_age);

		return url;
	}
	// }}}

}
