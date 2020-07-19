package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
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

	// {{{ public String getCopyrightYear()
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
