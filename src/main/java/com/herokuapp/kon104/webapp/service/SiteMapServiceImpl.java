package com.herokuapp.kon104.webapp.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.SiteMapRecord;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * Site Map Service Implement Class
 */
@Service
public class SiteMapServiceImpl implements SiteMapService
{
	private HttpRequestUtility hrUtil;

	// {{{ public SiteMapServiceImpl(HttpRequestUtility hrUtil)
	public SiteMapServiceImpl(HttpRequestUtility hrUtil)
	{
		this.hrUtil = hrUtil;
	}
	// }}}

	// {{{ public List<SiteMapRecord> getSiteMap(HttpServletRequest request)
	@Override
	public List<SiteMapRecord> getSiteMap(HttpServletRequest request)
	{
		String domain = hrUtil.getDomainURL(request);
		List<String> paths = hrUtil.collectMappingPaths();
		List<SiteMapRecord> sitemaps = this.buildSiteMap(domain, paths);
		return sitemaps;
	}
	// }}}

	// {{{ private List<SiteMapRecord> buildSiteMap(String domain, List<String> paths)
	private List<SiteMapRecord> buildSiteMap(String domain, List<String> paths)
	{
		List<SiteMapRecord> sitemaps = new ArrayList<SiteMapRecord>();

		for (String path : paths) {
			double priority = 0.5;
			if (path.equals("/")) {
				priority = 1;
			} else
			if (path.equals("/error") ||
			    path.startsWith("/private/")) {
				continue;
			}
			String url = String.format("%s%s", domain, path);
			Date lastmod = new Date();
			String changefreq = SiteMapRecord.FREQ_YEARLY;
			sitemaps.add(new SiteMapRecord(url, lastmod, changefreq, priority));
		}

		return sitemaps;
	}
	// }}}

}
