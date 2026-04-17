package com.deepak.usermanagementservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProfileResponse {
    private String name;
    private String email;
    private String gender;
    private String provider;
}
