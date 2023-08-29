package com.herokuapp.kon104.webapp.util;

// --------------------------------------------------
// This class is not working well at 2023.08.29
// --------------------------------------------------

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor
{

	// {{{ public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException
	{
		Logger logger = LoggerFactory.getLogger(RestTemplateLoggingInterceptor.class.getName());
		this.logRequest(logger, request, body);
		ClientHttpResponse response = execution.execute(request, body);
		this.logResponse(logger, response);

		return response;
	}
	// }}}

	// {{{ private void logRequest(Logger logger, HttpRequest request, byte[] body) throws IOException
	private void logRequest(Logger logger, HttpRequest request, byte[] body) throws IOException
	{
		logger.info("HTTP Request >>> URI > [{}], Method > [{}], Headers > [{}], Body > [{}]",
			request.getURI(), request.getMethod(), request.getHeaders(), new String(body, "UTF-8"));
	}
	// }}}

	// {{{ private void logResponse(Logger logger, ClientHttpResponse response) throws IOException
	private void logResponse(Logger logger, ClientHttpResponse response) throws IOException
	{
		InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
		BufferedReader bufferedReader = new BufferedReader(isr);

		// ---> Notice: maybe it can't go back to the first position on StringBuffer,
		//              so StreamTemplate can't read a stream.
		/*
		String line = bufferedReader.readLine();
		StringBuilder inputStringBuilder = new StringBuilder();
		while (line != null) {
			inputStringBuilder.append(line);
			inputStringBuilder.append('\n');
			line = bufferedReader.readLine();
		}
		String body = inputStringBuilder.toString();
		*/
		// <--- Notice

		String body = null;
		logger.info("HTTP Response >>> Status > [{} {}], Headers > [{}], Body > [{}]",
			response.getStatusCode(), response.getStatusText(), response.getHeaders(), body);
	}
	// }}}

}

