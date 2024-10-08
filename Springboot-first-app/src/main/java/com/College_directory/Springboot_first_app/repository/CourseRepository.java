package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.dto.dashboard.FacultyCourseLoadDTO;
import com.College_directory.Springboot_first_app.model.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTitle(@NotBlank @Size(max = 100) String title);

    List<Course> findAllByDepartmentId(Long departmentId);

    List<Course> findAllByFaculty_UserId(Long facultyId);

    @Query("SELECT new com.College_directory.Springboot_first_app.dto.dashboard.FacultyCourseLoadDTO(c.faculty.user.name, COUNT(c)) " +
            "FROM Course c GROUP BY c.faculty.user.id, c.faculty.user.name ORDER BY COUNT(c) DESC")
    List<FacultyCourseLoadDTO> getFacultyCourseLoads();
}