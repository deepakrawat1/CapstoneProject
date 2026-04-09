package com.deepak.usermanagementservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseModel {
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean isDeleted;
    private boolean isActive;
    private String provider;
    private String providerId;
}
