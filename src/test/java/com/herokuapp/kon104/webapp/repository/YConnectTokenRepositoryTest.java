package com.herokuapp.kon104.webapp.repository;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		String url = "http://localhost/";
		String clientId = "aaaaa";
		String clientSecret = "bbbbb";
		String code = "ccccc";
		MockHttpServletRequest request = new MockHttpServletRequest();

		// create a response body of plain and object type.
		String body = this.makeBodyString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = null;
		try {
			json = mapper.readTree(body);
		} catch(Exception e) {
			e.printStackTrace();
		}

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

	// {{{ private String makeBodyString()
	private String makeBodyString()
	{
		String body = "{\"access_token\":\"A_JC7GQCAOo42XkufRISf3P-y8mMJL5Fp03QN2tNqbSuLMCxoeEq6P4L0jnnEosqi5kyv7AtsSsd66f6EKRWBVJhjhsEVyNQ2V7aZa9OcRQ4usQ4glRJZppVQ29Pm6ZbuQExISwWrIf-u63BdIA8dgdQq3KkkGCcrrECGNLzOCTnN5RQ3KXW4wukTPlQYqzlzRh20J5zhTOd43tKW-ZK5oy3jDPk5486w3aXvAYSqOcZdor0O9vmayAXHOcM1IPPncZkoaLtHCF1bpdCOApDmMEX4gmzj-HwGtV0QBevRzPOzR5Bi8Hiz-C5ABZG5Uh5s-QoY5VVoTkYDpw_4UfKVXKvtZ3gRMSj7hEW1dHxGlMvo14o2Q7ll8XMIlzxH4VdyvRG7ffpKNjIBYTajFVaacT9Tp04ilL_glHU-xlvpYFoJOXRbFD5_Y7aQoqXgdd3z4S4k9THuQFkFFPaUi_JdIFFksjMvSWaqvjqXCl5JJCT9bXEba2cowffA2izv4DrjhLuHYAhpAARMbqGxtpImV6TFshgDT_n59oKJdff0OQaifmUa3N3iq18_AYKXoXx85iABkqSYwBQrNamczXQGkLKApEs_p3UBw4avZeQgX-YuIXUX_5LgA5Lmc1SJPYEtuZzfR_eKyQzBwAgPJ4vECFSHgJhTNJxAUEEL1dLgGCu4NMA1ICHnXAsoocodAwSPwuVKZaqW6A1-Xus6MaiMDf5cCbtMojB84A8HJJz9jn5oI2e92mMV_hZjplTQSchJUdnrMDKT1bjV0OjyfTdxlLnVT3or9A-PrxLsAPpYpk7uAEMhMi6I0gs7uoWr7SU0ZN-GQK4rMqZmKWQmXNCz8DEy0bHJW0L__cxsEepMzWrTmK5M9rEx6glFSpfS0hZb3StuGLdk0ReSgEONHFbHwoV6IgfrH_c_fk7xyaU3HT_hAwlY2o93uOstbt9v6r8pzAxFH54C4Rl15-sEhBbppRKU7ZcIK8sTBe62udVr5CWRRr0ZUEfs70fMux1Yb_3olNIlsB9TyGFgDGv_n_a4PObwWvxSiNqj-yVHDSnbvSZTCgno3wR1F9G_3IWUuss6VAuV_XaeitkPnAjjJ2kLgrTiSb1thxvGgbekpvqRKuT_qHehAzTm-UECKj7F4itI2-wlXrohGofk1Aih7qdm6TgshNaAsEJ0tn9l6kPhqFVwIay5TchqV7CGx8x_QJoHP_gf-f4k0z7fO6i2PQfei3vOX4LDh8UShSYjVN7cLU7qNa5GpLnGWbhriaBPDQP1TEEM4M2-nUoRO82pxg3rbXuAR8CbQlCHK19GA2D_ihkZyaaIYH4yG78lbXiX1z3_r7y0YKjYpXhkWJ9mluxYeqNpyPn1S2fGxO1MLlDhElILJlcmEH8iknu86GrXMCXpsZyQBax3fgJMt-4UxszXzNt3g7HKJRXLblhCUA-otfCX9xfCgYRBtl_qKdG9wuUPqqu0VAa61kN9tJ3auWHC0N_PLTw6vsY8qnddidS3wFGePxZiuJsLkI4LlNi3sKyrITKYCTNSivrZa7usFB8rX9cdOEvhya0rkevky2qYCgfYvZ78OlbSPdfCqwWZ3cuPRr6Bp4q9JG-QCgq0ANOl_fe2qVufxP0edQOEI5SCYsmCQuCgeiDGs4_wd5k4nwJHkNk1S2VoodMW5XrhlCPfNULOgcI30xXFRgGHgkhrCxwMZyN40kCZwJtcHHwVEXY6joM-obRK_62xYUlmATVLA~1\",\"expires_in\":3600,\"id_token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IjBjYzE3NWI5YzBmMWI2YTgzMWMzOTllMjY5NzcyNjYxIn0.eyJhdWQiOlsiZGowMGFpWnBQV3B6Ym1SRmVrdEZkelIwZVNaelBXTnZibk4xYldWeWMyVmpjbVYwSm5nOU1HTS0iXSwiZXhwIjoxNjk1NjI0NDM0LCJpc3MiOiJodHRwczovL2F1dGgubG9naW4ueWFob28uY28uanAveWNvbm5lY3QvdjIiLCJpYXQiOjE2OTMyMDUyMzQsInN1YiI6IkJPUkdDT05YWVVQUU8yUjYyM0JTS0dUTlpRIiwiYW1yIjpbInN3ayJdLCJhdXRoX3RpbWUiOjE2OTMyMDQ3NjEsImF0X2hhc2giOiJqYXRVZ0hSVExrYWZ6dW5qM3J1c1R3In0.c_k7TtcebFcNcR10bvk3tyf878nbFs0CSfGcfQSaZfmWccAri7n4jcM_DTDomxzH2PB8K4j7zl5f8CeD3tRGBR_9Dbq89H_FJbD5wmRvj1ZoYAQlZv93_PTa2Jtsl3WS7aQlgZ6aYx6f1Gm6QgpOeaLe3Utr3UcQbCs02N-nrS_DK63QzHTjB7EO5MUVLrqA-F_rWj3uNHbNyt2LOLlNyoKjo33trac4cRDspie1IlmY--vfSiH8oLcELODJOiBScTn--7BCPvDuwKvGBATRdVkJLIJyc0OMlsFO0iS5Peii0WBKcq7cLS7T47kQoy22dSF830QhkXL58M8aMIBFVg\",\"token_type\":\"Bearer\",\"refresh_token\":\"A_JC7GQBAA-_Y5IRm_zQTdgoXxhPvUURYzJxDnDIh6s-H4XIMFR6mmna6lFLYE2Spl9DNlO1k4xPv39tl4VtciFcfGM88EIuNjLFrV6rPWy0ejvmU1su_ghpWwXazfm_L9LXeW_4E4vVFdzrW9kKgh2DIlBxK_244iv63mo8-Xyz5HfH~1\"}";
		return body;
	}
	// }}}

}
