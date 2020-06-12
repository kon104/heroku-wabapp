package com.herokuapp.kon104.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.SiteMapService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * Site Map Controller
 */
//	@RestController
@Controller
@RequestMapping("/")
public class SiteMapController
{
	@Autowired
	private SiteMapService service;

	// {{{ public String sitemap(HttpServletRequest request)
	@GetMapping(value = "/sitemap.xml", produces = MediaType.TEXT_XML_VALUE)
	public String sitemap(HttpServletRequest request, Model model)
	{
		model.addAttribute("sitemaps", service.getSiteMap(request));
		return "portal/sitemap";
	}
	// }}}

}
