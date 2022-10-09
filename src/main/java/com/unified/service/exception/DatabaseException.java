package com.unified.service.exception;

public class DatabaseException extends RuntimeException {
	
	private String errorCode;
	
	private String errorMsg;

	public DatabaseException() {
	
	}
	
	public DatabaseException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;		
	}

}