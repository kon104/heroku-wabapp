package com.herokuapp.kon104.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collections;
import java.util.List;
import com.herokuapp.kon104.webapp.service.StockMoveAvgService;

/**
 * Private Apps Area
 *
 */
@Controller
@RequestMapping("/private")
public class PrivateController
{

	private StockMoveAvgService moveavg;

	// {{{ public PrivateController(StockMoveAvgService moveavg)
	public PrivateController(StockMoveAvgService moveavg)
	{
		this.moveavg = moveavg;
	}
	// }}}

	// top page

	// {{{ public String index()
	@GetMapping("/")
	public String index()
	{
		return "private/index";
	}
	// }}}

	// Section of Stock

	// {{{ public String stockMoveAvg(Model model)
	@GetMapping("/stock/moveavg/")
	public String stockMoveAvg(Model model)
	{
		model.addAttribute("moveavgs", this.moveavg.getMoveAvg());
		return "private/stock/moveavg/index";
	}
	// }}}

	// Section of sample for Turn.js

	// {{{ public String sampleTrunjs()
	@GetMapping("/sample/turn-js/")
	public String sampleTrunjs()
	{
		return "private/sample/turn-js/index";
	}
	// }}}

}
