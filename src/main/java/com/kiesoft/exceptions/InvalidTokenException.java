package com.kiesoft.exceptions;

public class InvalidTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8579547417802702960L;

	public InvalidTokenException(String token) {
		super(token);
	}

}
