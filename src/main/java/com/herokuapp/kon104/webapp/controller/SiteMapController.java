package com.herokuapp.kon104.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.SiteMapService;

/**
 * Site Map Controller
 */
@RestController
public class SiteMapController
{
	@Autowired
	private SiteMapService service;

	// {{{ public String sitemap(HttpServletRequest request)
	@RequestMapping(value = "/sitemap.xml", produces = "text/xml")
	public String sitemap(HttpServletRequest request)
	{
		return service.getSiteMapXml(request);
	}
	// }}}

}
