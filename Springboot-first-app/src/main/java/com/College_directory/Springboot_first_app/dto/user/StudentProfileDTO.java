package com.College_directory.Springboot_first_app.dto.user;

import lombok.Data;

@Data
public class StudentProfileDTO extends UserDTO{
    private String photo;
    private Long departmentId;
    private String year;
}
