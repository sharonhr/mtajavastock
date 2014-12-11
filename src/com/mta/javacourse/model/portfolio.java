package com.mta.javacourse.model;

import java.util.Date;

/**
 * This class creates stock portfolio
 * @author sharon
 *
 */
public class portfolio {

	public final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	int portfolioSize;


	public portfolio(){ 
		int portfolioSize=0;
		setTitle ("Portfolio 1");
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];

	}
	
	/**
	 * Constructor that sets a title
	 * @param title
	 */

	public portfolio (String title)
	{
		this ();
		this.setTitle(title);
	}
	

	/**
	 * adds a new stock to portfolio
	 * @param addingStock
	 */

	public void addStock(Stock addingStock){
		stocks[portfolioSize]=addingStock;
		stocksStatus[portfolioSize] = new StockStatus();
		portfolioSize++;
	}
	
	/**
	 * copy portfolio objects
	 * @param portfolio
	 */
	
	public portfolio(portfolio portfolio)
	{
		this(portfolio.getTitle());
		setPortfolioSize(portfolio.getPortfolioSize());

		for (int i = 0; i < portfolioSize; i++) 
			stocks[i] = new Stock(portfolio.getStocks()[i]);	

		for(int i = 0; i < portfolioSize; i++)
			stocksStatus[i] = new StockStatus(portfolio.getStocksStatus()[i]);     
	}
	

	/**
	 * removes a specific stock
	 * @param stock
	 */
	
	public void removeStock(Stock stock)
	{
		for(int i = 0; i < portfolioSize; i++)
		{
			if(this.stocks[i].getSymbol().equals(stock.getSymbol()))
			{
				if(i != portfolioSize-1 && portfolioSize > 1)
					for(int j = i; j < portfolioSize-1; j++)
					{
						this.stocks[j] = new Stock(this.stocks[j+1]);
					}
			}
		}
		portfolioSize--;
	}


	public Stock[] getStocks()
	{
		return stocks;
	}

	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}


	public String getHtmlString()
	{
		String resString = "<h1>" + title + "<h1>";
		for(int i=0; i<portfolioSize ; i++)
		{
			resString+= stocks[i].getHtmlDescription() + "<br>";
		}
		return resString;
	}

	/**
	 * inner class - contains stock status
	 * @author sharon
	 *
	 */
	
	public class StockStatus{
		final static int DO_NOTHING=0;
		final static int BUY=1;
		final static int SELL=2;

		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation;
		int stockQuantity;

		/**
		 * Initializing all needed variables 
		 */
		
		public	StockStatus()
		{
			symbol = "empty";
			currentAsk = 0;
			currentBid = 0;
			date = new Date();
			recommendation = DO_NOTHING;
			stockQuantity = 0;
		}
		
		/**
		 * copy constructor for stock status
		 * @param stockStatus
		 */
		public StockStatus (StockStatus stockStatus)
		{
			this();
			setSymbol(stockStatus.symbol);
			setCurrentAsk(stockStatus.currentAsk);
			setCurrentBid(stockStatus.currentBid);
			setDate(stockStatus.date);
			setRecommendation(stockStatus.recommendation);
			setStockQuantity(stockStatus.stockQuantity);
		}


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
			return currentAsk;
		}
		public void setCurrentAsk(float currentAsk) {
			currentAsk = currentAsk;
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

