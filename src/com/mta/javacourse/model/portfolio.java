package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.Stock;

public class portfolio {

	public final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	int counter=0;

	public portfolio(){ 
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	}

	public void addStock(Stock addingStock){
		stocks[counter]=addingStock;
		counter++;
	}

	public Stock[] getStocks()
	{	
		return stocks;
	}

	public class StockStatus{
		private String symbol;
		private float currentBid, CurrentAsk;
		private Date date;
		private int recommendation;
		int stockQuantity;
		final static int DO_NOTHING=0;
		final static int BUY=1;
		final static int SELL=2;

	}		

}

