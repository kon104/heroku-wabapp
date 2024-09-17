package com.herokuapp.kon104.webapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.herokuapp.kon104.webapp.entity.YConnectOpenIdConfigResponse;

@SpringBootTest
public class YConnectOpenIdConfigRepositoryTest
{
	private final YConnectOpenIdConfigRepository target;

	// {{{ public YConnectOpenIdConfigRepositoryTest(YConnectOpenIdConfigRepository target)
	@Autowired
	public YConnectOpenIdConfigRepositoryTest(YConnectOpenIdConfigRepository target)
	{
		this.target = target;
	}
	// }}}

	// {{{ public void discoveryTest()
	@Test
	public void discoveryTest()
	{
		YConnectOpenIdConfigResponse resp = this.target.discovery();
		assertThat(resp.getIssuer()).isEqualTo("https://auth.login.yahoo.co.jp/yconnect/v2");
		assertThat(resp.getAuthorization_endpoint()).isEqualTo("https://auth.login.yahoo.co.jp/yconnect/v2/authorization");
		assertThat(resp.getToken_endpoint()).isEqualTo("https://auth.login.yahoo.co.jp/yconnect/v2/token");
	}
	// }}}

}
