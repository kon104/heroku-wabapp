package com.herokuapp.kon104.webapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * Robots Service Implement Class
 */
@Service
public class RobotsServiceImpl implements RobotsService
{
	private final HttpRequestUtility hrUtil;

	// {{{ public RobotsServiceImpl(HttpRequestUtility hrUtil)
	public RobotsServiceImpl(HttpRequestUtility hrUtil)
	{
		this.hrUtil = hrUtil;
	}
	// }}}

	// {{{ public String getSiteMapUrl(HttpServletRequest request)
	@Override
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
