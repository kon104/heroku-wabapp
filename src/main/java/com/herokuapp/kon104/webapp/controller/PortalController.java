package com.herokuapp.kon104.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.SiteMapService;

/**
 * Portal Controller
 */
@Controller
public class PortalController
{
	@Autowired
	private SiteMapService service;

	// {{{ public String index()
	@RequestMapping("/")
	public String index()
	{
		return "redirect:/printaddr/";
	}
	// }}}

	// {{{ public String sitemap(HttpServletRequest request)
	@GetMapping(value = "/sitemap.xml", produces = MediaType.TEXT_XML_VALUE)
	public String sitemap(HttpServletRequest request, Model model)
	{
		model.addAttribute("sitemaps", service.getSiteMap(request));
		return "portal/sitemap";
	}
	// }}}

}
