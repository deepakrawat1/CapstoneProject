package com.deepak.usermanagementservice.dto.request;

import com.deepak.usermanagementservice.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50)
    private String password;

    public User convertToUser() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
