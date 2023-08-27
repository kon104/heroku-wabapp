package com.herokuapp.kon104.webapp.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YConnectUserInfoResponse
{
	private String sub;
	private String name;
	private String given_name;
	private String family_name;
	private String gender;
	private String zoneinfo;
	private String locale;
	private String birthdate;
	private String nickname;
	private String picture;
	private String email;
	private String email_verified;

	// {{{ public YConnectUserInfoResponse()
	public YConnectUserInfoResponse()
	{
	}
	// }}}

	// {{{ public String toString()
	@Override
	public String toString() {
		return "sub=[" + this.sub + "]\n"
			+ "name=[" + this.name + "]\n"
			+ "given_name=[" + this.given_name + "]\n"
			+ "family_name=[" + this.family_name + "]\n"
			+ "gender=[" + this.gender + "]\n"
			+ "zoneinfo=[" + this.zoneinfo + "]\n"
			+ "locale=[" + this.locale + "]\n"
			+ "birthdate=[" + this.birthdate + "]\n"
			+ "nickname=[" + this.nickname + "]\n"
			+ "picture=[" + this.picture + "]\n"
			+ "email=[" + this.email + "]\n"
			+ "email_verified=[" + this.email_verified + "]\n";
	}
	// }}}

}
