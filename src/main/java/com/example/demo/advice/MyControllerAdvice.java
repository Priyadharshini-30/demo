package com.example.demo.advice;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return new ResponseEntity<>("Incorrect request method type. Please check it",HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
		
		return new ResponseEntity<>("Input fields are empty. Please check the input field",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoElement(NoSuchElementException noSuchElementException){
		
		return new ResponseEntity<>("No value present in data base.Please enter the correct id to fetch data",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> handleElementNotPresent(EmptyResultDataAccessException emptyResultDataAccessException){
		
		return new ResponseEntity<>("The data to be deleted is not present in db",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyPresentException.class)
	public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyPresentException userAlreadyPresentException){
		
		return new ResponseEntity<>("Cannot register! Username already exists",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException userNotFoundException){
		
		return new ResponseEntity<>("User does not exists in db",HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
