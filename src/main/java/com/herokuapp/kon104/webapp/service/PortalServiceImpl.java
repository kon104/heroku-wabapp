package com.herokuapp.kon104.webapp.service;

import org.springframework.stereotype.Service;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * Portal Service Implement Class
 */
@Service
public class PortalServiceImpl implements PortalService
{
	private HttpRequestUtility hrUtil;

	// {{{ public PortalServiceImpl(HttpRequestUtility hrUtil)
	public PortalServiceImpl(HttpRequestUtility hrUtil)
	{
		this.hrUtil = hrUtil;
	}
	// }}}

	// {{{ public String getDomain(HttpServletRequest request)
	@Override
	public String getDomain(HttpServletRequest request)
	{
		String domain = hrUtil.getDomain(request);
		return domain;
	}
	// }}}

	// {{{ public String getCopyrightYear()
	@Override
	public String getCopyrightYear()
	{
		final int START_YEAR = 2020;

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		String cpYear = String.valueOf(year);
		if (year > START_YEAR) {
			cpYear = String.valueOf(START_YEAR) + " - " + String.valueOf(year);
		}

		return cpYear;
	}
	// }}}

}
