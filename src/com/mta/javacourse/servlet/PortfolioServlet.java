package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.taskdefs.Sync.MyCopy;

import com.mta.javacourse.Stock;
import com.mta.javacourse.model.portfolio;
import com.mta.javacourse.service.PortfolioService;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
    	PortfolioService portfolioService = new PortfolioService();
    	portfolio portfolio = portfolioService.getPortfolio();
    	Stock[] stocks = portfolio.getStocks();

    	resp.setContentType("Text/html");
    	resp.getWriter().println(portfolio.getHtmlString());

	
    }
}

