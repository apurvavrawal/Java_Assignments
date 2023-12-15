package com.jspnlp.assignment_9.service;

import com.jspnlp.assignment_9.bean.Employee;
import com.jspnlp.assignment_9.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    List<Employee> getEmployees(String query);

    EmployeeService createEmployee(Employee employee);
}
