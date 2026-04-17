package com.deepak.usermanagementservice.controllers;

import com.deepak.usermanagementservice.dto.ApiResponse;
import com.deepak.usermanagementservice.dto.ApiResponseData;
import com.deepak.usermanagementservice.dto.request.LoginRequest;
import com.deepak.usermanagementservice.dto.request.RegisterRequest;
import com.deepak.usermanagementservice.dto.request.UpdateProfileRequest;
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
    public ResponseEntity<ApiResponseData<GetProfileResponse>> profile(@RequestHeader("Authorization") String req){
        String token = req.split(" ")[1];
        GetProfileResponse res = _userService.getProfile(token);
        return ResponseUtil.success(res, HttpStatus.OK);
    }

    @PutMapping("/updateprofile")
    public ResponseEntity<ApiResponse> updateProfile(@RequestHeader("Authorization") String authHeader,@Valid @RequestBody UpdateProfileRequest req){
        String token = authHeader.split(" ")[1];
        _userService.updateProfile(token, req);
        return ResponseUtil.success(HttpStatus.OK);
    }
}
