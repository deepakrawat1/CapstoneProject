package com.deepak.usermanagementservice.controllers;

import com.deepak.usermanagementservice.dto.ApiResponse;
import com.deepak.usermanagementservice.dto.ApiResponseData;
import com.deepak.usermanagementservice.dto.request.*;
import com.deepak.usermanagementservice.dto.response.GetProfileResponse;
import com.deepak.usermanagementservice.services.UserService;
import com.deepak.usermanagementservice.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService _userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest req) {
        _userService.register(req);
        return ResponseUtil.success(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseData<String>> login(@Valid @RequestBody LoginRequest req){
        String token = _userService.login(req);
        return ResponseUtil.success(token, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponseData<GetProfileResponse>> profile(){
        GetProfileResponse res = _userService.getProfile();
        return ResponseUtil.success(res, HttpStatus.OK);
    }

    @PutMapping("/updateprofile")
    public ResponseEntity<ApiResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest req){
        _userService.updateProfile(req);
        return ResponseUtil.success(HttpStatus.OK);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<ApiResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest req){
        _userService.forgotPassword(req);
        return ResponseUtil.success(HttpStatus.OK, "If an account exists, a password reset link has been sent");
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest req, @RequestParam String token){
        _userService.resetPassword(req, token);
        return ResponseUtil.success(HttpStatus.OK, "Password reset successfully");
    }
}
