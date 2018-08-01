package demo.springboottest.service;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/*
 * Indicates that an error has occurred while processing an Employee object.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

   /**
	* Constructs an EmployeeProcessingException with the specified detail message.
	* 
	* @param message The detail message. 
	*/
	public EmployeeProcessingException(String message) {
		super(message);
	}
}
