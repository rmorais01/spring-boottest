package demo.springboottest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

import demo.springboottest.controller.EmployeeRestController;
import demo.springboottest.model.Employee;
import demo.springboottest.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeRestController.class)
public class EmployeeControllerUnitTest {

		@Autowired
		private MockMvc mvc;

		@MockBean
		private EmployeeService service;

		@Before
		public void setUp() throws Exception {
		}

		@Test
		public void whenPostEmployee_thenCreateEmployee() throws Exception {
			Employee employee1 = new Employee("Employee 1");
			employee1.setId(100L);
			given(service.addEmployee(ArgumentMatchers.any(Employee.class))).willReturn(employee1);
			
			MvcResult mvcResult = mvc.perform(post("/rmtest/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(employee1))).andReturn(); 
			 
			String headerValue = mvcResult.getResponse().getHeader("location");
			assertThat(mvcResult.getResponse().getStatus() == HttpStatus.CREATED.ordinal());
			assertThat(headerValue.endsWith("/rmtest/employees/100"));
			    
	 		verify(service, VerificationModeFactory.times(1)).addEmployee(ArgumentMatchers.any(Employee.class));
			reset(service);
		}
		

	    @Test
	    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
	        Employee employee1 = new Employee("Employee 1");
	        Employee employee2 = new Employee("Employee 2");
	        Employee employee3 = new Employee("Employee 3");

	        List<Employee> allEmployees = Arrays.asList(employee1, employee2, employee3);

	        given(service.getAllEmployees()).willReturn(allEmployees);

	        mvc.perform(get("/rmtest/employees").contentType(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
	        	.andExpect(jsonPath("$[0].name", is(employee1.getName()))).andExpect(jsonPath("$[1].name", is(employee2.getName())))
	            .andExpect(jsonPath("$[2].name", is(employee3.getName())));
	        verify(service, VerificationModeFactory.times(1)).getAllEmployees();
	        reset(service);
	    }

}		 