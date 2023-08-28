package com.herokuapp.kon104.webapp.repository;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method; 
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import com.herokuapp.kon104.webapp.domain.YConnectTokenResponse;
import com.herokuapp.kon104.webapp.testutil.TestUtilApiResponse;

@SpringBootTest
public class YConnectTokenRepositoryTest
{
	private final YConnectTokenRepository target;

	@Autowired
	private RestTemplate restTemplate;

	// {{{ public YConnectOpenIdConfigRepositoryTest(YConnectOpenIdConfigRepository target)
	@Autowired
	public YConnectTokenRepositoryTest(YConnectTokenRepository target)
	{
		this.target = target;
	}
	// }}}

	// {{{ public void generateTest()
	@Test
	public void generateTest()
	{
		// create parameters for target method.
		String url = "http://localhost/yconnect/v2/token";
		String clientId = "my-clientid";
		String clientSecret = "my-client-secret";
		String code = "helloworld";
		MockHttpServletRequest request = new MockHttpServletRequest();

		// create a response body of plain and object type.
		String filename = "TokenEndpoint.json";
		String body = TestUtilApiResponse.getString(filename);
		JsonNode json = TestUtilApiResponse.getJsonNode(filename);

		// bind a test response to a calling api in target method.
		MockRestServiceServer server = MockRestServiceServer.bindTo(this.restTemplate).build();
		server.expect(times(1), requestTo(url))
			.andExpect(method(HttpMethod.POST))
			.andRespond(withSuccess(body, MediaType.APPLICATION_JSON));

		// execute a test.
		YConnectTokenResponse actual = this.target.generate(url, clientId, clientSecret, code, request);

		// evaluate results.
		assertThat(actual.getAccess_token()).isEqualTo(json.get("access_token").asText());
		assertThat(actual.getToken_type()).isEqualTo(json.get("token_type").asText());
		assertThat(actual.getRefresh_token()).isEqualTo(json.get("refresh_token").asText());
		assertThat(actual.getExpires_in()).isEqualTo(json.get("expires_in").asText());
		assertThat(actual.getId_token()).isEqualTo(json.get("id_token").asText());
	}
	// }}}

}
