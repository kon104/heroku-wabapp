package com.herokuapp.kon104.webapp.repository;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.SiteMapRecord;

/**
 * Site Map Repository Interface
 */
public interface SiteMapRepository
{
	public List<SiteMapRecord> getSiteMap(HttpServletRequest request);
}
