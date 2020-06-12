package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import java.util.ArrayList;
import java.util.Date;
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

	// {{{ public List<SiteMapRecord> getSiteMap(HttpServletRequest request)
	public List<SiteMapRecord> getSiteMap(HttpServletRequest request)
	{
		String domain = hrUtil.getDomainURL(request);
		List<String> urls = this.collectMappingPaths();
		List<SiteMapRecord> sitemaps = this.buildSiteMap(domain, urls);
		return sitemaps;
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

	// {{{ private List<SiteMapRecord> buildSiteMap(String domain, List<String> urls)
	private List<SiteMapRecord> buildSiteMap(String domain, List<String> urls)
	{
		List<SiteMapRecord> sitemaps = new ArrayList<SiteMapRecord>();

		for (String url : urls) {
			double priority = 0.5;
			if (url.equals("/")) {
				priority = 1;
//			} else
//			if (url.equals("/error")) {
//				continue;
			}
			url = String.format("%s%s", domain, url);
			Date lastmod = new Date();
			String changefreq = SiteMapRecord.FREQ_YEARLY;
			sitemaps.add(new SiteMapRecord(url, lastmod, changefreq, priority));
		}

		return sitemaps;
	}
	// }}}

}
