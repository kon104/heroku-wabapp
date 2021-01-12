package com.herokuapp.kon104.webapp.domain;

import java.util.Date;

public class StockMoveAvgRecord
{
	public String code;
	public String company;
	public String url;

	public StockMoveAvgRecord(String code, String company, String url)
	{
		this.code = code;
		this.company = company;
		this.url = url;
	}
}
