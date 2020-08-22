package com.cashonline.app.config.exception;

public class BadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public BadRequestException() {}
	
	public BadRequestException(String message) {
		 this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
