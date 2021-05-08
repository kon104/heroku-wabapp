package com.herokuapp.kon104.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.herokuapp.kon104.webapp.domain.StockMoveAvgService;

/**
 * Private Apps Area
 *
 */
@Controller
@RequestMapping("/private")
public class PrivateController
{

	@Autowired
	private StockMoveAvgService moveavg;

	// {{{ public String index()
	@GetMapping("/")
	public String index()
	{
		return "private/index";
	}
	// }}}

	// {{{ public String stockMoveAvg(Model model)
	@GetMapping("/stock/moveavg/")
	public String stockMoveAvg(Model model)
	{
		model.addAttribute("moveavgs", moveavg.getMoveAvg());
		return "private/stock/moveavg/index";
	}
	// }}}

	// Section of Studying

	// {{{ public String studyEnglish()
	@GetMapping("/study/english/")
	public String studyEnglish()
	{
		return "private/study/english/index";
	}
	// }}}

	// {{{ public String studyWordbook()
	@GetMapping("/study/wordbook/")
	public String studyWordbook()
	{
		return "private/study/wordbook/index";
	}
	// }}}

	// Section of Sample

	// {{{ public String studyWordbook()
	@GetMapping("/sample/turn-jp/")
	public String sampleTrunjp()
	{
		return "private/sample/turn-jp/index";
	}
	// }}}

}
