package com.College_directory.Springboot_first_app.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String name;
    private Long departmentId;
    private Long facultyId;

    public String getDescription() {
        return "";
    }

    public String getTitle() {
        return "";
    }
}
