package com.herokuapp.kon104.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.herokuapp.kon104.webapp.domain.YConnectIdTokenService;
import com.herokuapp.kon104.webapp.domain.YConnectOpenIdConfigResponse;
import com.herokuapp.kon104.webapp.domain.YConnectTokenResponse;
import com.herokuapp.kon104.webapp.domain.YConnectTokenService;
import com.herokuapp.kon104.webapp.domain.YConnectUserInfoResponse;
import com.herokuapp.kon104.webapp.domain.YConnectUserInfoService;
import com.herokuapp.kon104.webapp.service.YConnectOpenIdConfigService;

/**
 *
 * Private Apps Area: Private YConnect Controller
 *
 */
@Controller
@RequestMapping("/private/yconnect")
public class PrivateYconnectController
{
	private YConnectOpenIdConfigService yconOpenIdConf;
	private YConnectTokenService yconToken;
	private YConnectUserInfoService yconUserInfo;
	private YConnectIdTokenService yconIdToken;
	private HttpSession session;

	public static final int max_age = 3600;

	// {{{ public PrivateYconnectController(...)
	public PrivateYconnectController(
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

	// {{{ public String indexV2(...)
	@RequestMapping(value = "/v2/", method = {RequestMethod.GET, RequestMethod.POST})
	public String indexV2(
			HttpServletRequest request,
			@RequestParam(name = "mode", required = false) String mode,
			@RequestParam(name = "clientid", required = false) String clientId,
			@RequestParam(name = "secret", required = false) String clientSecret,
			@RequestParam(name = "nonce", required = false) String nonce,
			@RequestParam(name = "code", required = false) String code,
			@RequestParam(name = "access_token", required = false) String access_token,
			@RequestParam(name = "token_type", required = false) String token_type,
			@RequestParam(name = "refresh_token", required = false) String refresh_token,
			@RequestParam(name = "expires_in", required = false) String expires_in,
			@RequestParam(name = "id_token", required = false) String id_token,
			Model model)
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
			this.yconToken.addModel(respToken, model);
			String issuer = respOpenId.getIssuer();
			Map<String, Boolean> result = this.yconIdToken.verify(id_token, issuer, clientId, nonce, access_token, this.max_age);
			model.addAttribute("verify6", result.get("verify6"));
			model.addAttribute("verify7", result.get("verify7"));
			model.addAttribute("verify8", result.get("verify8"));
			model.addAttribute("verify9", result.get("verify9"));
			model.addAttribute("verify10", result.get("verify10"));
			model.addAttribute("verify11", result.get("verify11"));
			model.addAttribute("verify12", result.get("verify12"));
			model.addAttribute("verify13", result.get("verify13"));
			model.addAttribute("verify14", result.get("verify14"));
		} else
		if ("userinfo".equals(mode) == true) {
			model.addAttribute("code", code);
			String url = respOpenId.getUserinfo_endpoint();
			YConnectUserInfoResponse respUser = this.yconUserInfo.getAttribute(url, access_token);
			this.yconUserInfo.addModel(respUser, model);
			YConnectTokenResponse respToken = new YConnectTokenResponse(access_token, token_type, refresh_token, expires_in, id_token);
			this.yconToken.addModel(respToken, model);
		} else
		if (code != null) {
			nonce = (String)this.session.getAttribute("nonce");
			clientId = (String)this.session.getAttribute("clientid");
			clientSecret = (String)this.session.getAttribute("secret");
			model.addAttribute("code", code);
			String url = respOpenId.getToken_endpoint();
			YConnectTokenResponse respToken = this.yconToken.generate(url, clientId, clientSecret, code, request);
			this.yconToken.addModel(respToken, model);
		}

		String authUrl = this.yconOpenIdConf.makeAuthUrl(respOpenId, request, clientId, nonce, this.max_age);
		model.addAttribute("authUrl", authUrl);
		model.addAttribute("nonce", nonce);
		model.addAttribute("clientid", clientId);
		model.addAttribute("secret", clientSecret);

		return "private/yconnect/v2/index";
	}
	// }}}

}
