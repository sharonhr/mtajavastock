package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.Stock;

public class portfolio {

	public final static int MAX_PORTFOLIO_SIZE = 5;
	private String title = ("portfolio title");
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
	
	public String getHtmlString()
	{
		String resString = "<h1>" + title + "<h1>";
		for(int i=0; i<counter ; i++)
		{
			resString+= stocks[i].getHtmlDescription() + "<br>";
		}
		return resString;
	}

	public class StockStatus{
		final static int DO_NOTHING=0;
		final static int BUY=1;
		final static int SELL=2;
		
		private String symbol;
		private float currentBid, CurrentAsk;
		private Date date;
		private int recommendation;
		int stockQuantity;
		
		
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public float getCurrentBid() {
			return currentBid;
		}
		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}
		public float getCurrentAsk() {
			return CurrentAsk;
		}
		public void setCurrentAsk(float currentAsk) {
			CurrentAsk = currentAsk;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getRecommendation() {
			return recommendation;
		}
		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}
		public int getStockQuantity() {
			return stockQuantity;
		}
		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
		

		
	}		

}

