package com.College_directory.Springboot_first_app.dto;

import com.College_directory.Springboot_first_app.Role;
import com.College_directory.Springboot_first_app.model.User;
import lombok.Data;


import java.io.Serializable;

@Data
public class UserDTO {
    private String username;
    private String password; // Only used for registration/password change
    private Role role;
    private String name;
    private String email;
    private String phone;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
