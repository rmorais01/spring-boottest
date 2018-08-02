package demo.springboottest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import demo.springboottest.model.Employee;
import demo.springboottest.service.EmployeeService;

/*
 * REST web interface for managing the persistence and retrieval of {@link Employee}  object from database.
 * @author rmorais
 */

@RestController
@RequestMapping("/rmtest")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Adds an employee to the system.
	 *
	 * @param employee
	 *            An employee object.
	 * @return Status code 201 if the Employee is successfully added with the URL of
	 *         the resource set in the location field of the header. An error code
	 *         is returned on failure.
	 * @see Employee
	 */
	@PostMapping("/employees")
	public ResponseEntity<?> addEmployee(final @RequestBody Employee employee) {
		employeeService.addEmployee(employee);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	/**
	 * Gets all employees.
	 *
	 * @return List of all employees in JSON format.
	 * @see Employee
	 */
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	/**
	 * Gets an employee by id.
	 *
	 * @param id
	 *            The employee id.
	 * @return An Employee details in JSON format if the id matches an existing
	 *         Employee. An error code is returned on failure.
	 * @see Employee
	 */
	@GetMapping("/employees/{id}")
	public Employee getEmployee(final @PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return employee;
	}
}
