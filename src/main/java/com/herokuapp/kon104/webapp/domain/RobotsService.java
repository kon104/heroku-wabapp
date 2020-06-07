package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	// {{{ public String getRobotsText(HttpServletRequest request)
	public String getRobotsText(HttpServletRequest request)
	{
		String domain = hrUtil.getDomainURL(request);

		StringBuilder sb = new StringBuilder();
		sb.append("User-agent: *\n");
		sb.append(String.format("Sitemap: %s/sitemap.xml\n", domain));
		sb.append("Disallow:\n");

		return sb.toString();
	}
	// }}}

}
