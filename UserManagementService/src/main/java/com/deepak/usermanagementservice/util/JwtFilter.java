package com.deepak.usermanagementservice.util;

import com.deepak.usermanagementservice.dto.ApiErrorResponse;
import com.deepak.usermanagementservice.exception.TokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JWTUtil _jwtUtil;
    private final UserContext _userContext;
    public JwtFilter(JWTUtil jwtUtil, UserContext userContext){
        _jwtUtil = jwtUtil;
        _userContext = userContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.equals("/api/user/login") || path.equals("/api/user/register") || path.equals("/api/user/forgotpassword") || path.equals("/api/user/resetpassword")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {

            ApiErrorResponse res = new ApiErrorResponse();
            res.setSuccess(false);
            res.setErrors(List.of("Missing or invalid token"));

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), res);
            return;
        }

        String token = header.substring(7);

        try {
            Map<String, Object> payload = _jwtUtil.validateAndExtractPayload(token);
            _userContext.setEmail(payload.get("sub").toString());
            filterChain.doFilter(request, response);
        }
        catch (ExpiredJwtException ex){
            ApiErrorResponse res = new ApiErrorResponse();
            res.setSuccess(false);
            res.setErrors(List.of("Token expired"));

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), res);
            return;
        }
        catch(JwtException ex){
            ApiErrorResponse res = new ApiErrorResponse();
            res.setSuccess(false);
            res.setErrors(List.of("Invalid token"));

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), res);
            return;
        }
        catch (Exception e) {
            ApiErrorResponse res = new ApiErrorResponse();
            res.setSuccess(false);
            res.setErrors(List.of(e.getMessage()));

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), res);
            return;
        }
    }
}
