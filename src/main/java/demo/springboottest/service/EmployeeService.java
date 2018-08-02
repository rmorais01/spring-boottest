package demo.springboottest.service;

import java.util.List;
import demo.springboottest.model.Employee;

/*
 * Service interface for managing the persistence and retrieval of  {@link Employee} object from database.
 */

public interface EmployeeService {

	/**
	 * Adds an employee.
	 *
	 * @param employee An employee object.
	 * 
	 * @return An instance of the Employee class with all attributes populated from
	 *         the database.
	 *         
	 * @see Employee        
	 */
	public Employee addEmployee(Employee employee);

	/**
	 * Gets all employees.
	 *
	 * @return A List of all Employee objects with all attributes populated from
	 *         the database.
	 *         
	 * @see Employee        
	 */
	public List<Employee> getAllEmployees();

	/**
	 * Gets an employee by id.
	 *
	 * @param id The employee id.
	 * 
	 * @return	An instance of the Employee class with all attributes populated from
	 *         the database.
	 *         
	 * @exception EmployeeNotFoundException if employee does not exist.  
	 * 
	 * @see Employee 
	 */
	public Employee getEmployeeById(Long id);

	/**
	 * Gets an employee by name.
	 * 
	 * @param name The employee name.
	 *
	 * @return	An instance of the Employee class with all attributes populated from
	 *         the database.
	 *         
	 * @exception EmployeeNotFoundException if employee does not exist.
	 * 
	 * @see Employee 
	 */
	public Employee getEmployeeByName(String name);
}
