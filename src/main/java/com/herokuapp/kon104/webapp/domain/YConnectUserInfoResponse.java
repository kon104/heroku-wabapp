package com.herokuapp.kon104.webapp.domain;

public class YConnectUserInfoResponse
{
	public String sub;
	public String name;
	public String given_name;
	public String family_name;
	public String gender;
	public String zoneinfo;
	public String locale;
	public String birthdate;
	public String nickname;
	public String picture;
	public String email;
	public String email_verified;

	public YConnectUserInfoResponse()
	{
	}

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
