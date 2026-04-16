package com.deepak.usermanagementservice.dto.request;

import com.deepak.usermanagementservice.validation.annotation.ValidProvider;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String password;
    @NotBlank(message ="Provider is required")
    @ValidProvider
    private String provider;
    private String providerToken;
}
