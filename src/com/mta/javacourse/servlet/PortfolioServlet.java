package com.mta.javacourse.servlet;

import com.mta.javacourse.dto.PortfolioDto;
import com.mta.javacourse.dto.PortfolioTotalStatus;
import com.mta.javacourse.model.StockStatus;

import exception.BalanceException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PortfolioServlet extends AbstractAlgoServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
		
		PortfolioTotalStatus[] totalStatus = null;
		try {
			totalStatus = portfolioService.getPortfolioTotalStatus();
		} catch (BalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StockStatus[] stockStatusArray = null;
		try {
			stockStatusArray = portfolioService.getPortfolio().getStocks();
		} catch (BalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<StockStatus> stockStatusList = new ArrayList<>();
		for (StockStatus ss : stockStatusArray) {
			if(ss != null)
				stockStatusList.add(ss);
		}
		
		PortfolioDto pDto = new PortfolioDto();
		try {
			pDto.setTitle(portfolioService.getPortfolio().getTitle());
		} catch (BalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pDto.setTotalStatus(totalStatus);
		pDto.setStockTable(stockStatusList);
		resp.getWriter().print(withNullObjects().toJson(pDto));
	}
}
