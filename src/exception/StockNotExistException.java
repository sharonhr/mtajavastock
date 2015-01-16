package exception;

/**
 * exception to be thrown when a stock doesn’t exist
 * @author sharon
 *
 */
public class StockNotExistException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public StockNotExistException(String symbol) {
		super("Stock " + symbol + " does not exist!");
	}

}
