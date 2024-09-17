package com.herokuapp.kon104.webapp.repository;

import java.util.List;
import com.herokuapp.kon104.webapp.entity.StockMoveAvgRecord;

/**
 * Stock Move Avg Repository Interface
 */
public interface StockMoveAvgRepository
{
	public List<StockMoveAvgRecord> getMoveAvg();
}
