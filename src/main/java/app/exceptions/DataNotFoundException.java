package app.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4534146665154225025L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
