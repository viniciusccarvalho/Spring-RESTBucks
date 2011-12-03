package com.fb.restbucks.exception;

/**
 * @author evincar
 *
 */
public class InvalidTagException extends RuntimeException {

	public InvalidTagException() {
		super();
	}

	public InvalidTagException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidTagException(String message) {
		super(message);
	}

	public InvalidTagException(Throwable cause) {
		super(cause);
	}

}
