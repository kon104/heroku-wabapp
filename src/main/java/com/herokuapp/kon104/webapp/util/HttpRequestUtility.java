package com.herokuapp.kon104.webapp.util;

import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * Http Request Utility
 */
@Component
public class HttpRequestUtility
{

	// {{{ public String getDomainURL(HttpServletRequest request)
	public String getDomainURL(HttpServletRequest request)
	{
		return request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf(request.getRequestURI()));
	}
	// }}}

}
