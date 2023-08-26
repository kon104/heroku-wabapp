package com.herokuapp.kon104.webapp.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteMapRecord
{
	public static final String FREQ_YEARLY = "yearly";

	private String loc;
	private Date lastmod;
	private String changefreq;
	private double priority;

	// {{{ public SiteMapRecord(String loc, Date lastmod, String changefreq, double priority)
	public SiteMapRecord(String loc, Date lastmod, String changefreq, double priority)
	{
		this.loc = loc;
		this.lastmod = lastmod;
		this.changefreq = changefreq;
		this.priority = priority;
	}
	// }}}

}
