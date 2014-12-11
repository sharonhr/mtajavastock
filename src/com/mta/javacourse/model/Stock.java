package com.mta.javacourse.model;

import java.util.Date;

/**
 * Stock - sets stock fields
 * @author sharon
 *
 */

public class Stock {

	private String symbol;
	private float ask;
	private float bid;
	private Date date;

	/**
	 * initializing members
	 */
	
	public Stock(){
		symbol = "";
		bid=0;
		date = new Date();
	}
	/**
	 * insert values to members
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
		setDate(date);
	}

	/**
	 * copy constructor for stock
	 * @param stock
	 */
	public Stock (Stock stock)
	{
		this (stock.getSymbol(), stock.getAsk(), stock.getBid(), stock.getDate());
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

	public Date getDate() {
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

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHtmlDescription() {
		String stocksDetails = new String ("<b>symbol</b>: " + getSymbol() +  "<b> ask: </b>" + getAsk() + "<b> bid: </b> " + getBid()  + " <b> date: </b> " + getDate());
		return stocksDetails;
	}



}
