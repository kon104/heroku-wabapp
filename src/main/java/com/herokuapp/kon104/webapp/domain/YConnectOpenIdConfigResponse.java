package com.herokuapp.kon104.webapp.domain;

public class YConnectOpenIdConfigResponse
{
	public String issuer;
	public String authorization_endpoint;
	public String token_endpoint;
	public String userinfo_endpoint;
	public String jwks_uri;

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
