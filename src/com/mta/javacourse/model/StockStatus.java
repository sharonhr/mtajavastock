package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.model.portfolio.ALGO_RECOMMENDATION;

public class StockStatus extends Stock{

	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	/**
	 * initializing members in StockStatus 
	 */
	public StockStatus(){
		symbol = "";
		ask = 0;
		bid = 0;
		recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}
	
	/**
	 * copy constructor of StockStatus
	 */
	public StockStatus (StockStatus stockStatus)
	{
		setSymbol(stockStatus.getSymbol());
		setAsk(stockStatus.getAsk());
		setBid(stockStatus.getBid());
		this.date = new Date(stockStatus.date.getTime());
		setRecommendation(stockStatus.recommendation);
		setStockQuantity(stockStatus.stockQuantity);
	}
	
	
	public StockStatus(Stock stock) {
		super(stock);
	}
	
	
	/**
	 * copy c'tor
	 * @param Symbol
	 * @param ask
	 * @param bid
	 * @param date
	 */
	public StockStatus(String Symbol, float ask, float bid, Date date) {
		this();
		setSymbol(Symbol);
		setAsk(ask);
		setBid(bid);
		setDate(new Date(date.getTime()));
	}

	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
		
}
