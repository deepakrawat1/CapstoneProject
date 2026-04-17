package com.deepak.usermanagementservice.dto.request;

import com.deepak.usermanagementservice.validation.annotation.ValidGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name size must be between 3 and 50")
    private String name;
    @NotBlank(message = "Gender is required")
    @ValidGender
    private String gender;
}
