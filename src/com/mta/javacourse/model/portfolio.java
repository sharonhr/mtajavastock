package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.service.PortfolioService;
import com.mta.javacourse.model.StockStatus;

import exception.BalanceException;
import exception.PortfolioFullException;
import exception.StockAlreadyExistsException;
import exception.StockNotExistException;

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

	public void addStock(Stock addingStock)throws StockAlreadyExistsException, PortfolioFullException{
		if (portfolioSize >= MAX_PORTFOLIO_SIZE){
			System.out.println("You have reached the maximum size of portfolio ");
			throw new PortfolioFullException();
		}

		else if(portfolioSize<MAX_PORTFOLIO_SIZE)
		{
			for (int i = 0; i < portfolioSize; i++) {
				if (stocksStatus[i].getSymbol() == addingStock.symbol)
				{
					System.out.println("This stock already exists");
					throw new StockAlreadyExistsException(addingStock.getSymbol());
				}
			}
			
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
	 * @throws PortfolioFullException 
	 */
	public  void removeStock(String symbol)  throws StockNotExistException, PortfolioFullException
	{
		sellStock(symbol,-1);

		for (int i=0 ; i<portfolioSize; i++)
		{
			if (stocksStatus[i].symbol.equals(symbol))
			{
				stocksStatus[i] = stocksStatus[portfolioSize-1];
				stocksStatus[portfolioSize-1] =null;
				portfolioSize--;
			}
			else throw new StockNotExistException(symbol);
		}
	}

	/** Sells stock and update the balance accordingly 
	 * @param symbol
	 * @param quantity
	 * @return
	 * @throws PortfolioFullException 
	 */


	public void sellStock (String symbol, int quantity) throws StockNotExistException, PortfolioFullException
	{
		for (int i=0; i < portfolioSize; i++) 
		{
			if (stocksStatus[i].getSymbol().equals(symbol)) 
			{
				if (quantity == (-1)) 
				{
					balance+=stocksStatus[i].getStockQuantity()*stocksStatus[i].bid;
					stocksStatus[i].setStockQuantity(0);
				}
				if (quantity == MAX_PORTFOLIO_SIZE || quantity > MAX_PORTFOLIO_SIZE || quantity<0) 
				{
					System.out.println(symbol+" --- Could not sell! ---");
					throw new PortfolioFullException();
				}
				else
				{
					stocksStatus[i].setStockQuantity(stocksStatus[i].getStockQuantity()-quantity) ;
					balance+= stocksStatus[i].bid*quantity;
				
				}
			}
			else throw new StockNotExistException(symbol);

		}
		
	}
	/**
	 * Buy stock/s and update the balance
	 * @param symbol
	 * @param quantity
	 * @return
	 */

	public void buyStock(String symbol, int quantity ) throws StockNotExistException
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
			}
			else throw new StockNotExistException(symbol);
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


