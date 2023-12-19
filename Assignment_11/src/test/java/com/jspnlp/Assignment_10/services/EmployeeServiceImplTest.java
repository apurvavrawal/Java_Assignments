package com.jspnlp.Assignment_10.services;

import com.jspnlp.Assignment_10.models.Employee;
import com.jspnlp.Assignment_10.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testGetEmployees() {
        Employee employee = new Employee(1L, "Pooja",34000);
        Employee employee2 = new Employee(2L, "Asmita",36000);
        List<Employee> employeeList= new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee2);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        Iterable<Employee> employees = employeeService.getEmployees();
        assertNotNull(employees);
    }

    @Test
    public void testGetEmployeeById() {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee(employeeId, "Samiksha", 30000);
        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(mockEmployee));

        Employee resultEmployee = employeeService.getEmployeeById(employeeId);
        assertEquals(mockEmployee, resultEmployee);
    }

    @Test
    public void testCreateEmployee() {
        Employee newEmployee = new Employee(1L,"Apurva",34000);

        Mockito.when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);
        Employee createdEmployee = employeeService.createEmployee(newEmployee);
        assertNotNull(createdEmployee);
    }

    @Test
    public void testUpdateEmployee() {
        Employee existingEmployee = new Employee();
        existingEmployee.setId(2L);
        existingEmployee.setName("Apurva");
        existingEmployee.setSalary(36000);
        Mockito.when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        Employee updatedEmployee = employeeService.updateEmployee(existingEmployee);
        assertNotNull(updatedEmployee);
    }

    @Test
    public void testDeleteEmployee() {

        Long employeeId = 1L;
        employeeService.deleteEmployee(employeeId);
        Mockito.verify(employeeRepository).deleteById(employeeId);
    }
}
