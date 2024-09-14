package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.dto.DepartmentDTO;
import com.College_directory.Springboot_first_app.model.Course;
import com.College_directory.Springboot_first_app.model.Department;
import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.model.StudentProfile;

import java.util.List;

public interface DepartmentInterface {
    // Create
    Department createDepartment(DepartmentDTO departmentDTO);

    // Read
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();

    // Update
    Department updateDepartment(Long id, DepartmentDTO departmentDTO);

    // Delete
    boolean deleteDepartment(Long id);

    // Other
    List<StudentProfile> getDepartmentStudents(Long departmentId);
    List<FacultyProfile> getDepartmentFaculty(Long departmentId);
    List<Course> getDepartmentCourses(Long departmentId);
}
