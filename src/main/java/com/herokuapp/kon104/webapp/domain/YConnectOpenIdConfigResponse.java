package com.herokuapp.kon104.webapp.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YConnectOpenIdConfigResponse
{
	private String issuer;
	private String authorization_endpoint;
	private String token_endpoint;
	private String userinfo_endpoint;
	private String jwks_uri;

	// {{{ public YConnectOpenIdConfigResponse()
	public YConnectOpenIdConfigResponse()
	{
	}
	// }}}

	// {{{ public String toString()
	@Override
	public String toString() {
		return "issuer=[" + this.issuer + "]\n"
			+ "authorization_endpoint=[" + this.authorization_endpoint + "]\n"
			+ "token_endpoint=[" + this.token_endpoint + "]\n"
			+ "userinfo_endpoint=[" + this.userinfo_endpoint + "]\n"
			+ "jwks_uri=[" + this.jwks_uri + "]";
	}
	// }}}

}
