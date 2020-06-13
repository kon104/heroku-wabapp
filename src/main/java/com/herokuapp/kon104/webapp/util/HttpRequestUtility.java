package com.herokuapp.kon104.webapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

/**
 * Http Request Utility
 */
@Component
public class HttpRequestUtility
{
	@Autowired
	private RequestMappingHandlerMapping rmhm;

	// {{{ public String getDomainURL(HttpServletRequest request)
	public String getDomainURL(HttpServletRequest request)
	{
		return request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf(request.getRequestURI()));
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
