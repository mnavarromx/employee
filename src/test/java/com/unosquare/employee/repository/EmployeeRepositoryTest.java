package com.unosquare.employee.repository;

import com.unosquare.employee.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findByIdUnexistent() {
        Optional<Employee> optEmployee = employeeRepository.findById(1000L);

        assertThat(optEmployee.isPresent()).isEqualTo(false);
    }

    @Test
    public void saveAndFindEmployee() {
        Employee employee = getEmployeeRecord();
        employeeRepository.save(employee);
        Optional<Employee> optEmployee = employeeRepository.findById(employee.getId());
        assertThat(optEmployee.isPresent()).isEqualTo(true);
        assertThat(optEmployee.get().getName()).isEqualTo("Test Employee");
    }

    @Test
    public void deleteEmployee() {
        Employee employee = getEmployeeRecord();
        employeeRepository.save(employee);
        Optional<Employee> optEmployee = employeeRepository.findById(employee.getId());
        assertThat(optEmployee.isPresent()).isEqualTo(true);
        employeeRepository.deleteById(employee.getId());
        optEmployee = employeeRepository.findById(employee.getId());
        assertThat(optEmployee.isPresent()).isEqualTo(false);
    }

    private Employee getEmployeeRecord() {
        Employee employee = new Employee();
        employee.setPhone("1111111111º");
        employee.setOffice("315E");
        employee.setRole("admin");
        employee.setEmail("mail@domain.com");
        employee.setName("Test Employee");
        return employee;
    }

}
