package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;
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

		Calendar cal = Calendar.getInstance();
		cal.set (2014, 10, 15, 0, 0, 0);
		Date date = cal.getTime();

		Stock myFirstStock = new Stock("PIH", 12.4f, 13.1f, date);
		Stock mySecondStock = new Stock("AAL", 5.5f, 5.78f, date);
		Stock myThirdStock = new Stock("CAAS", 31.5f, 31.2f, date);

		myPortfolio.addStock(myFirstStock);
		myPortfolio.addStock(mySecondStock);
		myPortfolio.addStock(myThirdStock);

		return myPortfolio;

	}
}



