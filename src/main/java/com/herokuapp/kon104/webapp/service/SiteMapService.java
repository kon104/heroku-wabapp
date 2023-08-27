package com.herokuapp.kon104.webapp.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.SiteMapRecord;

/**
 * Site Map Service Interface
 */
public interface SiteMapService
{
	public List<SiteMapRecord> getSiteMap(HttpServletRequest request);
}
