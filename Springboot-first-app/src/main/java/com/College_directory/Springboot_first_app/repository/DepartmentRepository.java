package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.model.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(@NotBlank @Size(max = 100) String name);
}
