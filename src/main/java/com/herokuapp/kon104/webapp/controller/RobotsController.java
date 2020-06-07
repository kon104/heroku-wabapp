package com.herokuapp.kon104.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.kon104.webapp.domain.RobotsService;

/**
 * Robots Controller
 */
@RestController
public class RobotsController
{
	@Autowired
	private RobotsService service;

	// {{{ public String robots(HttpServletRequest request)
	@RequestMapping(value = "/robots.txt", produces = "text/plain")
	public String robots(HttpServletRequest request)
	{
		return service.getRobotsText(request);
//		return "abc\nxyz";
	}
	// }}}

}
