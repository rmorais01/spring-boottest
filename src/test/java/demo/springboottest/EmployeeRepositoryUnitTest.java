package demo.springboottest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import demo.springboottest.model.Employee;
import demo.springboottest.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        Employee employee = new Employee("Employee 1", 60000);
        entityManager.persistAndFlush(employee);

        Employee found = employeeRepository.findByName(employee.getName());
        assertThat(found.getName()).isEqualTo(employee.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        Employee fromDb = employeeRepository.findByName("doesNotExist");
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenGetNameById_thenReturnName() {
        Employee employee = new Employee("Employee 1", 65000);
        Employee result = entityManager.persistAndFlush(employee);

        String fromDb = employeeRepository.getEmployeeName(result.getId());
        assertThat(fromDb.equals(employee.getName()));
    }

    @Test
    public void whenFindById_thenReturnEmployee() {
        Employee employee = new Employee("Employee 1", 90000);
        entityManager.persistAndFlush(employee);

        Employee fromDb = employeeRepository.findById(employee.getId()).orElse(null);
        assertThat(fromDb.getName()).isEqualTo(employee.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Employee fromDb = employeeRepository.findById(-111L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindBySalary_thenReturnEmployees() {
        Employee employee1 = new Employee("Employee 1", 85000);
        Employee employee2 = new Employee("Employee 2", 125000);
        Employee employee3 = new Employee("Employee 3", 130000);
        Employee employee4 = new Employee("Employee 4", 140000);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.persist(employee4);
        entityManager.flush();

        List<Employee> allEmployees = employeeRepository.findBySalaryRange(130000, 140000);

        assertThat(allEmployees).hasSize(2).extracting(Employee::getName).containsOnly(employee3.getName(),
                employee4.getName());
    }

    @Test
    public void whenFindBySalaryNoMatch_thenReturnEmptyList() {
        Employee employee1 = new Employee("Employee 1", 85000);
        Employee employee2 = new Employee("Employee 2", 125000);
        Employee employee3 = new Employee("Employee 3", 130000);
        Employee employee4 = new Employee("Employee 4", 140000);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.persist(employee4);
        entityManager.flush();

        List<Employee> allEmployees = employeeRepository.findBySalaryRange(60000, 80000);

        assertThat(allEmployees).hasSize(0);
    }

    @Test
    public void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
        Employee employee1 = new Employee("Employee 1", 85000);
        Employee employee2 = new Employee("Employee 2", 125000);
        Employee employee3 = new Employee("Employee 3", 140000);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.flush();

        List<Employee> allEmployees = employeeRepository.findAll();

        assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(employee1.getName(),
                employee2.getName(), employee3.getName());
    }

}
