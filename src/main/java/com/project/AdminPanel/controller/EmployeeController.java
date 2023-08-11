package com.project.AdminPanel.controller;

import com.project.AdminPanel.mapper.DepartmentMapper;
import com.project.AdminPanel.model.DepartmentDTO;
import com.project.AdminPanel.model.EmployeeDTO;
import com.project.AdminPanel.service.DepartmentService;
import com.project.AdminPanel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping(value = "department/{id_url}")
    public String employeeOverview(@PathVariable("id_url") int id, Model model) {

        List<EmployeeDTO> employeeList = employeeService.getEmployees();
        employeeList = employeeList.stream()
                .filter(employee
                        -> employee.getDepartment().getId() == departmentService.findDepartmentById(id).getId())
                .toList();

        DepartmentDTO departmentDTO = departmentService.findDepartmentById(id);

        if(employeeList.size()>0) {
            model.addAttribute("title",departmentDTO.getName());
            model.addAttribute("employees", employeeList);
            model.addAttribute("department", departmentService.findDepartmentById(id));
        }
        else {
            model.addAttribute("title", "This department doesn't have any employees");
            model.addAttribute("employees", "null");
            model.addAttribute("department", departmentService.findDepartmentById(id));
        }

        return "employeeOverview";
    }

    @GetMapping(value = "/department/{id_url}/add")
    public String addEmployee(@PathVariable("id_url") int id, Model model) {

        DepartmentDTO departmentDTO = departmentService.findDepartmentById(id);
        departmentDTO.setNumberOfEmployees(departmentDTO.getNumberOfEmployees()+1);
        departmentService.saveDepartment(departmentDTO);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartment(departmentMapper.mapToDepartment(departmentService.findDepartmentById(id)));

        model.addAttribute("employee", employeeDTO);
        model.addAttribute("department", departmentDTO);

        return "employeeForm";

    }

    @GetMapping(value = "/department/{id_url}/editEmployee")
    public String editEmployee(@PathVariable("id_url") int id, Model model) {

        EmployeeDTO employeeDTO = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employeeDTO);

        return "employeeForm";

    }

    @PostMapping(value = "/department/{id_url}")
    public String submitEmployee(@PathVariable("id_url") int id,
                                   @ModelAttribute("employee")
                                   EmployeeDTO employeeDTO) {

        employeeService.saveEmployee(employeeDTO);

        return String.format("redirect:/department/%d",id);

    }

    @GetMapping(value = "/department/{id_url}/deleteEmployee")
    public String deleteEmployee(@PathVariable("id_url") int id) {

        DepartmentDTO departmentDTO = departmentService
                .findDepartmentById(employeeService
                        .findEmployeeById(id).getDepartment().getId());

        departmentDTO.setNumberOfEmployees(departmentDTO.getNumberOfEmployees()-1);

        departmentService.saveDepartment(departmentDTO);
        employeeService.deleteEmployeeById(id);

        return String.format("redirect:/department/%d",departmentDTO.getId());

    }

}
