package com.herokuapp.kon104.webapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.assertj.core.api.Assertions.assertThat;
import com.herokuapp.kon104.webapp.domain.YConnectOpenIdConfigResponse;

@SpringBootTest
public class YConnectOpenIdConfigServiceTest
{
	private final YConnectOpenIdConfigService yconnectOpenIdConfigService;

	// {{{ public YConnectOpenIdConfigServiceTest(YConnectOpenIdConfigService yconnectOpenIdConfigService)
	@Autowired
	public YConnectOpenIdConfigServiceTest(YConnectOpenIdConfigService yconnectOpenIdConfigService)
	{
		this.yconnectOpenIdConfigService = yconnectOpenIdConfigService;
	}
	// }}}

	// {{{ public void discoveryTest()
	@Test
	public void discoveryTest()
	{
		YConnectOpenIdConfigResponse resp = this.yconnectOpenIdConfigService.discovery();
		assertThat(resp.getIssuer()).isEqualTo("https://auth.login.yahoo.co.jp/yconnect/v2");
		assertThat(resp.getAuthorization_endpoint()).isEqualTo("https://auth.login.yahoo.co.jp/yconnect/v2/authorization");
		assertThat(resp.getToken_endpoint()).isEqualTo("https://auth.login.yahoo.co.jp/yconnect/v2/token");
	}
	// }}}

	// {{{ public void generateNonceTest()
	@Test
	public void generateNonceTest()
	{
		String nonce = this.yconnectOpenIdConfigService.generateNonce();
		assertThat(nonce).containsOnlyDigits();
	}
	// }}}

	// {{{ public void makeAuthUrlTest()
	@Test
	public void makeAuthUrlTest()
	{
		YConnectOpenIdConfigResponse resp = this.yconnectOpenIdConfigService.discovery();
		MockHttpServletRequest request = new MockHttpServletRequest();
		String clientId = "abc";
		String nonce = "xyz";
		int max_age = 123;

		String answer = resp.getAuthorization_endpoint()
			+ "?response_type=code"
			+ "&client_id=" + clientId
			+ "&redirect_uri=" + (new String(request.getRequestURL())).replace(":", "%3A").replace("/", "%2F")
			+ "&scope=openid+profile+email+address"
			+ "&nonce=" + nonce
			+ "&max_age=" + String.valueOf(max_age);

		String url = this.yconnectOpenIdConfigService.makeAuthUrl(resp, request, clientId, nonce, max_age);

		assertThat(url).isEqualTo(answer);
	}
	// }}}

}
