package com.project.platform.auth.controller.resolver;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.project.platform.auth.domain.Accessor;
import com.project.platform.auth.service.JwtProvider;
import com.project.platform.global.exception.ErrorCode;
import com.project.platform.global.exception.UnauthorizedException;
import io.jsonwebtoken.JwtException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;

    public AuthArgumentResolver(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.withContainingClass(Long.class).hasParameterAnnotation(Auth.class);
    }

    @Override
    public Accessor resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                    NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final String header = webRequest.getHeader(AUTHORIZATION);

        try {
            final String accessToken = extractAccessTokenFromHeader(header);
            Long memberId = jwtProvider.getMemberId(accessToken);
            return Accessor.member(memberId);
        } catch (JwtException e) {
            return Accessor.guest();
        }
    }

    private String extractAccessTokenFromHeader(String header) {
        final String prefix = "Bearer ";
        if (header == null || !header.startsWith(prefix)) {
            throw new UnauthorizedException(ErrorCode.TOKEN_NOT_EXIST);
        }
        return header.substring(prefix.length());
    }

}
