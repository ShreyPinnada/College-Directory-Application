package com.College_directory.Springboot_first_app.dto.user;

import com.College_directory.Springboot_first_app.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String username;

    private Role role;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 15)
    private String phone;
}
