package com.jspnlp.Assignment_10.services;

import com.jspnlp.Assignment_10.models.Employee;

public interface EmployeeService {
    Iterable<Employee> getEmployees();

    Employee getEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long id);
}
