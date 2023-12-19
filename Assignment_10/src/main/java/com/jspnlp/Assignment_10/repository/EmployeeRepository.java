package com.jspnlp.Assignment_10.repository;

import com.jspnlp.Assignment_10.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
