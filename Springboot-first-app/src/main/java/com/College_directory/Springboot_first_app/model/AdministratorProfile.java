package com.College_directory.Springboot_first_app.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "administratorprofile")
public class AdministratorProfile {
    @Id
    private Long Id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public Long getUserId() {
        return Id;
    }

    public void setUserId(Long userId) {
        this.Id = userId;
    }

    public void setDepartmentId(Department departmentId) {
    }
}