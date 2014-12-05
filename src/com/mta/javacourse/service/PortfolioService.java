package com.mta.javacourse.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.Stock;
import com.mta.javacourse.model.portfolio;

public class PortfolioService  {
    

	public portfolio getPortfolio(){
		
		Calendar cal = Calendar.getInstance();
		cal.set (2014, 10, 15, 0, 0, 0);
		Date date = cal.getTime();
		
		
		portfolio myPortfolio = new portfolio();
		Stock myFirstStock = new Stock();
		Stock mySecondStock = new Stock();
		Stock myThirdStock = new Stock();
		
		
		myFirstStock.setSymbol("PIH");
		mySecondStock.setSymbol("AAL");
		myThirdStock.setSymbol("CAAS");
		
		myFirstStock.setAsk((float) 12.4);
		mySecondStock.setAsk((float) 5.5);
		myThirdStock.setAsk((float) 31.5);
		
		myFirstStock.setBid((float) 13.1);
		mySecondStock.setBid((float) 5.78);
		myThirdStock.setBid((float) 31.2);
		

		myFirstStock.setDate(date);
		mySecondStock.setDate(date);
		myThirdStock.setDate(date);
		
		myPortfolio.addStock(myFirstStock);
		myPortfolio.addStock(mySecondStock);
		myPortfolio.addStock(myThirdStock);
		
		return myPortfolio;
	}
}

		
		
