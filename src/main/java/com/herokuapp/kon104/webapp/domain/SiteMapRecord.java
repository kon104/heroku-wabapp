package com.herokuapp.kon104.webapp.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteMapRecord
{
	private String loc;
	private Date lastmod;
	private String changefreq;
	private double priority;

	public static final String FREQ_YEARLY = "yearly";

	// {{{ public SiteMapRecord(String loc, Date lastmod, String changefreq, double priority)
	public SiteMapRecord(String loc, Date lastmod, String changefreq, double priority)
	{
		this.loc = loc;
		this.lastmod = lastmod;
		this.changefreq = changefreq;
		this.priority = priority;
	}
	// }}}

	// {{{ public String toString()
	@Override
	public String toString()
	{
		return "loc=[" + this.loc + "]\n"
			+ "lastmod=[" + this.lastmod + "]\n"
			+ "changefreq=[" + this.changefreq + "]\n"
			+ "priority=[" + this.priority + "]";
	}
	// }}}

}
