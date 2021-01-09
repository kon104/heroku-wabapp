package com.herokuapp.kon104.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Private Apps Area
 *
 */
@Controller
@RequestMapping("/private")
public class PrivateController
{

	// {{{ public String index(Model model)
	@GetMapping("/")
	public String index()
	{
		return "private/index";
	}
	// }}}

	// {{{ public String stockMoveAvg()
	@GetMapping("/stock/moveavg/")
	public String stockMoveAvg()
	{
		return "private/stock/moveavg";
	}
	// }}}

}
