package app.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class EmptyBodyException extends RuntimeException {

	private static final long serialVersionUID = -4534146665154225025L;

	public EmptyBodyException(String message) {
		super(message);
	}

}
