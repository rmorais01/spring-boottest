package demo.springboottest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import demo.springboottest.model.Employee;
import demo.springboottest.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringBoottestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase

public class EmployeeRestControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeRepository repository;

    @After
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateEmployee() throws Exception {
        Employee employee1 = new Employee("Employee 1", 65000);
        mvc.perform(
                post("/rmtest/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(employee1)));

        List<Employee> found = repository.findAll();
        assertThat(found).extracting(Employee::getName).containsOnly("Employee 1");
    }

    @Test
    public void whenInvalidInput_thenStatus401() throws Exception {
        mvc.perform(get("/rmtest/employees/1111")).andExpect(status().is4xxClientError());
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
        createTestEmployee("Employee 3", 55000);
        createTestEmployee("Employee 4", 65000);

        mvc.perform(get("/rmtest/employees").contentType(MediaType.APPLICATION_JSON))
                // .andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("Employee 3"))).andExpect(jsonPath("$[1].name", is("Employee 4")));
    }

    @Test
    public void givenEmployees_whenFindEmployeesBySalary_thenReturnList() throws Exception {
        createTestEmployee("Employee 1", 45000);
        createTestEmployee("Employee 2", 50000);
        createTestEmployee("Employee 3", 55000);
        createTestEmployee("Employee 4", 65000);

        mvc.perform(get("/rmtest/employees/search/salary?low=50000&high=60000").contentType(MediaType.APPLICATION_JSON))
                // .andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("Employee 3"))).andExpect(jsonPath("$[1].name", is("Employee 2")));
    }

    @Test
    public void givenEmployees_whenFindEmployeesBySalaryNoMatch_thenReturnEmptyList() throws Exception {
        createTestEmployee("Employee 1", 45000);
        createTestEmployee("Employee 2", 50000);
        createTestEmployee("Employee 3", 55000);
        createTestEmployee("Employee 4", 65000);

        mvc.perform(get("/rmtest/employees/search/salary?low=70000&high=90000").contentType(MediaType.APPLICATION_JSON))
                // .andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    private void createTestEmployee(String name, Integer salary) {
        Employee emp = new Employee(name, salary);
        repository.saveAndFlush(emp);
    }

}
