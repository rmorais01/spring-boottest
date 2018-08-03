package demo.springboottest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import demo.springboottest.model.Employee;

/**
 * Repository interface for managing the persistence and retrieval of
 * {@link Employee} object from database.
 *
 * @author rmorais
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Finds an employee by name.
     *
     * @param name
     *            The name of the employee.
     * @return An instance of the Employee class with all attributes populated from
     *         the database or null if no match is found.
     * @see Employee
     */
    Employee findByName(String name);

    /**
     * Returns an employee name for a given id.
     *
     * @param id
     *            The id of the employee.
     * @return The name of the employee or null if no match is found.
     * @see Employee
     */
    @Query("select e.name from Employee e where e.id = ?1")
    String getEmployeeName(Long id);

    /**
     * Finds employees by salary range.
     *
     * @param low
     *            The low range of salary.
     * @param high
     *            The high range of salary.
     *
     * @return A List of Employees who meet the specified criteria.
     * @see Employee
     */
    @Query("select e from Employee e where e.salary >= ?1 and e.salary <= ?2 order by e.salary DESC")
    List<Employee> findBySalaryRange(Integer low, Integer high);
}
