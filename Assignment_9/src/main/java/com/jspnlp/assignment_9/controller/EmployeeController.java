package com.jspnlp.assignment_9.controller;

import com.jspnlp.assignment_9.model.Employee;
import com.jspnlp.assignment_9.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salaryDetails")
    public ResponseEntity<List<Employee>> getEmployeeDetails(@RequestParam("query") String query){
        return ResponseEntity.ok(employeeService.getEmployees(query));
    }
}
