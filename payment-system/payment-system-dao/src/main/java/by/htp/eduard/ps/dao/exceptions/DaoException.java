package by.htp.eduard.ps.dao.exceptions;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException(Exception e) {
		super(e);
	}

	public DaoException(String message, Exception e) {
		super(message, e);
	}
}
