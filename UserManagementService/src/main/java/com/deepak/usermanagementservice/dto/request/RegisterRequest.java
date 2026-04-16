package com.deepak.usermanagementservice.dto.request;
import com.deepak.usermanagementservice.validation.annotation.ValidGender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name size must be between 3 and 50")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password size must be between 6 and 50")
    private String password;
    @NotBlank(message = "Gender is required")
    @ValidGender
    private String gender;
}
