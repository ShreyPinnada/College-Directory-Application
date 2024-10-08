package com.College_directory.Springboot_first_app.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseUpdateDTO {

    @Size(max = 100)
    private String title;

    private String description;


    private Long departmentId;


    private Long facultyId;
}
