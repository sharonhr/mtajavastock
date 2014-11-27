package com.mta.javacourse;

import java.util.Date;
public class Stock {

	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	
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
