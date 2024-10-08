package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.dto.dashboard.EnrollmentTrendDTO;
import com.College_directory.Springboot_first_app.dto.dashboard.FacultyCourseLoadDTO;

import java.util.List;

public interface DashboardInterface {
    List<EnrollmentTrendDTO> getEnrollmentTrends();

List<FacultyCourseLoadDTO> getFacultyCourseLoads();
}
