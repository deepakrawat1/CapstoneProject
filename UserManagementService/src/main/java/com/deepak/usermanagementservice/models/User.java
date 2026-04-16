package com.deepak.usermanagementservice.models;

import com.deepak.usermanagementservice.enums.AuthProvider;
import com.deepak.usermanagementservice.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseModel {
    @Column(nullable = false, length = 100)
    private String name;
    @Column(unique = true, length = 150, nullable = false)
    private String email;
    @Column(length = 100)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;
    @Column(nullable = false)
    private boolean deleted = false;
    @Column(nullable = false)
    private boolean active = true;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private AuthProvider provider;
    @Column(length = 100)
    private String providerId;
}
