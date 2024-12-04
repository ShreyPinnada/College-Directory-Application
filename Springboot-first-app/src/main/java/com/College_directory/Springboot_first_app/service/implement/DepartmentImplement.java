package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.department.DepartmentCreateDTO;
import com.College_directory.Springboot_first_app.dto.department.DepartmentUpdateDTO;
import com.College_directory.Springboot_first_app.model.Department;
import com.College_directory.Springboot_first_app.repository.DepartmentRepository;
import com.College_directory.Springboot_first_app.service.DepartmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentImplement implements DepartmentInterface {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(DepartmentCreateDTO departmentCreateDTO) {
        Department department = departmentRepository.findByName(departmentCreateDTO.getName());
        if (department != null) {
            throw new IllegalArgumentException("Department with name " + departmentCreateDTO.getName() + " already exists");
        }
        Department newDepartment = new Department();
        newDepartment.setName(departmentCreateDTO.getName());
        newDepartment.setDescription(departmentCreateDTO.getDescription());
        return departmentRepository.save(newDepartment);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Long id, DepartmentUpdateDTO departmentUpdateDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));

        if (departmentUpdateDTO.getName() != null) {
            if (!departmentUpdateDTO.getName().equals(department.getName())) {
                Department existingDepartment = departmentRepository.findByName(departmentUpdateDTO.getName());
                if (existingDepartment != null) {
                    throw new IllegalArgumentException("Department with name " + departmentUpdateDTO.getName() + " already exists");
                }
                department.setName(departmentUpdateDTO.getName());
            }
        }

        if (departmentUpdateDTO.getDescription() != null) {
            department.setDescription(departmentUpdateDTO.getDescription());
        }

        return departmentRepository.save(department);
    }

    @Override
    public boolean deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));
        departmentRepository.delete(department);
        return true;
    }
}