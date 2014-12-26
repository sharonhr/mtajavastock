package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.service.PortfolioService;

/**
 * This class creates stock portfolio
 * @author sharon
 *
 */
public class portfolio {

	public final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private float balance;
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	int portfolioSize;

	public static enum ALGO_RECOMMENDATION{DO_NOTHING, BUY, SELL};


	/**
	 * portfolio constructor
	 */

	public portfolio(){ 
		int portfolioSize=0;
		setTitle ("");
		balance=0;
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	}

	/**
	 * sets the title
	 * @param title
	 */

	public portfolio (String title)
	{
		this ();
		this.setTitle(title);
	}

	/**
	 * copy constructor
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
	 * function that updates balance according to the relevant actions 
	 * @param amount - sum of money
	 * @return
	 */

	public float updateBalance(float amount){

		balance+=amount;
		return balance;
	}

	/**
	 * adds a new stock to portfolio and adjust the array accordingly
	 * @param addingStock
	 */

	public void addStock(Stock addingStock){
		if (portfolioSize >= MAX_PORTFOLIO_SIZE){
			System.out.println("You have reached the maximum size of portfolio ");
		}

		else if(portfolioSize<MAX_PORTFOLIO_SIZE)
		{
			stocks[portfolioSize]=addingStock;
			stocksStatus[portfolioSize] = new StockStatus(addingStock.getSymbol(), addingStock.getAsk(), addingStock.getBid(), new Date(addingStock.getDate().getTime()), ALGO_RECOMMENDATION.DO_NOTHING, 0 );
			portfolioSize++;
		}
	}

	/**
	 * Removes the sold stock from portfolio.
	 * @param success or failure
	 */
	public  boolean removeStock(String symbol)
	{
		boolean tryingToSell = sellStock(symbol,-1);

		for (int i=0 ; i<portfolioSize; i++)
		{
			if (stocksStatus[i].symbol.equals(symbol) && tryingToSell==true)
			{
				stocks[i] = stocks[portfolioSize-1];
				stocks[portfolioSize-1]= null;
				stocksStatus[i] = stocksStatus[portfolioSize-1];
				stocksStatus[portfolioSize-1] =null;
				portfolioSize--;
				return true;
			}
		}
		return false;
	}

	/** Sells stock and update the balance accordingly 
	 * @param symbol
	 * @param quantity
	 * @return
	 */


	public boolean sellStock (String symbol, int quantity)
	{
		for (int i=0; i < portfolioSize; i++) 
		{
			if (stocks[i].getSymbol().equals(symbol)) 
			{
				if (quantity == (-1)) 
				{
					balance+=stocksStatus[i].stockQuantity*stocksStatus[i].currentBid;
					stocksStatus[i].stockQuantity=0;
					return true;
				}
				if (quantity == MAX_PORTFOLIO_SIZE || quantity > MAX_PORTFOLIO_SIZE || quantity<0) 
				{
					System.out.println(symbol+" --- Could not sell! ---");
					return false;
				}
				else
				{
					stocksStatus[i].stockQuantity= (stocksStatus[i].stockQuantity-quantity) ;
					balance+= stocksStatus[i].currentBid*quantity;
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Buy stock/s and update the balance
	 * @param symbol
	 * @param quantity
	 * @return
	 */

	public boolean buyStock(String symbol, int quantity )
	{
		for(int i=0; i<portfolioSize;i++)
			if(symbol.equals(stocks[i].getSymbol()))
			{
				if( quantity == -1) {
					int canBuy = (int) (balance/stocksStatus[i].getCurrentAsk());
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()+ canBuy);
					float amountOfMoney = ((canBuy) *stocksStatus[i].getCurrentAsk())/(-1); 
					updateBalance(amountOfMoney);

				}
				else{
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()+quantity);
					float amountOfMoney=(quantity*stocksStatus[i].getCurrentAsk())/(-1);
					updateBalance(amountOfMoney);
				}
				return true;
			}
		return false;
	}


	/**
	 * Calculates value of balance
	 * @return
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * returns the total value of all stocks
	 * @return
	 */

	public float getStocksValue() {
		float totalStocksValue = 0;

		for (int i = 0; i < portfolioSize; i++) {
			totalStocksValue += stocksStatus[i].getStockQuantity() * stocksStatus[i].getCurrentBid();
		}
		return totalStocksValue;
	}

	/**
	 * The sum of all stocks value and portfoios balance
	 * @returns Total value
	 */
	public float getTotalValue() {
		return getBalance() + getStocksValue();
	}

	//Setters
	public void setBalance(float balance) {
		this.balance = balance;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}
	
	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	// Getters
	public int getPortfolioSize() {
		return portfolioSize;
	}

	public String getTitle() {
		return title;
	}
	
	public Stock[] getStocks()
	{
		return stocks;
	}
	
	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}

	/**
	 * returns all relevant values that will be printed)
	 * @return
	 */
	public String getHtmlString()
	{
		String resString = "<h1>" + title + "<h1>"+ "<br>"+"<br>"+ "Total value of stocks: "+ getStocksValue() + " $" + "<br>" + "Total Portfolio value: " + getTotalValue() + "$"+"<br>"+" Balance: "+ getBalance()+ "$" +"<br>" ;
		getBalance();
		getTotalValue();

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


		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private ALGO_RECOMMENDATION recommendation;
		int stockQuantity;

		/**
		 * Initializing all needed variables 
		 */

		public	StockStatus(String newSymbol, float newCurrentAsk, float newCurrentBid, Date newDate, ALGO_RECOMMENDATION newRecommendation, int newStockQuantity)
		{
			symbol = newSymbol;
			currentAsk = newCurrentAsk;
			currentBid = newCurrentBid;
			date = newDate;
			ALGO_RECOMMENDATION recommendation=newRecommendation;
			stockQuantity = newStockQuantity;
		}

		/**
		 * copy constructor for stock status
		 * @param stockStatus
		 */
		public StockStatus (StockStatus stockStatus)
		{
			setSymbol(stockStatus.symbol);
			setCurrentAsk(stockStatus.currentAsk);
			setCurrentBid(stockStatus.currentBid);
			setDate(stockStatus.date);
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
			this.currentAsk = currentAsk;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getStockQuantity() {
			return stockQuantity;
		}
		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}



	}		

}

