package com.herokuapp.kon104.webapp.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.service.PortalService;
import com.herokuapp.kon104.webapp.service.RobotsService;
import com.herokuapp.kon104.webapp.service.SiteMapService;

/**
 * Portal Controller
 */
@Controller
public class PortalController
{
	private PortalService portal;
	private SiteMapService sitemap;
	private RobotsService robots;

	// {{{ public PortalController(PortalService portal, SiteMapService sitemap, RobotsService robots)
	public PortalController(PortalService portal, SiteMapService sitemap, RobotsService robots)
	{
		this.portal = portal;
		this.sitemap = sitemap;
		this.robots = robots;
	}
	// }}}

	// {{{ public String index(HttpServletRequest request, Model model)
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model)
	{
//		return "redirect:/printaddr/";

		model.addAttribute("domain", portal.getDomain(request));
		model.addAttribute("cpyear", portal.getCopyrightYear());
		return "portal/index";
	}
	// }}}

	// {{{ public String sitemap(HttpServletRequest request, Model model)
	@GetMapping(value = "/sitemap.xml", produces = MediaType.TEXT_XML_VALUE)
	public String sitemap(HttpServletRequest request, Model model)
	{
		model.addAttribute("sitemaps", sitemap.getSiteMap(request));
		return "portal/sitemap";
	}
	// }}}

	// {{{ public String robots(HttpServletRequest request, Model model)
	@GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
	public String robots(HttpServletRequest request, Model model)
	{
		model.addAttribute("sitemap_url", robots.getSiteMapUrl(request));
		return "portal/robots";
	}
	// }}}

}
