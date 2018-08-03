package demo.springboottest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import demo.springboottest.model.Employee;
import demo.springboottest.repository.EmployeeRepository;
import demo.springboottest.service.EmployeeNotFoundException;
import demo.springboottest.service.EmployeeService;
import demo.springboottest.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceUnitTest {

    @TestConfiguration
    static class EmployeeServiceTestContextConfiguration {
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        Employee employee1 = new Employee("Employee 1", 65000);
        employee1.setId(111L);

        Employee employee2 = new Employee("Employee 2", 75000);
        Employee employee3 = new Employee("Employee 3", 85000);

        List<Employee> allEmployees = Arrays.asList(employee1, employee2, employee3);

        Mockito.when(employeeRepository.findByName(employee1.getName())).thenReturn(employee1);

        Mockito.when(employeeRepository.findByName(employee2.getName())).thenReturn(employee2);

        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);

        Mockito.when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.of(employee1));

        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "Employee 2";
        Employee found = employeeService.getEmployeeByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void whenInvalidName_thenEmployeeShouldNotBeFound() {
        employeeService.getEmployeeByName("wrong_name");
        verifyFindByNameIsCalledOnce("wrong_name");
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        Employee fromDb = employeeService.getEmployeeById(111L);
        assertThat(fromDb.getName()).isEqualTo("Employee 1");

        verifyFindByIdIsCalledOnce();
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void whenInvalidId_thenEmployeeShouldNotBeFound() {
        employeeService.getEmployeeById(-99L);
        // assertThat(fromDb).isNull();
        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void given3Employees_whenGetAll_thenReturn3Records() {
        Employee employee1 = new Employee("Employee 1", 65000);
        Employee employee2 = new Employee("Employee 2", 75000);
        Employee employee3 = new Employee("Employee 3", 85000);

        List<Employee> allEmployees = employeeService.getAllEmployees();
        assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(employee1.getName(),
                employee2.getName(), employee3.getName());

        verifyFindAllEmployeesIsCalledOnce();
    }

    private void verifyFindByNameIsCalledOnce(String name) {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findByName(name);
        Mockito.reset(employeeRepository);
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.reset(employeeRepository);
    }

    private void verifyFindAllEmployeesIsCalledOnce() {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(employeeRepository);
    }

}
