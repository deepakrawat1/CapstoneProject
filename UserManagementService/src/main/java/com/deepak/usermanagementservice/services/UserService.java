package com.deepak.usermanagementservice.services;

import com.deepak.usermanagementservice.dto.request.*;
import com.deepak.usermanagementservice.dto.response.GetProfileResponse;
import com.deepak.usermanagementservice.enums.AuthProvider;
import com.deepak.usermanagementservice.enums.Gender;
import com.deepak.usermanagementservice.exception.AppException;
import com.deepak.usermanagementservice.models.ResetPasswordToken;
import com.deepak.usermanagementservice.models.User;
import com.deepak.usermanagementservice.repositories.ResetPasswordTokenRepository;
import com.deepak.usermanagementservice.repositories.UserRepository;
import com.deepak.usermanagementservice.util.EmailSender;
import com.deepak.usermanagementservice.util.JWTUtil;
import com.deepak.usermanagementservice.util.UserContext;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${google.client.id}")
    private String _googleClientId;
    @Value("${facebook.oauth.url}")
    private  String _facebookOauthUrl;
    @Value("${base.url}")
    private String _baseUrl;

    private final UserRepository _userRepository;
    private final ResetPasswordTokenRepository _resetPasswordTokenRepository;
    private final PasswordEncoder _passwordEncoder;
    private final UserContext _userContext;
    private final JWTUtil _jwtUtil;
    private final EmailSender _emailSender;

    public void register(RegisterRequest req) {
        if(_userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new AppException("User already exists");
        }

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail().toLowerCase());
        user.setGender(Gender.valueOf(req.getGender().toUpperCase()));
        user.setProvider(AuthProvider.LOCAL);
        user.setPassword(_passwordEncoder.encode(req.getPassword()));
        user.setActive(true);
        user.setDeleted(false);

        User data = _userRepository.save(user);
    }

    public String login(LoginRequest req){

        AuthProvider provider = AuthProvider.valueOf(req.getProvider().toUpperCase());

        if(provider == AuthProvider.LOCAL){
            String errorMsg = "Invalid credentials";
            User user = _userRepository.findByEmail(req.getEmail()).orElseThrow(() -> new AppException(errorMsg));

            if(!_passwordEncoder.matches(req.getPassword(), user.getPassword())){
                throw new AppException(errorMsg);
            }

            return _jwtUtil.generateToken(user.getEmail());
        }

        if(provider == AuthProvider.GOOGLE){
            if(req.getProviderToken() == null) throw new AppException("Invalid provider token");

            GoogleIdToken.Payload payload = verifyGoogle(req.getProviderToken());

            Optional<User> existingUser = _userRepository.findByEmail(payload.getEmail());

            User user;

            if(existingUser.isPresent()) user = existingUser.get();
            else{
                user = new User();
                user.setName((String)payload.get("name"));
                user.setEmail(payload.getEmail());
                user.setProvider(AuthProvider.GOOGLE);
                user.setProviderId(payload.getSubject());
                user.setActive(true);
                user.setDeleted(false);

                User res = _userRepository.save(user);
            }

            return _jwtUtil.generateToken(user.getEmail());
        }

        if(provider == AuthProvider.FACEBOOK){
            if(req.getProviderToken() == null) throw new AppException("Invalid provider token");

            Map<String, Object> userData = verifyFacebook(req.getProviderToken());

            Optional<User> existingUser = _userRepository.findByEmail((String)userData.get("email"));

            User user;

            if(existingUser.isPresent()) user = existingUser.get();
            else{
                user = new User();
                user.setName((String)userData.get("name"));
                user.setEmail((String)userData.get("email"));
                user.setProvider(AuthProvider.FACEBOOK);
                user.setProviderId((String)userData.get("id"));
                user.setActive(true);
                user.setDeleted(false);

                User res = _userRepository.save(user);
            }

            return _jwtUtil.generateToken(user.getEmail());

        }

        throw new AppException("Invalid provider");
    }

    private GoogleIdToken.Payload verifyGoogle(String token) {

        try {
            GoogleIdTokenVerifier tokenVerifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance()
            )
                    .setAudience(Collections.singletonList(_googleClientId))
                    .build();

            GoogleIdToken idToken = tokenVerifier.verify(token);

            if (idToken != null) {
                return idToken.getPayload();
            }

        } catch (Exception e) {
            throw new AppException("Invalid provider token");
        }

        throw new AppException("Invalid provider token");
    }

    private Map<String, Object> verifyFacebook(String token) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = _facebookOauthUrl + token;

            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            return response.getBody();

        } catch (Exception e) {
            throw new AppException("Invalid provider token");
        }
    }

    public GetProfileResponse getProfile(){
        GetProfileResponse res = new GetProfileResponse();
        User user = _userRepository.findByEmail(_userContext.getEmail()).orElseThrow(() -> new AppException("User not found"));
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setGender(user.getGender().name());
        res.setProvider(user.getProvider().name());
        return res;
    }

    public void updateProfile(UpdateProfileRequest req){
        User user = _userRepository.findByEmail(_userContext.getEmail()).orElseThrow(() -> new AppException("User not found"));
        user.setName(req.getName());
        user.setGender(Gender.valueOf(req.getGender().toUpperCase()));
        _userRepository.save(user);
    }

    public void forgotPassword(ForgotPasswordRequest req){
        Optional<User> userOptional = _userRepository.findByEmail(req.getEmail());

        if(userOptional.isEmpty()) return;

        User user = userOptional.get();

        String token = UUID.randomUUID().toString();
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        resetPasswordToken.setUser(user);
        resetPasswordToken.setToken(token);
        resetPasswordToken.setUsed(false);
        resetPasswordToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        _resetPasswordTokenRepository.save(resetPasswordToken);

        String resetLink = _baseUrl+"/api/user/resetpassword?token=" + token;

        String emailBody = """
                Hello,
                
                                You requested to reset your password.
                
                                Click the link below to reset your password:
                                %s
                
                                This link will expire in 15 minutes.
                
                                If you did not request this, please ignore this email.
                """.formatted(resetLink);

        _emailSender.send(user.getEmail(), "Password Reset", emailBody);
    }

    public void resetPassword(ResetPasswordRequest req, String token){

        if(token == null || token.isBlank()) throw new AppException("Invalid link");

        ResetPasswordToken resetPasswordToken = _resetPasswordTokenRepository.findByToken(token).orElseThrow(() -> new AppException("Link expired"));

        if(resetPasswordToken.isUsed()) throw new AppException("Link already used");

        if(resetPasswordToken.getExpiryDate().isBefore(LocalDateTime.now())) throw new AppException("Link expired");

        User user = resetPasswordToken.getUser();

        user.setPassword(_passwordEncoder.encode(req.getPassword()));
        user.setLastPasswordReset(LocalDateTime.now());
        _userRepository.save(user);

        resetPasswordToken.setUsed(true);

        _resetPasswordTokenRepository.save(resetPasswordToken);
    }
}
