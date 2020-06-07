package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

/**
 * Site Map Service
 */
@Service
public class SiteMapService
{

	@Autowired
	private RequestMappingHandlerMapping rmhm;

	// {{{ public String getSiteMapXml(HttpServletRequest request)
	public String getSiteMapXml(HttpServletRequest request)
	{
		String domain = this.scoopDomainFromURL(request);
		List<String> urls = this.collectMappingPaths();
		String xml = this.buildSiteMapXml(domain, urls);

		return xml;
	}
	// }}}

	// {{{ private List<String> collectMappingPaths()
	private List<String> collectMappingPaths()
	{
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
		List<String> urls = new ArrayList<>();
		for (Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
			urls.addAll((entry.getKey().getPatternsCondition().getPatterns()));
		}
		return urls;
	}
	// }}}

	// {{{ private String scoopDomainFromURL(HttpServletRequest request)
	private	String scoopDomainFromURL(HttpServletRequest request)
	{
		return request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf(request.getRequestURI()));
	}
	// }}}

	// {{{ private String buildSiteMapXml(String domain, List<String> urls)
	private String buildSiteMapXml(String domain, List<String> urls)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");

		for (String url : urls) {
			double ver = 0.5;
			if (url.equals("/")) {
				ver = 1;
			} else
			if (url.equals("/error")) {
				continue;
			}
			sb.append("<url>");
			sb.append(String.format("<loc>%s%s</loc>", domain, url));
			sb.append("<lastmod>2020-06-06</lastmod>");
			sb.append("<changefreq>yearly</changefreq>");
			sb.append(String.format("<priority>%.1f</priority>", ver));
			sb.append("</url>");
		}

		sb.append("</urlset>");

		return sb.toString();
	}
	// }}}

}
