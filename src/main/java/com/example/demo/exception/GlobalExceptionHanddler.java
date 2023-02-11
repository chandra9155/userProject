package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHanddler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandler(ResourceNotFoundException ex,WebRequest request){
		ErrorInfo errorInfo=new ErrorInfo(new Date() ,ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errorInfo,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception globalException,WebRequest request){
		ErrorInfo errorInfo =new ErrorInfo(new Date(), globalException.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errorInfo,HttpStatus.NOT_FOUND);
	}

}
