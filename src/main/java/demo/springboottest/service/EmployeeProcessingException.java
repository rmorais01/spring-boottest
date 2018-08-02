package demo.springboottest.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Indicates that an error has occurred while processing an Employee object.
 * 
 * @author rmorais
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeProcessingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an EmployeeProcessingException with the specified detail message.
     *
     * @param message
     *            The detail message.
     */
    public EmployeeProcessingException(final String message) {
        super(message);
    }
}
