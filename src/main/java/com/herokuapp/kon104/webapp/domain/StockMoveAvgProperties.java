package com.herokuapp.kon104.webapp.domain;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "apps.private.stockma")
@Getter
@Setter
public class StockMoveAvgProperties
{
	private List<String> codes;
	private Map<String, String> companies;
	private Map<String, String> urls;

	// {{{ public String toString()
	@Override
	public String toString()
	{
		return "codes=[" + codes + "]\n"
			+ "companies=[" + companies + "]\n"
			+ "urls=[" + urls + "]";
	}
	// }}}

}
