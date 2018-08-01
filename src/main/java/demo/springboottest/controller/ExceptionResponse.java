package demo.springboottest.controller;

import java.util.Date;

/*
 * Provides the details when an exception has occurred during processing.
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
  public ExceptionResponse(Date timestamp, String message, String details) {
	  super();
	  this.timestamp = timestamp;
	  this.message = message;
	  this.details = details;
  }
 
 /*
  *  @return The time at which the exception occurred.
  */
  public Date getTimestamp() {
	  return timestamp;
  }

 /*
  *  @return The exception message.
  */
  public String getMessage() {
	  return message;
  }

 /*
  *  @return The exception details.  
  */
  public String getDetails() {
	  return details;
  }

}
	  