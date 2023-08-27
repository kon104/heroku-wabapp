package com.herokuapp.kon104.webapp.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.herokuapp.kon104.webapp.domain.StockMoveAvgProperties;
import com.herokuapp.kon104.webapp.domain.StockMoveAvgRecord;

/**
 * Stock Move Avg Service Implement Class
 */
@Service
public class StockMoveAvgServiceImpl implements StockMoveAvgService
{

	private StockMoveAvgProperties properties;

	// {{{ public StockMoveAvgServiceImpl(StockMoveAvgProperties properties)
	public StockMoveAvgServiceImpl(StockMoveAvgProperties properties)
	{
		this.properties = properties;
	}
	// }}}

	// {{{ public List<StockMoveAvgRecord> getMoveAvg()
	@Override
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
