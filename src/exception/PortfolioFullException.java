package exception;

/**
 * exception to be thrown when adding more stocks than
allowed
 * @author sharon
 *
 */

public class PortfolioFullException extends Exception{

	private static final long serialVersionUID = 1L;

	public PortfolioFullException() {
		super("You have reached the maximum portfolio size!");
	}
}
