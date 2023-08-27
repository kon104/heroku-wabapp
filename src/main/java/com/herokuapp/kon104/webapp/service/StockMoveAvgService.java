package com.herokuapp.kon104.webapp.service;

import java.util.List;
import com.herokuapp.kon104.webapp.domain.StockMoveAvgRecord;

/**
 * Stock Move Avg Service Interface
 */
public interface StockMoveAvgService
{
	public List<StockMoveAvgRecord> getMoveAvg();
}
