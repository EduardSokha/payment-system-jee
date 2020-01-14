package by.htp.eduard.service.exceptions;

public class NegativeBalanceException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NegativeBalanceException() {
		super("You have no enought money!");
	}
}
