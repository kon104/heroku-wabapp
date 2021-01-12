package com.herokuapp.kon104.webapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
//	import com.herokuapp.kon104.webapp.util.HttpRequestUtility;

/**
 * Stock Move Avg Service
 */
@Service
public class StockMoveAvgService
{

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

		moveavgs.add(new StockMoveAvgRecord("4689", "Ｚホールディングス(株)",
			"https://docs.google.com/spreadsheets/d/1JlhOrm1CNbmj5sG8zpJ3s_JNOwjVHsluZQm33Ott4Uo/edit?usp=sharing"));
		moveavgs.add(new StockMoveAvgRecord("3402", "東レ(株)",
			"https://docs.google.com/spreadsheets/d/1dw7O2P3STQGvD0W4SSHcNCjanW8QyXWiQynatPpb9iY/edit?usp=sharing"));
		moveavgs.add(new StockMoveAvgRecord("4902", "コニカミノルタ(株)",
			"https://docs.google.com/spreadsheets/d/1oLnPNjUzxr_M8kpS1eLQzGpWN54DvYn0NaPdh5uazjU/edit?usp=sharing"));
		moveavgs.add(new StockMoveAvgRecord("5020", "ＥＮＥＯＳホールディングス(株)",
			"https://docs.google.com/spreadsheets/d/13fjFRLSyrm2rFpmTTMcBtDzFTK6poPoog1CRfuBr3H4/edit?usp=sharing"));
		moveavgs.add(new StockMoveAvgRecord("9434", "ソフトバンク(株)",
			"https://docs.google.com/spreadsheets/d/1bffURdA5Meo6g0LzZ6S_WI5trr2ORL_U-pfu6SAYxy0/edit?usp=sharing"));
		moveavgs.add(new StockMoveAvgRecord("9508", "九州電力(株)",
			"https://docs.google.com/spreadsheets/d/1vNVpON3ttYhjAtupwK3mn9-IrQImfzpkH3k77HHZPeE/edit?usp=sharing"));
		moveavgs.add(new StockMoveAvgRecord("9984", "ソフトバンクグループ(株)",
			"https://docs.google.com/spreadsheets/d/1I8-Zjs6MTKv-jxIhWQAEnLMtlXhzvWFIVkWQAOjOd5k/edit?usp=sharing"));

		return moveavgs;
	}
	// }}}

}
