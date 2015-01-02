package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.mta.javacourse.model.Stock;
import com.mta.javacourse.model.portfolio;

/**
 * class that adds values into stock members
 * @author sharon
 *
 */

public class PortfolioService  {

	/**
	 * this function creates a portfolio
	 * @return
	 */

	public portfolio getPortfolio(){
		portfolio myPortfolio = new portfolio();

		myPortfolio.setTitle("<b>Exercise 08 - Portfolio</b>");
		myPortfolio.setBalance(10000);

		Calendar cal = new GregorianCalendar(2014,11,15);
		java.util.Date date = cal.getTime();	

		Stock myFirstStock = new Stock("PIH", 10f, 8.5f, date);
		Stock mySecondStock = new Stock("AAL", 30f, 25.5f, date);
		Stock myThirdStock = new Stock("CAAS", 20f, 15.5f, date);

		myPortfolio.addStock(myFirstStock);
		myPortfolio.addStock(mySecondStock);
		myPortfolio.addStock(myThirdStock);

		myPortfolio.buyStock("PIH",20);
		myPortfolio.buyStock("AAL",30);
		myPortfolio.buyStock("CAAS",40);

		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");


		return myPortfolio;

	}
}


