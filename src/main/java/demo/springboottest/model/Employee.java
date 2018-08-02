package demo.springboottest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/*
 * Represents the attributes of an Employee.
 * @author rmorais
 */

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 3, max = 20)
	private String name;

	/**
	 * Creates an instance of Employee. (Required by JPA)
	 */
	public Employee() {
	}

	/**
	 * Creates an instance of Employee with the specified name.
	 * 
	 * @param name
	 *            The Employee name.
	 */
	public Employee(String name) {
		this.name = name;
	}

	/**
	 * Returns the Employee id.
	 * 
	 * @return The Employee id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the Employee id.
	 * 
	 * @param id
	 *            The Employee id.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the Employee name.
	 * 
	 * @return The Employee name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Employee name.
	 * 
	 * @param name
	 *            The Employee name.
	 */
	public void setName(String name) {
		this.name = name;
	}

}
