package com.College_directory.Springboot_first_app.dto.user;

import com.College_directory.Springboot_first_app.dto.user.UserDTO;
import lombok.Data;

@Data
public class FacultyProfileDTO extends UserDTO {
    private String photo;
    private Long departmentId;
    private String officeHours;
    private String facultyName;
    private String departmentName;
}
