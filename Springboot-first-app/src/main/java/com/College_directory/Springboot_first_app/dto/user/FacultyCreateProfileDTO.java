package com.College_directory.Springboot_first_app.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class FacultyCreateProfileDTO extends UserDTO {
    private String photo;
    private Long departmentId;
    private String officeHours;
}
