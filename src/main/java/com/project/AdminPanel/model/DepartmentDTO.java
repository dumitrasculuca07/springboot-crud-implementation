package com.project.AdminPanel.model;

import com.project.AdminPanel.entity.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDTO {

    private int id;
    private String name;
    private String code;
    private int numberOfEmployees;
    public List<Employee> employees;

}
