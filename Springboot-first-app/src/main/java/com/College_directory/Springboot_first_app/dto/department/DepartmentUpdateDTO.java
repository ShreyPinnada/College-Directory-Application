package com.College_directory.Springboot_first_app.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentUpdateDTO {
    @NotBlank
    @Size(max = 100)
    private String name;
    private String description;
}
