package com.unified.service.exception;

public class UnifiedServiceException extends RuntimeException {

	private String message;
	
	public UnifiedServiceException() {
		// TODO Auto-generated constructor stub
	}

	public UnifiedServiceException(String message) {
		super(message);
		this.message = message;		
	}

}
