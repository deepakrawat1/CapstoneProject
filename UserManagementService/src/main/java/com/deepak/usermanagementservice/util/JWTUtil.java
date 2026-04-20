package com.deepak.usermanagementservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String _secret;

    public String generateToken(String email) {

        Map<String, Object> payload = new HashMap<>();
        Long nowInSecond = System.currentTimeMillis()/1000;
        payload.put("iat", nowInSecond);
        payload.put("nbf", nowInSecond);
        payload.put("exp", nowInSecond + (60*10));
        payload.put("sub" ,email);
        payload.put("iss", "scaler");

        return Jwts.builder()
                .claims(payload)
                .signWith(Keys.hmacShaKeyFor(_secret.getBytes()))
                .compact();
    }

    public Map<String,Object> validateAndExtractPayload(String token){
        Map<String, Object> payload = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(_secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return  payload;
    }
}
