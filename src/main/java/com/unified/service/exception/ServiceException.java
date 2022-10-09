package com.unified.service.exception;

import org.springframework.http.HttpStatus;

public class ServiceException {
	
	private String errorMsg;
	
	private HttpStatus status;

	public ServiceException() {
	
	}
	
	public ServiceException(String errorMsg) {
		this.errorMsg = errorMsg;		
	}
	
	public ServiceException(HttpStatus status, String errorMsg) {
		this.status = status;
		this.errorMsg = errorMsg;		
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
