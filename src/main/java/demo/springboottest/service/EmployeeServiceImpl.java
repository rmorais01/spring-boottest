package demo.springboottest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.springboottest.model.Employee;
import demo.springboottest.repository.EmployeeRepository;

/*
 * Implements the EmployeeService interface for managing the persistence and retrieval of Employee object from database.
 * 
 * @author rmorais
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee addEmployee(Employee employee) {
		employeeRepository.save(employee);
		return employeeRepository.findByName(employee.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> result = employeeRepository.findById(id);
		if(result.isPresent())
			return result.get();
		else  
			throw new EmployeeNotFoundException(String.format("Employee [%s] doesn't exist.", id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee getEmployeeByName(String name) {
		Employee employee = employeeRepository.findByName(name);
		if (employee != null) 
			return employee;
		else  
			throw new EmployeeNotFoundException(String.format("Employee [%s] doesn't exist.", name));
	}
}