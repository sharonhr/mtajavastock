package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.taskdefs.Sync.MyCopy;

import com.mta.javacourse.model.Stock;
import com.mta.javacourse.model.portfolio;
import com.mta.javacourse.service.PortfolioService;

import exception.BalanceException;
import exception.PortfolioFullException;
import exception.StockAlreadyExistsException;
import exception.StockNotExistException;

@SuppressWarnings("serial")

/**
 * this class shows portfolio as html
 * @author sharon
 *
 */
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException { 
		resp.setContentType("text/html");
		try {
			PortfolioService portfolioService = new PortfolioService();
			portfolio portfolio = portfolioService.getPortfolio();
			resp.getWriter().println(portfolio.getHtmlString());
			Stock[] stocksStatus = portfolio.getStocksStatus();
			
		}catch(PortfolioFullException e) {
			resp.getWriter().println("Portfolio is full!");
		} catch(StockAlreadyExistsException e) {
			resp.getWriter().println("The stock already exists!");
	//	} catch (BalanceException e) {
		//	resp.getWriter().println("Not enough balance!");
		} catch (StockNotExistException e) {
			resp.getWriter().println("The stock does not exist!");
		
	}	
		/*PortfolioService portfolioService = new PortfolioService();
		portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocksStatus = portfolio.getStocksStatus();
		
		resp.setContentType("Text/html");
		resp.getWriter().println(portfolio.getHtmlString());*/

	}
}

