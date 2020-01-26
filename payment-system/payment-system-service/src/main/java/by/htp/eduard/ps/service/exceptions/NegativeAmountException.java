package by.htp.eduard.ps.service.exceptions;

public class NegativeAmountException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegativeAmountException() {
		super("The payment amount must be without a minus!");
	}
}
