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
 * @author rmorais
 */

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Generic handler for all exceptions.
	 * 
	 * @param ex
	 *            The exception.
	 * @param request
	 *            The WebRequest.
	 * @return HTTP Status for Internal Server Error - 500.
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(final Exception ex, final WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handler for Employee processing exceptions.
	 *
	 * @param ex
	 *            The exception.
	 * @param request
	 *            The WebRequest.
	 * @return HTTP Status for Bad Request - 400.
	 */
	@ExceptionHandler(EmployeeProcessingException.class)
	public final ResponseEntity<Object> handleProductProcessingExceptions(final Exception ex,
			final WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handler for Employee not found exceptions.
	 *
	 * @param ex
	 *            The exception.
	 * @param request
	 *            The WebRequest.
	 * @return HTTP Status for Not Found - 404.
	 */
	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<Object> handleProductNotFoundException(final EmployeeNotFoundException ex,
			final WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getLocalizedMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * Generic handler for Invalid arguments.
	 *
	 * @param ex
	 *            The exception.
	 * @param headers
	 *            The HttpHeaders.
	 * @param status
	 *            The HttpStatus.
	 * @param request
	 *            The WebRequest.
	 * @return HTTP Status for Bad Request - 400.
	 */
	@Override
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getLocalizedMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
