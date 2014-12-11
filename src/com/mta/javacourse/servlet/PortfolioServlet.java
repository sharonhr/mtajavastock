package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tools.ant.taskdefs.Sync.MyCopy;
import com.mta.javacourse.model.Stock;
import com.mta.javacourse.model.portfolio;
import com.mta.javacourse.service.PortfolioService;

@SuppressWarnings("serial")

/**
 * this class shows portfolio as html
 * @author sharon
 *
 */
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException { 

		PortfolioService portfolioService = new PortfolioService();
		portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();
		resp.setContentType("Text/html");
		
		portfolio portfolio2 = new portfolio(portfolio);
		portfolio2.setTitle("Portfolio 2");

		resp.getWriter().println(portfolio.getHtmlString());
		resp.getWriter().println("</br>");
		resp.getWriter().println(portfolio2.getHtmlString());
		resp.getWriter().println("</br>");

		portfolio.removeStock(portfolio.getStocks()[0]);

		resp.getWriter().println("without the first stock in portfolio1" + "</br>");
		resp.getWriter().println(portfolio.getHtmlString() + "</br>");
		resp.getWriter().println(portfolio2.getHtmlString() + "</br>");

		portfolio2.getStocks()[2].setBid(55.55f);

		resp.getWriter().println("bid value of portfolio2 is 55.55:" + "</br>");
		resp.getWriter().println(portfolio.getHtmlString() + "</br>");
		resp.getWriter().println();
		resp.getWriter().println(portfolio2.getHtmlString());

	}
}

