package demo.springboottest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Represents the attributes of an Employee.
 *
 * @author rmorais
 */

@Entity
@Table(name = "employee")
public class Employee {

    private final int minNameSize = 3;
    private final int maxNameSize = 20;

    /**
     * Auto-generated id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Size between 3 and 20 characters.
     */
    @Size(min = minNameSize, max = maxNameSize)
    private String name;

    private Integer salary;

    /**
     * Creates an instance of Employee. (Required by JPA).
     */
    public Employee() {
    }

    /**
     * Creates an instance of Employee with the specified attributes.
     *
     * @param name
     *            The Employee name.
     * @param name
     *            The Employee salary.
     */
    public Employee(final String name, final Integer salary) {
        this.name = name;
        this.salary = salary;
    }

    /**
     * Returns the Employee id.
     *
     * @return The Employee id.
     */
    public final Long getId() {
        return id;
    }

    /**
     * Sets the Employee id.
     *
     * @param id
     *            The Employee id.
     */
    public final void setId(final Long id) {
        this.id = id;
    }

    /**
     * Returns the Employee name.
     *
     * @return The Employee name.
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets the Employee name.
     *
     * @param name
     *            The Employee name.
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the Employee salary.
     *
     * @return The Employee salary.
     */
    public final Integer getSalary() {
        return salary;
    }

    /**
     * Sets the Employee salary.
     *
     * @param salary
     *            The Employee salary.
     */
    public final void setSalary(final Integer salary) {
        this.salary = salary;
    }

}
