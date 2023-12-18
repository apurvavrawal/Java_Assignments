package com.jspnlp.assignment_9.service;

import com.jspnlp.assignment_9.model.Employee;
import com.jspnlp.assignment_9.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employee){
        this.employeeRepository= employee;
    }

    @Override
    public List<Employee> getEmployees(String query) {
        List<Employee> employeeList = employeeRepository.getAllSalaryDetails();
        return employeeList;
    }
}
