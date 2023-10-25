package com.project.platform.module.auth.resolver;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.project.platform.exception.BadRequestException;
import com.project.platform.exception.ErrorCode;
import com.project.platform.exception.InvalidTokenException;
import com.project.platform.module.auth.service.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthGuardAspect {
    private final JwtProvider jwtProvider;

    public AuthGuardAspect(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Before("@annotation(AuthGuard)")
    public void checkAuthorization(JoinPoint joinPoint, AuthGuard AuthGuard) throws InvalidTokenException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        try {
            String header = request.getHeader(AUTHORIZATION);
            String accessToken = extractAccessTokenFromHeader(header);
            jwtProvider.validateAccessToken(accessToken);
            Long memberId = Long.valueOf(jwtProvider.getSubject(accessToken));
            request.setAttribute("memberId", memberId);
        } catch (Exception e) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }
    }

    private String extractAccessTokenFromHeader(String header) {
        final String prefix = "Bearer ";
        if (header == null || !header.startsWith(prefix)) {
            throw new BadRequestException(ErrorCode.INVALID_TOKEN);
        }
        return header.substring(prefix.length());
    }
}


