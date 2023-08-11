package com.project.AdminPanel.service;


import com.project.AdminPanel.entity.Employee;
import com.project.AdminPanel.mapper.EmployeeMapper;
import com.project.AdminPanel.model.EmployeeDTO;
import com.project.AdminPanel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeMapper employeeMapper;


    public List<EmployeeDTO> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeMapper.getEmployeeDtoList(employeeList);
    }

    public EmployeeDTO findEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).get();
        return employeeMapper.mapToEmployeeDTO(employee);
    }

    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeMapper.mapToEmployee(employeeDTO));
    }

    public void deleteEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.delete(employeeMapper.mapToEmployee(employeeDTO));
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

}
