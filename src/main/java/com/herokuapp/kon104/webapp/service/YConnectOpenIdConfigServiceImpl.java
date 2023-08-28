package com.herokuapp.kon104.webapp.service;

import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.YConnectOpenIdConfigResponse;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * YConnect OpenID Configuration Service Implement Class
 */
@Service
public class YConnectOpenIdConfigServiceImpl implements YConnectOpenIdConfigService
{
	private HttpRequestUtility hrUtil;

	// {{{ public YConnectOpenIdConfigServiceImpl(HttpRequestUtility hrUtil)
	public YConnectOpenIdConfigServiceImpl(HttpRequestUtility hrUtil)
	{
		this.hrUtil = hrUtil;
	}
	// }}}

	// {{{ public String generateNonce()
	@Override
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

	// {{{ public String makeAuthUrl(YConnectOpenIdConfigResponse resp, HttpServletRequest request, String clientId, String nonce, int max_age)
	@Override
	public String makeAuthUrl(YConnectOpenIdConfigResponse resp, HttpServletRequest request, String clientId, String nonce, int max_age)
	{
		String redirect = null;
		try {
			redirect = URLEncoder.encode(this.hrUtil.getURL(request), "utf-8");
		} catch(Exception e) {
		}
		
		String url = resp.getAuthorization_endpoint()
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
