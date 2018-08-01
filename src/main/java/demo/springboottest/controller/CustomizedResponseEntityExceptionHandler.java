package demo.springboottest.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import demo.springboottest.service.EmployeeNotFoundException;
import demo.springboottest.service.EmployeeProcessingException;

/*
 * Converts an exception to a detailed error response message.
 */

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		  ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage(), request.getDescription(false));
		  return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
  }
	
  @ExceptionHandler(EmployeeProcessingException.class)
  public final ResponseEntity<Object> handleProductProcessingExceptions(Exception ex, WebRequest request) {
	  System.out.println("FAILED ## 1");
	  ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage(), request.getDescription(false));
	  return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EmployeeNotFoundException.class)
  public final ResponseEntity<Object> handleProductNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
	  ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage(), request.getDescription(false));
	  return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	  System.out.println("FAILED ## 2");
	  ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", ex.getLocalizedMessage());
	  return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
  } 
}