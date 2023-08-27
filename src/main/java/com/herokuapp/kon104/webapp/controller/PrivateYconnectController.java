package com.herokuapp.kon104.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.service.YConnectService;

/**
 *
 * Private Apps Area: Private YConnect Controller
 *
 */
@Controller
@RequestMapping("/private/yconnect")
public class PrivateYconnectController
{
	private YConnectService ycon;

	// {{{ public PrivateYconnectController(...)
	public PrivateYconnectController(YConnectService ycon)
	{
		this.ycon = ycon;
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
		this.ycon.v2(model, request,
			mode, clientId, clientSecret,
			nonce, code,
			access_token, token_type, refresh_token, expires_in, id_token);

		return "private/yconnect/v2/index";
	}
	// }}}

}
