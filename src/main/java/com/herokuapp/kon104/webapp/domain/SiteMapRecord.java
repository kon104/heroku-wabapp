package com.herokuapp.kon104.webapp.domain;

import java.util.Date;

public class SiteMapRecord
{
	public static final String FREQ_YEARLY = "yearly";

	public String loc;
	public Date lastmod;
	public String changefreq;
	public double priority;

	public SiteMapRecord(String loc, Date lastmod, String changefreq, double priority)
	{
		this.loc = loc;
		this.lastmod = lastmod;
		this.changefreq = changefreq;
		this.priority = priority;
	}
}
