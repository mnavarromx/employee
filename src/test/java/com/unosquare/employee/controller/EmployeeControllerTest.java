package com.unosquare.employee.controller;

import com.unosquare.employee.model.Employee;
import com.unosquare.employee.service.EmployeeService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    EmployeeController employeeController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenUserControllerInjected_thenNotNull() throws Exception {
        assertThat(employeeController).isNotNull();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/all")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void testCreateSuccess() throws Exception {
        Employee emp = new Employee();
        Mockito.when(employeeService.saveEmployee(Mockito.any())).thenReturn(emp);
        MediaType jsonMedia = new MediaType(MediaType.APPLICATION_JSON);
        String employee = "{\"name\": \"John\", \"email\" : \"john@domain.com\", \"office\" : " +
            "\"200C\", \"phone\" : \"2332222222\", \"role\" : \"admin\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/create")
            .content(employee)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType(jsonMedia));
    }

    @Test
    public void testCreateBadOffice() throws Exception {
        Employee emp = new Employee();
        Mockito.when(employeeService.saveEmployee(Mockito.any())).thenReturn(emp);
        MediaType jsonMedia = new MediaType(MediaType.APPLICATION_JSON);
        String employee = "{\"name\": \"John\", \"email\" : \"john@domain.com\", \"office\" : " +
            "\"200H\", \"phone\" : \"2332222222\", \"role\" : \"admin\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/create")
            .content(employee)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().contentType(jsonMedia));
    }

    @Test
    public void testGetById() throws Exception {
        Employee emp = new Employee();
        Mockito.when(employeeService.getEmployeeById(Mockito.anyLong())).thenReturn(Optional.of(emp));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateSuccess() throws Exception {
        Mockito.when(employeeService.getEmployeeById(Mockito.anyLong())).thenReturn(Optional.of(getEmployeeRecord()));
        MediaType jsonMedia = new MediaType(MediaType.APPLICATION_JSON);
        String employee = "{\"name\": \"John updated\", \"email\" : \"john@domain.com\", \"office\" : " +
            "\"200A\", \"phone\" : \"2332222222\", \"role\" : \"admin\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/employee/update/1")
            .content(employee)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("John updated")))
            .andExpect(MockMvcResultMatchers.content().contentType(jsonMedia));
    }

    public void testUpdateFail() throws Exception {
        Mockito.when(employeeService.getEmployeeById(Mockito.anyLong())).thenReturn(Optional.of(getEmployeeRecord()));
        MediaType jsonMedia = new MediaType(MediaType.APPLICATION_JSON);
        String employee = "{\"email\" : \"john@domain.com\", \"office\" : " +
            "\"200A\", \"phone\" : \"2332222222\", \"role\" : \"admin\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/employee/update/1")
            .content(employee)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Name is mandatory")))
            .andExpect(MockMvcResultMatchers.content().contentType(jsonMedia));
    }

    private Employee getEmployeeRecord() {
        Employee employee = new Employee();
        employee.setPhone("1111111111º");
        employee.setOffice("315E");
        employee.setRole("admin");
        employee.setEmail("mail@domain.com");
        employee.setName("John Smith");
        return employee;
    }
}
