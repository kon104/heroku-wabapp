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
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * Site Map Service
 */
@Service
public class SiteMapService
{

	@Autowired
	private HttpRequestUtility hrUtil;

	@Autowired
	private RequestMappingHandlerMapping rmhm;

	// {{{ public String getSiteMapXml(HttpServletRequest request)
	public String getSiteMapXml(HttpServletRequest request)
	{
		String domain = hrUtil.getDomainURL(request);
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

	// {{{ private String buildSiteMapXml(String domain, List<String> urls)
	private String buildSiteMapXml(String domain, List<String> urls)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");

		for (String url : urls) {
			double ver = 0.5;
			if (url.equals("/")) {
				ver = 1;
			} else
			if (url.equals("/error")) {
				continue;
			}
			sb.append("<url>\n");
			sb.append(String.format("  <loc>%s%s</loc>\n", domain, url));
			sb.append("  <lastmod>2020-06-06</lastmod>\n");
			sb.append("  <changefreq>yearly</changefreq>\n");
			sb.append(String.format("\t\t<priority>%.1f</priority>\n", ver));
			sb.append("</url>\n");
		}

		sb.append("</urlset>");

		return sb.toString();
	}
	// }}}

}
