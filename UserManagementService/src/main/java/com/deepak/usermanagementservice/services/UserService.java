package com.deepak.usermanagementservice.services;

import com.deepak.usermanagementservice.dto.request.RegisterRequest;
import com.deepak.usermanagementservice.exception.AppException;
import com.deepak.usermanagementservice.models.User;
import com.deepak.usermanagementservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository _userRepository;
    private final BCryptPasswordEncoder _bCryptPasswordEncoder;

    public void register(RegisterRequest req) {
        if(_userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new AppException("User already exists");
        }

        User user = req.convertToUser();
        //user.setProvider("LOCAL");
        user.setPassword(_bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setDeleted(false);
        var data = _userRepository.save(user);
    }
}
