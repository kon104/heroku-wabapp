package com.herokuapp.kon104.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tradingview")
public class TradingViewController
{

	// {{{ public String index()
	@GetMapping("/")
	public String index(
		HttpServletRequest request,
		@RequestParam(name = "m", required = false) String market,
		@RequestParam(name = "c", required = false) String code,
		Model model)
	{
		if (market == null) {
			market = "BINANCE";
		}
		if (code == null) {
			code = "BTCUSDT";
		}
		model.addAttribute("market", market);
		model.addAttribute("code", code);
		return "tradingview/index";
	}
	// }}}

}
