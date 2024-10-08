package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.dashboard.EnrollmentTrendDTO;
import com.College_directory.Springboot_first_app.dto.dashboard.FacultyCourseLoadDTO;
import com.College_directory.Springboot_first_app.repository.CourseRepository;
import com.College_directory.Springboot_first_app.repository.EnrollmentRepository;
import com.College_directory.Springboot_first_app.service.DashboardInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardImplement implements DashboardInterface {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    public DashboardImplement(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    public List<EnrollmentTrendDTO> getEnrollmentTrends() {
        return enrollmentRepository.getEnrollmentTrends();
    }

    public List<FacultyCourseLoadDTO> getFacultyCourseLoads() {
        return courseRepository.getFacultyCourseLoads();
    }
}