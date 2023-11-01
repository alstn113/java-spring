package com.project.platform.domain.auth.service;

import com.project.platform.domain.auth.domain.JwtTokens;
import com.project.platform.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private static final String EMPTY_SUBJECT = "";
    private final SecretKey secretKey;
    private final long accessTokenExpired;
    private final long refreshTokenExpired;

    public JwtProvider(
            @Value("${spring.auth.jwt.secretKey}") final String key,
            @Value("${spring.auth.jwt.accessTokenExpired}") final long accessTokenExpired,
            @Value("${spring.auth.jwt.refreshTokenExpired}") final long refreshTokenExpired) {
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpired = accessTokenExpired;
        this.refreshTokenExpired = refreshTokenExpired;
    }

    public JwtTokens generateTokens(final String subject) {
        final String refreshToken = createToken(EMPTY_SUBJECT, refreshTokenExpired);
        final String accessToken = createToken(subject, accessTokenExpired);
        return new JwtTokens(refreshToken, accessToken);
    }

    private String createToken(final String subject, final long validityInMilliseconds) {
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public void validateAccessToken(final String accessToken) {
        validateToken(accessToken);
    }

    private void validateToken(final String token) {
        try {
            parseToken(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredPeriodJwtException(ErrorCode.EXPIRED_PERIOD_TOKEN);
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtException(ErrorCode.INVALID_TOKEN);
        }
    }

    // getSubject는 token을 파싱하여 subject를 반환한다.
    public String getSubject(final String token) {
        return parseToken(token).getBody().getSubject();
    }

    // parseToken은 token을 파싱하여 Jws<Claims>를 반환한다.
    private Jws<Claims> parseToken(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}
