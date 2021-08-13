package com.unosquare.employee.service;

import com.unosquare.employee.model.Employee;
import com.unosquare.employee.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService();
        }
    }

    @Test
    public void testSave() {
        Employee employeeRecord = this.getEmployeeRecord();
        Mockito.when(employeeRepository.save(getEmployeeRecord())).thenReturn(employeeRecord);
        Employee emp = employeeService.saveEmployee(employeeRecord);
        assertThat(emp.getId()).isEqualTo(employeeRecord.getId());
    }

    @Test
    public void testGetById() {
        Employee employeeRecord = this.getEmployeeRecord();
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeRecord));
        Optional<Employee> emp = employeeService.getEmployeeById(1L);
        assertThat(emp.get().getId()).isEqualTo(employeeRecord.getId());
    }

    @Test
    public void testGetAll() {
        Employee employeeRecord = this.getEmployeeRecord();
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeRecord);
        employees.add(new Employee());
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> emps = employeeService.getAllEmployees();
        assertThat(emps.size()).isEqualTo(2);
    }

    @Test
    public void testDelete() {
        employeeService.deleteEmployee(1L);
        doNothing().when(employeeRepository).deleteById(isA(Long.class));
        verify(employeeRepository, Mockito.times(1)).deleteById(1L);
    }

    private Employee getEmployeeRecord() {
        Employee employee = new Employee();
        employee.setPhone("1111111111º");
        employee.setOffice("315E");
        employee.setRole("admin");
        employee.setEmail("mail@domain.com");
        employee.setName("John Smith");
        employee.setId(1L);
        return employee;
    }

}
