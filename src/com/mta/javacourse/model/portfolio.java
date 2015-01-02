package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.service.PortfolioService;
import com.mta.javacourse.model.StockStatus;

/**
 * This class creates stock portfolio
 * @author sharon
 *
 */
public class portfolio {

	public final static int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private float balance;
	private StockStatus[] stocksStatus;
	int portfolioSize;

	public static enum ALGO_RECOMMENDATION{DO_NOTHING, BUY, SELL};


	/**
	 * portfolio constructor
	 */

	public portfolio(){ 
		portfolioSize=0;
		setTitle ("");
		balance=0;
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
			StockStatus stockStatus = new StockStatus();
			
			stockStatus.setSymbol(addingStock.symbol);
			stockStatus.setAsk(addingStock.ask);
			stockStatus.setBid(addingStock.bid);
			stockStatus.setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			stockStatus.date=new Date(addingStock.getDate().getTime());
			this.stocksStatus[portfolioSize]=stockStatus;
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
			if (stocksStatus[i].getSymbol().equals(symbol)) 
			{
				if (quantity == (-1)) 
				{
					balance+=stocksStatus[i].getStockQuantity()*stocksStatus[i].bid;
					stocksStatus[i].setStockQuantity(0);
					return true;
				}
				if (quantity == MAX_PORTFOLIO_SIZE || quantity > MAX_PORTFOLIO_SIZE || quantity<0) 
				{
					System.out.println(symbol+" --- Could not sell! ---");
					return false;
				}
				else
				{
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()-quantity) ;
					balance+= stocksStatus[i].bid*quantity;
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
			if(symbol.equals(stocksStatus[i].getSymbol()))
			{
				if( quantity == -1) {
					int canBuy = (int) (balance/stocksStatus[i].getAsk());
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()+ canBuy);
					float amountOfMoney = ((canBuy) *stocksStatus[i].getAsk())/(-1); 
					updateBalance(amountOfMoney);

				}
				else{
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()+quantity);
					float amountOfMoney=(quantity*stocksStatus[i].getAsk())/(-1);
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
			totalStocksValue += stocksStatus[i].getStockQuantity() * stocksStatus[i].getBid();
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
			resString+= stocksStatus[i].getHtmlDescription() + "<br>";
		}
		return resString;
	}
}


