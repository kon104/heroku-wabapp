package com.herokuapp.kon104.webapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
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

	// {{{ public HttpEntity<MultiValueMap<String, String>> createHttpEntityWithBasicAuth(MultiValueMap<String, String> body, String clientId, String clientSecret)
	public HttpEntity<MultiValueMap<String, String>> createHttpEntityWithBasicAuth(MultiValueMap<String, String> body, String clientId, String clientSecret)
	{
		String credential = clientId + ":" + clientSecret;
		String auth = Base64.getEncoder().encodeToString(credential.getBytes(StandardCharsets.UTF_8));
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization","Basic " + auth);

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(body, header);

		return httpEntity;
	}
	// }}}

	// {{{ public HttpEntity<String> createHttpEntityWithBearerAuth(String access_token)
	public HttpEntity<String> createHttpEntityWithBearerAuth(String access_token)
	{
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization","Bearer " + access_token);

		HttpEntity<String> httpEntity = new HttpEntity<>(header);

		return httpEntity;
	}
	// }}}

}
