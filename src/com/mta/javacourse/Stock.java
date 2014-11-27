package com.mta.javacourse;

import java.util.Calendar;
import java.util.Date;
import java.util.Date;
public class Stock {

	private String symbol;
	private float ask;
	private float bid;
	private java.util.Date Date;
	//Calendar c = Calendar.getInstance();
	
	
	
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
		return Date;
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
		Date = date;
	}

	/*public void setC(Calendar c) {
		this.c = c;
	}*/
	
	public String getHtmlDescription() {
		String stocksDetails = new String ("<b>symbol</b>: " + getSymbol() +  "<b> ask = </b>:" + getAsk() + "<b> bid </b>: " + getBid()  + " <b> date </b>: " + getDate());
		return stocksDetails;
	}


	
}
