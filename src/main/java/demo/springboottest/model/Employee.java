package demo.springboottest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/*
 * Represents the attributes of an Employee.
 */

@Entity
@Table(name = "employee")
public class Employee {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
	@Size(min = 3, max = 20)
    private String name;
 
	public Employee() {
	}
	
	public Employee(String name) {
		this.name = name;
	}
	
	/**
	 * @return The Employee id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id  The Employee id.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return The Employee name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name  The Employee name.
	 */
	public void setName(String name) {
		this.name = name;
	}
   
}
