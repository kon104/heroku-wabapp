package com.herokuapp.kon104.webapp.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMoveAvgRecord
{
	private String code;
	private String company;
	private String url;

	// {{{ public StockMoveAvgRecord(String code, String company, String url)
	public StockMoveAvgRecord(String code, String company, String url)
	{
		this.code = code;
		this.company = company;
		this.url = url;
	}
	// }}}

	// {{{ public String toString()
	@Override
	public String toString()
	{
		return "code=[" + code + "]\n"
			+ "company=[" + company + "]\n"
			+ "url=[" + url + "]";
	}
	// }}}

}
