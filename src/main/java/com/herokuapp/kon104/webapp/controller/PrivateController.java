package com.herokuapp.kon104.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collections;
import java.util.List;
import com.herokuapp.kon104.webapp.domain.SentenceJpn2EngRecord;
import com.herokuapp.kon104.webapp.domain.StockMoveAvgService;
import com.herokuapp.kon104.webapp.domain.StudyEnglishService;

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

	@Autowired
	private StudyEnglishService studyeng;

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

	// Section of Studying

	// {{{ public String studyEnglish()
	@GetMapping("/study/english/")
	public String studyEnglish()
	{
		return "private/study/english/index";
	}
	// }}}

	// {{{ public String studyWordbook(Model model)
	@GetMapping("/study/wordbook/")
	public String studyWordbook(Model model)
	{
		List<SentenceJpn2EngRecord> list = this.studyeng.getList();
		Collections.shuffle(list);
		model.addAttribute("studyengs", list);
		return "private/study/wordbook/index";
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
