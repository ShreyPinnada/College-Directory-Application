package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.dto.dashboard.EnrollmentTrendDTO;
import com.College_directory.Springboot_first_app.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByStudent_UserId(Long studentId);

    List<Enrollment> findAllByCourse_Id(Long courseId);

    Optional<Enrollment> findByStudent_UserIdAndCourse_Id(Long studentId, Long courseId);

    @Query("SELECT new com.College_directory.Springboot_first_app.dto.dashboard.EnrollmentTrendDTO(e.student.year, COUNT(e)) " +
            "FROM Enrollment e GROUP BY e.student.year ORDER BY e.student.year")
    List<EnrollmentTrendDTO> getEnrollmentTrends();

}
