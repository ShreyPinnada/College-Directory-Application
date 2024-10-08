package com.College_directory.Springboot_first_app.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FacultyCourseLoadDTO {
    private String facultyName;
    private Long courseCount;
}
