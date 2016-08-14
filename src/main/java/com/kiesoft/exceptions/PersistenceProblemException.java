package com.kiesoft.exceptions;

public class PersistenceProblemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6923479161078145137L;

	private Exception exception;

	public PersistenceProblemException(Exception exception) {
		super();
		this.exception = exception;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
