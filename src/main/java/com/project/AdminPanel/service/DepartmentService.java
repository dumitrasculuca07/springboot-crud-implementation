package com.project.AdminPanel.service;


import com.project.AdminPanel.entity.Department;
import com.project.AdminPanel.mapper.DepartmentMapper;
import com.project.AdminPanel.model.DepartmentDTO;
import com.project.AdminPanel.model.EmployeeDTO;
import com.project.AdminPanel.repository.DepartmentRepository;
import com.project.AdminPanel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentMapper departmentMapper;


    public List<DepartmentDTO> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departmentMapper.getDepartmentDtoList(departments);
    }

    public DepartmentDTO findDepartmentById(int id) {
        Department department = departmentRepository.findById(id).get();
        return departmentMapper.mapToDepartmentDTO(department);
    }

    public void saveDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.mapToDepartment(departmentDTO);
        departmentRepository.save(department);
    }

    public void deleteDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.mapToDepartment(departmentDTO);
        departmentRepository.delete(department);
    }

    public void deleteDepartmentById(int id) {
        departmentRepository.deleteById(id);
    }

}
