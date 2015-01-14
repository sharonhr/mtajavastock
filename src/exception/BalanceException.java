package exception;

public class BalanceException extends Exception {

	private static final long serialVersionUID = 1L;

	public BalanceException(float balance, float ask, int quantity) {
		super("Not enough balance. Your balance is " + balance + ", BUT the amount is " + ask * quantity);
	}
}
