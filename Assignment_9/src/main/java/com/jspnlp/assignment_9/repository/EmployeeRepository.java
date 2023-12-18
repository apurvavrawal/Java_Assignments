package com.jspnlp.assignment_9.repository;

import com.jspnlp.assignment_9.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM EmployeeDetails",nativeQuery = true)
    List<Employee> getAllSalaryDetails();
}
