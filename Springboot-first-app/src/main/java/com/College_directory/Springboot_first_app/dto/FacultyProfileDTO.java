package com.College_directory.Springboot_first_app.dto;

import lombok.Data;

@Data
public class FacultyProfileDTO {
    private Long userId;
    private String name;
    private String department;
    private String officeHours;
    private String photoUrl;
}
