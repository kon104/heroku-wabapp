package com.herokuapp.kon104.webapp.util;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Http Request Utility
 */
@Component
public class HttpRequestUtility
{
	private final RequestMappingHandlerMapping rmhm;

	// {{{ public HttpRequestUtility(RequestMappingHandlerMapping rmhm)
	public HttpRequestUtility(RequestMappingHandlerMapping rmhm)
	{
		this.rmhm = rmhm;
	}
	// }}}

	// {{{ public String getURL(HttpServletRequest request)
	public String getURL(HttpServletRequest request)
	{
		String url = request.getRequestURL().toString();
		return url;
	}
	// }}}

	// {{{ public String getDomainURL(HttpServletRequest request)
	public String getDomainURL(HttpServletRequest request)
	{
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		int startIdxUri = url.lastIndexOf(uri);
		String domainUrl = url.substring(0, startIdxUri);

		return domainUrl;
	}
	// }}}

	// {{{ public String getDomain(HttpServletRequest request)
	public String getDomain(HttpServletRequest request)
	{
		String domainUrl = this.getDomainURL(request);
		final String delimiter = "://";
		int startIdxDomain = domainUrl.indexOf(delimiter) + delimiter.length();
		String domain = domainUrl.substring(startIdxDomain);

		return domain;
	}
	// }}}

	// {{{ public List<String> collectMappingPaths()
	public List<String> collectMappingPaths()
	{
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
		List<String> paths = new ArrayList<>();
		for (Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
			paths.addAll((entry.getKey().getPatternsCondition().getPatterns()));
		}
		return paths;
	}
	// }}}

}
