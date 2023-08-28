package com.herokuapp.kon104.webapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.assertj.core.api.Assertions.assertThat;
import com.herokuapp.kon104.webapp.domain.YConnectOpenIdConfigResponse;
import com.herokuapp.kon104.webapp.repository.YConnectOpenIdConfigRepository;

@SpringBootTest
public class YConnectOpenIdConfigServiceTest
{
	private final YConnectOpenIdConfigService target;
	private final YConnectOpenIdConfigRepository repository;

	// {{{ public YConnectOpenIdConfigServiceTest(YConnectOpenIdConfigService target, YConnectOpenIdConfigRepository repository)
	@Autowired
	public YConnectOpenIdConfigServiceTest(YConnectOpenIdConfigService target, YConnectOpenIdConfigRepository repository)
	{
		this.target = target;
		this.repository = repository;
	}
	// }}}

	// {{{ public void generateNonceTest()
	@Test
	public void generateNonceTest()
	{
		String nonce = this.target.generateNonce();
		assertThat(nonce).containsOnlyDigits();
	}
	// }}}

	// {{{ public void makeAuthUrlTest()
	@Test
	public void makeAuthUrlTest()
	{
		YConnectOpenIdConfigResponse resp = this.repository.discovery();
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

		String url = this.target.makeAuthUrl(resp, request, clientId, nonce, max_age);

		assertThat(url).isEqualTo(answer);
	}
	// }}}

}
