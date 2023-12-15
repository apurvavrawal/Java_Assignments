package com.jspnlp.assignment_9.service;

import com.jspnlp.assignment_9.bean.Employee;
import com.jspnlp.assignment_9.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employee){
        this.employeeRepository= employee;
    }

    @Override
    public List<Employee> getEmployees(String query) {
        List<Employee> employeeList = employeeRepository.getAllSalaryDetails(query);
        return employeeList;
    }

    @Override
    public EmployeeService createEmployee(Employee employee) {
        return (EmployeeService) employeeRepository.save(employee);
    }
}
