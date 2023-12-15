package com.jspnlp.assignment_9.controller;

import com.jspnlp.assignment_9.bean.Employee;
import com.jspnlp.assignment_9.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employee) {
        this.employeeService = employee;
    }
    @GetMapping("/salaryDetails")
    public ResponseEntity<List<Employee>> getEmployeeDetails(@RequestParam("query") String query){
        return ResponseEntity.ok(employeeService.getEmployees(query));
    }

    @PostMapping
    public EmployeeService createEmployee(Employee employee){
        return employeeService.createEmployee(employee);
    }
}
