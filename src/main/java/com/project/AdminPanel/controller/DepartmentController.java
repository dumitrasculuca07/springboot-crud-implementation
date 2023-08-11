package com.project.AdminPanel.controller;

import com.project.AdminPanel.model.DepartmentDTO;
import com.project.AdminPanel.model.EmployeeDTO;
import com.project.AdminPanel.service.DepartmentService;
import com.project.AdminPanel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/departmentOverview")
    public String departmentOverview(Model model){

        List<DepartmentDTO> departments = departmentService.getDepartments();

        if(departments.size()>0) {
            model.addAttribute("title","Department Panel");
            model.addAttribute("departments", departments);
        }
        else {
            model.addAttribute("title","There isn't any department to show");
            model.addAttribute("departments", "null");
        }

        return "departmentOverview";
    }

    @GetMapping(value = "/department/{id_url}/edit")
    public String editDepartment(@PathVariable("id_url") int id, Model model) {

        DepartmentDTO departmentDTO = departmentService.findDepartmentById(id);
        model.addAttribute("department", departmentDTO);
        model.addAttribute("title", "Edit department form: ");

        return "departmentForm";

    }

    @GetMapping(value = "/department/add")
    public String addDepartment(Model model) {

        model.addAttribute("department", new DepartmentDTO());
        model.addAttribute("title", "Add department form: ");

        return "departmentForm";

    }

    @PostMapping(value = "/departmentOverview")
    public String submitDepartment(@ModelAttribute("department") DepartmentDTO departmentDTO) {

        departmentService.saveDepartment(departmentDTO);

        return "redirect:/departmentOverview";

    }

    @GetMapping(value = "/department/{id_url}/delete")
    public String deleteDepartment(@PathVariable("id_url") int id) {

        List<EmployeeDTO> employeeList = employeeService.getEmployees();
        employeeList = employeeList.stream()
                .filter(employee
                        -> employee.getDepartment().getId() == departmentService.findDepartmentById(id).getId())
                .toList();

        if(employeeList.size()>0) {
            for (EmployeeDTO employeeDTO : employeeList) {
                employeeService.deleteEmployee(employeeDTO);
            }
        }
        departmentService.deleteDepartmentById(id);

        return "redirect:/departmentOverview";

    }

}
