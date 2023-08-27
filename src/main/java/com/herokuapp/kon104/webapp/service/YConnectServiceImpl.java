package com.herokuapp.kon104.webapp.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.herokuapp.kon104.webapp.domain.YConnectOpenIdConfigResponse;
import com.herokuapp.kon104.webapp.domain.YConnectTokenResponse;
import com.herokuapp.kon104.webapp.domain.YConnectUserInfoResponse;
import com.herokuapp.kon104.webapp.service.YConnectIdTokenService;
import com.herokuapp.kon104.webapp.service.YConnectOpenIdConfigService;
import com.herokuapp.kon104.webapp.service.YConnectTokenService;
import com.herokuapp.kon104.webapp.service.YConnectUserInfoService;

/**
 *
 * YConnect Service Implement Class
 *
 */
@Service
public class YConnectServiceImpl implements YConnectService
{
	private YConnectOpenIdConfigService yconOpenIdConf;
	private YConnectTokenService yconToken;
	private YConnectUserInfoService yconUserInfo;
	private YConnectIdTokenService yconIdToken;
	private HttpSession session;

	// {{{ public YConnectService(...)
	public YConnectServiceImpl(
		YConnectOpenIdConfigService yconOpenIdConf,
		YConnectTokenService yconToken,
		YConnectUserInfoService yconUserInfo,
		YConnectIdTokenService yconIdToken,
		HttpSession session)
	{
		this.yconOpenIdConf = yconOpenIdConf;
		this.yconToken = yconToken;
		this.yconUserInfo = yconUserInfo;
		this.yconIdToken = yconIdToken;
		this.session = session;
	}
	// }}}

	// {{{ public void v2(...)
	@Override
	public void v2(
			Model model,
			HttpServletRequest request,
			String mode, String clientId, String clientSecret,
			String nonce, String code,
			String access_token, String token_type, String refresh_token,
			String expires_in, String id_token)
	{
		YConnectOpenIdConfigResponse respOpenId = this.yconOpenIdConf.discovery();

		if ("setid".equals(mode) == true) {
			nonce = this.yconOpenIdConf.generateNonce();
			this.session.setAttribute("nonce", nonce);
			this.session.setAttribute("clientid", clientId);
			this.session.setAttribute("secret", clientSecret);
		} else
		if ("idtoken".equals(mode) == true) {
			model.addAttribute("code", code);
			YConnectTokenResponse respToken = new YConnectTokenResponse(access_token, token_type, refresh_token, expires_in, id_token);
			model.addAttribute("yctoken", respToken);
			String issuer = respOpenId.getIssuer();
			Map<String, Boolean> result = this.yconIdToken.verify(id_token, issuer, clientId, nonce, access_token, this.max_age);
			model.addAttribute("verify", result);
		} else
		if ("userinfo".equals(mode) == true) {
			model.addAttribute("code", code);
			String url = respOpenId.getUserinfo_endpoint();
			YConnectUserInfoResponse respUser = this.yconUserInfo.getAttribute(url, access_token);
			model.addAttribute("ycuser", respUser);
			YConnectTokenResponse respToken = new YConnectTokenResponse(access_token, token_type, refresh_token, expires_in, id_token);
			model.addAttribute("yctoken", respToken);
		} else
		if (code != null) {
			nonce = (String)this.session.getAttribute("nonce");
			clientId = (String)this.session.getAttribute("clientid");
			clientSecret = (String)this.session.getAttribute("secret");
			model.addAttribute("code", code);
			String url = respOpenId.getToken_endpoint();
			YConnectTokenResponse respToken = this.yconToken.generate(url, clientId, clientSecret, code, request);
			model.addAttribute("yctoken", respToken);
		}

		String authUrl = this.yconOpenIdConf.makeAuthUrl(respOpenId, request, clientId, nonce, this.max_age);
		model.addAttribute("authUrl", authUrl);
		model.addAttribute("nonce", nonce);
		model.addAttribute("clientid", clientId);
		model.addAttribute("secret", clientSecret);
	}
	// }}}

}
