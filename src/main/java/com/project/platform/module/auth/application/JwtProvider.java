package com.project.platform.module.auth.application;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private final long accessTokenExpired;
    private final long refreshTokenExpired;

    public JwtProvider(
            @Value("${spring.auth.jwt.secretKey}") final String key,
            @Value("${spring.auth.jwt.accessTokenExpired}") final long accessTokenExpired,
            @Value("${spring.auth.jwt.refreshTokenExpired}") final long refreshTokenExpired
    ) {
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpired = accessTokenExpired;
        this.refreshTokenExpired = refreshTokenExpired;
    }
}
