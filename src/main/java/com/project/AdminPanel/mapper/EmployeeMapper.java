package com.project.AdminPanel.mapper;

import com.project.AdminPanel.entity.Department;
import com.project.AdminPanel.entity.Employee;
import com.project.AdminPanel.model.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    public List<EmployeeDTO> getEmployeeDtoList(List<Employee> employeeList) {
        return employeeList.stream()
                .map(employee -> mapToEmployeeDTO(employee))
                .toList();
    }

    public EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .department(employee.getDepartment())
                .build();
    }

    public Employee mapToEmployee(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .id(employeeDTO.getId())
                .firstname(employeeDTO.getFirstname())
                .lastname(employeeDTO.getLastname())
                .department(employeeDTO.getDepartment())
                .build();
    }

}
