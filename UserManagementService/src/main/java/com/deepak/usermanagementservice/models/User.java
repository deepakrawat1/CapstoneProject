package com.deepak.usermanagementservice.models;

import com.deepak.usermanagementservice.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseModel {
    @Column(nullable = false, length = 100)
    private String name;
    @Column(unique = true, length = 150, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Gender Gender;
    @Column(nullable = false)
    private boolean isDeleted;
    @Column(nullable = false)
    private boolean isActive;
//    private String provider;
//    private String providerId;
}
