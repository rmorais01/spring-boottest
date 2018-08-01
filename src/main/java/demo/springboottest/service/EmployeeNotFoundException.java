package demo.springboottest.service;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/*
 * Indicates that an Employee object is not found in the database.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

   /**
	* Constructs an EmployeeNotFoundException with the specified detail message.
	* 
	* @param message The detail message. 
	*/
	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
