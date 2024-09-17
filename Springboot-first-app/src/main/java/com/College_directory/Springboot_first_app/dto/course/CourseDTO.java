package com.College_directory.Springboot_first_app.dto.course;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private Long departmentId;
    private Long facultyId;
}
