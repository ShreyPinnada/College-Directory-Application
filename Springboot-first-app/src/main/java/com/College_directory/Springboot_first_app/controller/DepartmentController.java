package com.College_directory.Springboot_first_app.controller;

import com.College_directory.Springboot_first_app.dto.department.DepartmentCreateDTO;
import com.College_directory.Springboot_first_app.dto.department.DepartmentUpdateDTO;
import com.College_directory.Springboot_first_app.model.Department;
import com.College_directory.Springboot_first_app.service.DepartmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentInterface departmentInterface;

    public DepartmentController(DepartmentInterface departmentInterface) {
        this.departmentInterface = departmentInterface;
    }

    @PostMapping
    ResponseEntity<Department> createDepartment(@RequestBody DepartmentCreateDTO departmentCreateDTO) {
        Department department = departmentInterface.createDepartment(departmentCreateDTO);
        return ResponseEntity.ok(department);
    }

    @PatchMapping("{id}")
    ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody DepartmentUpdateDTO departmentUpdateDTO) {
        Department department = departmentInterface.updateDepartment(id, departmentUpdateDTO);
        return ResponseEntity.ok(department);
    }

    @GetMapping("{id}")
    ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentInterface.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping
    ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentInterface.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @DeleteMapping("{id}")
    ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        boolean result = departmentInterface.deleteDepartment(id);
        if (result) {
            return ResponseEntity.ok("Department deleted successfully");
        } else {
            return ResponseEntity.status(400).body("Department deletion failed");
        }
    }

}