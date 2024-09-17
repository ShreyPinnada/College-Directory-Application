package com.College_directory.Springboot_first_app.dto.enrollment;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private Long id;
    private Long studentId;
    private Long courseId;
}
