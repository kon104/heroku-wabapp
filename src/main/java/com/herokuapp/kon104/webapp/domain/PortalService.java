package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * Portal Service
 */
@Service
public class PortalService
{
	@Autowired
	private HttpRequestUtility hrUtil;

	// {{{ public String getDomain(HttpServletRequest request)
	public String getDomain(HttpServletRequest request)
	{
		String domain = hrUtil.getDomain(request);
		return domain;
	}
	// }}}

}
