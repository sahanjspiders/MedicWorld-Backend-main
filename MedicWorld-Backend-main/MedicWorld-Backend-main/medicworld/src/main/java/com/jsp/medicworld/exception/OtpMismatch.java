package com.jsp.medicworld.exception;


public class OtpMismatch extends RuntimeException{
	
	String invalid;
	public OtpMismatch(String invalid) {
		
		super(invalid);
		this.invalid=invalid;
		
	}

}
