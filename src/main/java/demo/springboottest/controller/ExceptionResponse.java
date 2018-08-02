package demo.springboottest.controller;

import java.util.Date;

/*
 * Provides the details when an exception has occurred during processing.
 * 
 * @author rmorais
 */

public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private String details;

	/**
	 * Constructs an exception response with the message and details.
	 * 
	 * @param timestamp The time at which the exception occurred.
	 * @param message  The exception message.
	 * @param details The exception details.  
	 */	
	public ExceptionResponse(final Date timestamp, final String message, final String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	/* 
	 *  Provides the time at which the exception occurred.
	 * 
	 *  @return The time at which the exception occurred.
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/*
	 * Provides the exception message.
	 *
	 *  @return The exception message.
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * Provides the exception details.
	 *
	 *  @return The exception details.  
	 */
	public String getDetails() {
		return details;
	}

}
