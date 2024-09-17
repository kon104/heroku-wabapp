package com.herokuapp.kon104.webapp.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import com.herokuapp.kon104.webapp.entity.StockMoveAvgProperties;
import com.herokuapp.kon104.webapp.entity.StockMoveAvgRecord;

/**
 * Stock Move Avg Repository Implement Class
 */
@Repository
public class StockMoveAvgRepositoryImpl implements StockMoveAvgRepository
{

	private StockMoveAvgProperties properties;

	// {{{ public StockMoveAvgRepositoryImpl(StockMoveAvgProperties properties)
	public StockMoveAvgRepositoryImpl(StockMoveAvgProperties properties)
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
