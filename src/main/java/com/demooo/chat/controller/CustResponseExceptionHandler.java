package com.demooo.chat.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(){
		return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
	}
	
	public ResponseEntity<Object> handleJsonMappingException(HttpMessageNotReadableException ex){
		return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid (MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
	}
}
