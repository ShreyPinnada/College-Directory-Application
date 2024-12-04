package com.College_directory.Springboot_first_app.controller;


import com.College_directory.Springboot_first_app.dto.dashboard.EnrollmentTrendDTO;
import com.College_directory.Springboot_first_app.dto.dashboard.FacultyCourseLoadDTO;
import com.College_directory.Springboot_first_app.service.DashboardInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardInterface dashboardInterface;

    public DashboardController(DashboardInterface dashboardInterface) {
        this.dashboardInterface = dashboardInterface;
    }
    
    @GetMapping("/enrollment-trends")
    public ResponseEntity<List<EnrollmentTrendDTO>> getEnrollmentTrends() {
        return ResponseEntity.ok(dashboardInterface.getEnrollmentTrends());
    }

    @GetMapping("/faculty-course-loads")
    public ResponseEntity<List<FacultyCourseLoadDTO>> getFacultyCourseLoads() {
        return ResponseEntity.ok(dashboardInterface.getFacultyCourseLoads());
    }

}
