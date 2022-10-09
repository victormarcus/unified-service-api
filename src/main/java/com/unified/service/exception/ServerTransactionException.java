package com.unified.service.exception;

public class ServerTransactionException extends RuntimeException {
	
	private String errorCode;
	
	private String errorMsg;

	public ServerTransactionException() {
	
	}
	
	public ServerTransactionException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;		
	}

}