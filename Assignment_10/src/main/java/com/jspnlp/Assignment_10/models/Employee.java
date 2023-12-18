package com.jspnlp.Assignment_10.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EmployeeDetails")
public class Employee {
    @Id
    private Long id;
    private String name;
    private Integer salary;
}
