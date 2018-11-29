package com.nagarro.java.training.FlightSearchApis.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nagarro.java.training.FlightSearchApis.errorResponse.ErrorResponse;
import com.nagarro.java.training.FlightSearchApis.exceptions.UserNotFoundException;
import com.nagarro.java.training.FlightSearchApis.exceptions.UsernameAlreadyTakenException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleRuntimeException(UserNotFoundException e) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		errorResponse.setErrorMessage(e.getMessage());
		
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleRuntimeException(UsernameAlreadyTakenException e) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		errorResponse.setErrorMessage(e.getMessage());
		
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	
	}
}
