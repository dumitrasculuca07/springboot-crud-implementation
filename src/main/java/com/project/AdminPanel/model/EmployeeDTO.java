package com.project.AdminPanel.model;

import com.project.AdminPanel.entity.Department;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private int id;
    private String firstname;
    private String lastname;
    private Department department;
}
