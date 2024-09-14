package com.College_directory.Springboot_first_app.dto;

import com.College_directory.Springboot_first_app.model.Department;
import lombok.Data;

@Data
public class AdministratorProfileDTO {
    private Long userId;
    private String name;
    private String department;
    private String photoUrl;

    public String getPhoto() {
        return "";
    }

    public Department getDepartmentId() {
        return null;
    }
}
