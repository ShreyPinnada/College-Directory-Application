package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
