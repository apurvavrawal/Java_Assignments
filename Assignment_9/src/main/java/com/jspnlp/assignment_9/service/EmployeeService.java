package com.jspnlp.assignment_9.service;

import com.jspnlp.assignment_9.model.Employee;

import java.util.List;


public interface EmployeeService {

    List<Employee> getEmployees(String query);
}
