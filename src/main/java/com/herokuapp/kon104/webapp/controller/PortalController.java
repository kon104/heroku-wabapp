package com.herokuapp.kon104.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.PortalService;
import com.herokuapp.kon104.webapp.domain.RobotsService;
import com.herokuapp.kon104.webapp.domain.SiteMapService;

/**
 * Portal Controller
 */
@Controller
public class PortalController
{
	@Autowired
	private PortalService portal;

	@Autowired
	private SiteMapService sitemap;

	@Autowired
	private RobotsService robots;

	// {{{ public String index()
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model)
	{
//		return "redirect:/printaddr/";
		model.addAttribute("domain", portal.getDomain(request));
		return "portal/index";
	}
	// }}}

	// {{{ public String sitemap(HttpServletRequest request)
	@GetMapping(value = "/sitemap.xml", produces = MediaType.TEXT_XML_VALUE)
	public String sitemap(HttpServletRequest request, Model model)
	{
		model.addAttribute("sitemaps", sitemap.getSiteMap(request));
		return "portal/sitemap";
	}
	// }}}

	// {{{ public String robots(HttpServletRequest request)
	@GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
	public String robots(HttpServletRequest request, Model model)
	{
		model.addAttribute("sitemap_url", robots.getSiteMapUrl(request));
		return "portal/robots";
	}
	// }}}

}
