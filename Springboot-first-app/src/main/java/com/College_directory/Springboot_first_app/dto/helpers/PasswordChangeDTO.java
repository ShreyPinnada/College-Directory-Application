package com.College_directory.Springboot_first_app.dto.helpers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordChangeDTO {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
