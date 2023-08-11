package com.project.AdminPanel.mapper;

import com.project.AdminPanel.entity.Department;
import com.project.AdminPanel.model.DepartmentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentMapper {

    public List<DepartmentDTO> getDepartmentDtoList(List<Department> departmentList) {
        return departmentList.stream()
                .map(department -> mapToDepartmentDTO(department))
                .toList();
    }

    public DepartmentDTO mapToDepartmentDTO(Department department) {
        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .code(department.getCode())
                .numberOfEmployees(department.getNumberOfEmployees())
                .employees(department.getEmployees())
                .build();
    }

    public Department mapToDepartment(DepartmentDTO departmentDto) {
        return Department.builder()
                .id(departmentDto.getId())
                .name(departmentDto.getName())
                .code(departmentDto.getCode())
                .numberOfEmployees(departmentDto.getNumberOfEmployees())
                .employees(departmentDto.getEmployees())
                .build();
    }
}
