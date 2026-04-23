package com.deepak.productcatalogservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String _secret;

    public Map<String,Object> validateAndExtractPayload(String token){
        Map<String, Object> payload = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(_secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return  payload;
    }
}
