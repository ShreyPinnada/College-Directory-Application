package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.dto.department.DepartmentCreateDTO;
import com.College_directory.Springboot_first_app.dto.department.DepartmentUpdateDTO;
import com.College_directory.Springboot_first_app.model.Department;

import java.util.List;

public interface DepartmentInterface {
    // Create
    Department createDepartment(DepartmentCreateDTO departmentCreateDTO);

    // Read
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();

    // Update
    Department updateDepartment(Long id, DepartmentUpdateDTO departmentUpdateDTO);

    // Delete
    boolean deleteDepartment(Long id);

    // Other
//    List<StudentProfile> getDepartmentStudents(Long departmentId);
//    List<FacultyProfile> getDepartmentFaculty(Long departmentId);
//    List<Course> getDepartmentCourses(Long departmentId);
}
