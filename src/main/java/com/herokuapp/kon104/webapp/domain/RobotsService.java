package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * Robots Service
 */
@Service
public class RobotsService
{
	@Autowired
	private HttpRequestUtility hrUtil;

	// {{{ public String getSiteMapUrl(HttpServletRequest request)
	public String getSiteMapUrl(HttpServletRequest request)
	{
		String domain = hrUtil.getDomainURL(request);
		List<String> paths = hrUtil.collectMappingPaths();
		String url = this.buildSiteMapUrl(domain, paths);
		return url;
	}
	// }}}

	// {{{ private String buildSiteMapUrl(String domain, List<String> paths)
	private String buildSiteMapUrl(String domain, List<String> paths)
	{
		String url = null;
		for (String path : paths) {
			boolean match = path.endsWith("/sitemap.xml");
			if (match == true) {
				url = String.format("%s%s", domain, path);	
				break;
			}
		}
		return url;
	}
	// }}}

}
