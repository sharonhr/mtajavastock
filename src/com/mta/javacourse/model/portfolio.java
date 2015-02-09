package com.mta.javacourse.model;

import java.util.Date;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

import com.mta.javacourse.service.PortfolioService;
import com.mta.javacourse.model.StockStatus;

import exception.BalanceException;
import exception.PortfolioFullException;
import exception.StockAlreadyExistsException;
import exception.StockNotExistsException;

/**
 * This class creates stock portfolio
 * @author sharon
 *
 */
public class portfolio {

	
	public static final int MAX_PORTFOLIO_SIZE = 5;
	private String title;
	private float balance;
	//private StockStatus[] stocksStatus;
	private List<StockStatus> stockStatus;
	int portfolioSize;
	private int stockStatusSize;

	public static enum ALGO_RECOMMENDATION{DO_NOTHING, BUY, SELL};


	/**
	 * portfolio constructor
	 */

	public portfolio(){ 
		portfolioSize=0;
		setTitle ("");
		balance=0;
		stockStatus = new ArrayList<StockStatus>(MAX_PORTFOLIO_SIZE);
		//stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		stockStatusSize=0;
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

		/*for(int i = 0; i < portfolioSize; i++)
			stocksStatus[i] = new StockStatus(portfolio.getStocksStatus()[i]);*/ 

		stockStatusSize=portfolio.stockStatusSize;
		stockStatus.addAll(portfolio.stockStatus);
		balance=portfolio.balance;

	}

	public portfolio(List <StockStatus> stockStatus)
	{
		this();

		for (int i = 0; i < stockStatus.size(); i++) {
			stockStatus.add(stockStatus.get(i));
		}
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

	/*public void addStock(Stock addingStock)throws StockAlreadyExistsException, PortfolioFullException{
		if (portfolioSize >= MAX_PORTFOLIO_SIZE){
			System.out.println("You have reached the maximum size of portfolio ");
			throw new PortfolioFullException();
		}

		else if(portfolioSize<MAX_PORTFOLIO_SIZE)
		{
			for (int i = 0; i < portfolioSize; i++) {
				if (stockStatus.get(i).getSymbol().equals(addingStock.getSymbol())) { //
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
			//	this.stocksStatus[portfolioSize]=stockStatus;
				stockStatus.add(new StockStatus((StockStatus) addingStock));
				portfolioSize++;
			}
	}
	 */

	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException {

		if(stockStatus.size()==MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Cant add new stock, portfolio can have only "+ MAX_PORTFOLIO_SIZE +" stocks");
			throw new PortfolioFullException();
		}

		for (int i = 0; i < stockStatus.size(); i++) {

			if (stockStatus.get(i).getSymbol().equals(stock.getSymbol())) {
				System.out.println(stockStatus.get(i).getSymbol() + " Already exists in portfolio");

				throw new StockAlreadyExistsException(stock.getSymbol());
			}
		}

		stockStatus.add(new StockStatus(stock));		
	}

	/**
	 * Removes the sold stock from portfolio.
	 * @param success or failure
	 * @throws PortfolioFullException 
	 */
	/*
	public  void removeStock(String symbol)  throws StockNotExistException, PortfolioFullException
	{
		sellStock(symbol,-1);

		for (int i=0 ; i<portfolioSize; i++)
		{
			if (stocksStatus.get(i).symbol.equals(symbol))
			{
				stockStatus.get(i) = stocksStatus[portfolioSize-1];
				stockStatus[portfolioSize-1] =null;
				portfolioSize--;
			}
			else throw new StockNotExistException(symbol);
		}
	}
	*/

	public void removeStock(String symbol) throws StockNotExistsException, PortfolioFullException
	{	
		int index = get_index_of_symbol(symbol);
		
		if(index ==-1){
			throw new  StockNotExistsException(symbol);
		}
		
		sellStock(symbol, -1);
		
		while (index < stockStatus.size())
		{
			stockStatus.remove(index);
			index++;
		}
	}

	/**
	 * get symbol and find the place in the array - the index of symbol 
	 * @param symbol
	 * @return
	 */
	private int get_index_of_symbol(String symbol)
	{
		for(int i = 0; i < stockStatus.size(); i++)
		{
			if(stockStatus.get(i).getSymbol().toLowerCase().equals(symbol.toLowerCase()))
			{
				return i;
			}
		}
		return -1; 
	}








	/** Sells stock and update the balance accordingly 
	 * @param symbol
	 * @param quantity
	 * @return
	 * @throws PortfolioFullException 
	 */


	public void sellStock (String symbol, int quantity) throws  StockNotExistsException, PortfolioFullException
	{
		for (int i=0; i < portfolioSize; i++) 
		{
			if (stockStatus.get(i).getSymbol().equals(symbol)) { 
				{
					if (quantity == (-1)) 
					{
						balance+=stockStatus.get(i).getStockQuantity()*stockStatus.get(i).bid;
						stockStatus.get(i).setStockQuantity(0);
					}
					if (quantity == MAX_PORTFOLIO_SIZE || quantity > MAX_PORTFOLIO_SIZE || quantity<0) 
					{
						System.out.println(symbol+" --- Could not sell! ---");
						throw new PortfolioFullException();
					}
					else
					{
						stockStatus.get(i).setStockQuantity(stockStatus.get(i).getStockQuantity()-quantity) ;
						balance+= stockStatus.get(i).bid*quantity;

					}
				}
			}
			else throw new StockNotExistsException(symbol);
		}
	}
	/**
	 * Buy stock/s and update the balance
	 * @param symbol
	 * @param quantity
	 * @return
	 */

	public void buyStock(String symbol, int quantity ) throws StockNotExistsException
	{
		for(int i=0; i<portfolioSize;i++)
			if(symbol.equals(stockStatus.get(i).getSymbol()))
			{
				if( quantity == -1) {
					int canBuy = (int) (balance/stockStatus.get(i).getAsk());
					stockStatus.get(i).setStockQuantity(stockStatus.get(i).getStockQuantity()+ canBuy);
					float amountOfMoney = ((canBuy) *stockStatus.get(i).getAsk())/(-1); 
					updateBalance(amountOfMoney);

				}
				else{
					stockStatus.get(i).setStockQuantity(stockStatus.get(i).getStockQuantity()+quantity);
					float amountOfMoney=(quantity*stockStatus.get(i).getAsk())/(-1);
					updateBalance(amountOfMoney);
				}	
			}
			else throw new StockNotExistsException(symbol);
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
			totalStocksValue += stockStatus.get(i).getStockQuantity() *stockStatus.get(i).getBid();
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
/*
	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}
	*/
	public void setStocksStatus(List<StockStatus> stocksStatus) {
		this.stockStatus = stocksStatus;
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
/*
	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}
*/
	public List<StockStatus> getStocksStatus() {
		return stockStatus;
	}
	
	
	/**
	 * returns all relevant values that will be printed)
	 * @return
	 */
	

	public StockStatus[] getStocks() {
		StockStatus[] ret = new StockStatus[stockStatus.size()];
		ret =  stockStatus.toArray(ret);
		return ret;
	}

	public void printStockStatusList (List<StockStatus> stockStatuses)
	{
		System.out.println("method printStockStatusList");
		System.out.println("stockStatuses.size() in printStockStatusList is:" + stockStatuses.size());
		for (int i = 0; i < stockStatuses.size(); i++) {
			System.out.println(stockStatuses.get(i).symbol);
		}
	}

	
	public StockStatus findBySymbol(String symbol) throws BalanceException,StockNotExistsException, StockAlreadyExistsException, PortfolioFullException {

		PortfolioService portfolioService = new PortfolioService();
		portfolio portfolio = portfolioService.getPortfolio();

		System.out.println("method findBySymbol(String symbol)");
		System.out.println("portfolio.stockStatusSize in findBySymbol is:" + portfolio.stockStatusSize);
		for (int i = 0; i < portfolio.stockStatusSize; i++) {
			if (symbol.equals(portfolio.stockStatus.get(i).symbol)) {
				System.out.println("worked");
				return portfolio.stockStatus.get(i);
			}
		}
		System.out.println("error");
		return null;
		
		
	}
	
	
	public String getHtmlString()
	{
		String resString = "<h1>" + title + "<h1>"+ "<br>"+"<br>"+ "Total value of stocks: "+ getStocksValue() + " $" + "<br>" + "Total Portfolio value: " + getTotalValue() + "$"+"<br>"+" Balance: "+ getBalance()+ "$" +"<br>" ;
		getBalance();
		getTotalValue();

		for(int i=0; i<portfolioSize ; i++)
		{
			resString+= stockStatus.get(i).getHtmlDescription() + "<br>";
		}
		return resString;
	}
}


