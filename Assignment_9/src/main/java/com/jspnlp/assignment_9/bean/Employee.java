package com.jspnlp.assignment_9.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name="EmployeeDetails")
public class Employee {
    @Id
    private Long id;
    private String name;
    private int salary;
    public Employee(){}

    public Employee(Long id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Long getEmpId() {
        return id;
    }

    public void setEmpId(Long empId) {
        this.id = empId;
    }

    public String getEmpName() {
        return name;
    }

    public void setEmpName(String empName) {
        this.name = empName;
    }

    public int getEmpSalary() {
        return salary;
    }

    public void setEmpSalary(int empSalary) {
        this.salary = empSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + id +
                ", empName='" + name + '\'' +
                ", empSalary=" + salary +
                '}';
    }
}
