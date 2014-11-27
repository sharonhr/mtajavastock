package com.mta.javacourse;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws IOException {
		
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		Stock stock3 = new Stock();
		
		Calendar cal = Calendar.getInstance();
		cal.set (2014, 10, 15, 0, 0, 0);
		Date date = cal.getTime();
		
		stock1.setSymbol("PIH");
		stock2.setSymbol("AAL");
		stock3.setSymbol("CAAS");
		
		stock1.setAsk((float) 12.4);
		stock2.setAsk((float) 5.5);
		stock3.setAsk((float) 31.5);
		
		stock1.setBid((float) 13.1);
		stock2.setBid((float) 5.78);
		stock3.setBid((float) 31.2);
		
		stock1.setDate(date);
		stock2.setDate(date);
		stock3.setDate(date);
		
		
		 resp.getWriter().println(stock1.getHtmlDescription()+ "<br>" + stock2.getHtmlDescription() + "<br>" + stock3.getHtmlDescription() );
	
	}
}
