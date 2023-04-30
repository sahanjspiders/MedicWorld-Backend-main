package com.jsp.medicworld.exception;

public class UserNotFoundException extends RuntimeException {

	String exception;

	public UserNotFoundException(String exception) {
		super(exception);
		this.exception = exception;
	}

}
