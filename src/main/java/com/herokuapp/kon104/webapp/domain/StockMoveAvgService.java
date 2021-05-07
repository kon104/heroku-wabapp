package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Stock Move Avg Service
 */
@Service
public class StockMoveAvgService
{

	@Autowired
	private StockMoveAvgProperties properties;

	// {{{ public List<StockMoveAvgRecord> getMoveAvg()
	public List<StockMoveAvgRecord> getMoveAvg()
	{
		List<StockMoveAvgRecord> moveavgs = this.buildMoveAvg();
		return moveavgs;
	}
	// }}}

	// {{{ private List<StockMoveAvgRecord> buildMoveAvg()
	private List<StockMoveAvgRecord> buildMoveAvg()
	{
		List<StockMoveAvgRecord> moveavgs = new ArrayList<StockMoveAvgRecord>();

		for (String code: properties.getCodes()) {
			moveavgs.add(new StockMoveAvgRecord(code,
				properties.getCompanies().get(code), properties.getUrls().get(code)));
		}

		return moveavgs;
	}
	// }}}

}
