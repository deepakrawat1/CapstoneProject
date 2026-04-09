package com.deepak.usermanagementservice.controllers;

import com.deepak.usermanagementservice.dto.ApiResponse;
import com.deepak.usermanagementservice.dto.request.RegisterRequest;
import com.deepak.usermanagementservice.services.UserService;
import com.deepak.usermanagementservice.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService _userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest userDto) {
        _userService.register(userDto);
        return ResponseUtil.success(HttpStatus.CREATED);
    }


}
