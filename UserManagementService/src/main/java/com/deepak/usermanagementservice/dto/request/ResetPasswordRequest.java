package com.deepak.usermanagementservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password size must be between 6 and 50")
    private String password;
}
