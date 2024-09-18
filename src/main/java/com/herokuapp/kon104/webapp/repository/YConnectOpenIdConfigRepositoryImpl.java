package com.herokuapp.kon104.webapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import com.herokuapp.kon104.webapp.entity.YConnectOpenIdConfigResponse;

/**
 * YConnect OpenID Configuration Repository Implement Class
 */
@Repository
public class YConnectOpenIdConfigRepositoryImpl implements YConnectOpenIdConfigRepository
{
	private final RestTemplate restTemplate;

	// {{{ public YConnectOpenIdConfigRepositoryImpl(RestTemplate restTemplate)
	public YConnectOpenIdConfigRepositoryImpl(RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}
	// }}}

	// {{{ public YConnectOpenIdConfigResponse discovery()
	@Override
	public YConnectOpenIdConfigResponse discovery()
	{
		YConnectOpenIdConfigResponse resp = this.restTemplate.getForObject(URL, YConnectOpenIdConfigResponse.class);
		return resp;
	}
	// }}}

}
