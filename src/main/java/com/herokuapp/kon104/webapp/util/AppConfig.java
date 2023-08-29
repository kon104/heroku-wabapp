package com.herokuapp.kon104.webapp.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

@Configuration
public class AppConfig
{

	// {{{ public RestTemplate restTemplate() {
	/***********************************************
	 * This bean is to be able to inject the instance of RestTemplate in each Services.
	 **********************************************/
	@Bean
	public RestTemplate restTemplate() {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		RestTemplate restTemplate = restTemplateBuilder
//			.additionalInterceptors(new RestTemplateLoggingInterceptor())
			.build();
		return restTemplate;
	}
	// }}}

}
