package com.mta.javacourse.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Stock - sets stock fields
 * @author sharon
 *
 */

public class Stock {

	protected String symbol;
	protected float ask;
	protected float bid;
	protected Calendar cal = Calendar.getInstance();
	protected java.util.Date date = cal.getTime(); 

	DateFormat dateFt = new SimpleDateFormat("dd/MM/yy");
	
	/**
	 * constructor (initializing members)
	 */

	public Stock(){
		symbol = "";
		bid=0;
		ask=0;
	}
	/**
	 * Stocks copy constructor -receive and set values
	 * @param symbol
	 * @param ask
	 * @param bid
	 * @param date
	 */
	 public Stock (String symbol, float ask, float bid, Date date)
	{
		this();
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		setDate(new Date(date.getTime()));
		
	}
	 /**
		 * copy c'tor of the Stock
		 * @param stock
		 */
	 public Stock(Stock stock) {
			this(stock.getSymbol(), stock.getAsk(), stock.getBid(), new Date (stock.date.getTime()));
		}


	// Getters
	public String getSymbol() {
		return symbol;
	}

	public Float getAsk() {
		return ask;
	}

	public Float getBid() {
		return bid;
	}

	public java.util.Date getDate() {
		return date;
	}

	// Setters
	public void setSymbol(String name) {
		symbol = name;
	}

	public void setAsk(float num1) {
		ask = num1;
	}

	public void setBid(float num2) {
		bid = num2;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}


	public String getHtmlDescription() {
		String dateStr = dateFt.format(date.getTime());
		String stocksDetails = new String ("<b>symbol</b>: " + getSymbol() +  "<b> ask: </b>" + getAsk() + "<b> bid: </b> " + getBid()  + " <b> date: </b> " + dateStr);
		return stocksDetails;
	}



}