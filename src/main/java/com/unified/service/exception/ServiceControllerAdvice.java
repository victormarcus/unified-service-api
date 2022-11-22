package com.unified.service.exception;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ServiceControllerAdvice {
	
	@ExceptionHandler(UnknownHostException.class)
	public ResponseEntity<Object> handleServerError(UnknownHostException exception) {
		ServiceException apiError = new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, exception.toString());
		return buildResponseEntity(apiError);
	}
	
	public ResponseEntity<Object> buildResponseEntity(ServiceException apiError) {
		return new ResponseEntity<Object>(apiError, apiError.getStatus());	
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDbIntegrityError(DataIntegrityViolationException exception) {
		ServiceException apiError = new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, exception.toString());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(SocketTimeoutException.class)
	public ResponseEntity<Object> handleDbIntegrityError(SocketTimeoutException exception) {
		ServiceException apiError = new ServiceException(HttpStatus.REQUEST_TIMEOUT, exception.toString());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(UnifiedServiceException.class)
	public ResponseEntity<Object> handleServiceError(UnifiedServiceException exception) {
		ServiceException apiError = new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
		return buildResponseEntity(apiError);
	}
}
