package com.project.platform.module.auth.resolver;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.project.platform.exception.ErrorCode;
import com.project.platform.exception.InvalidTokenException;
import com.project.platform.module.auth.service.JwtProvider;
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
        return parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Long resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final String header = webRequest.getHeader(AUTHORIZATION);
        final String accessToken = extractAccessTokenFromHeader(header);
        jwtProvider.validateAccessToken(accessToken);
        return Long.valueOf(jwtProvider.getSubject(accessToken));
    }

    private String extractAccessTokenFromHeader(String header) {
        final String prefix = "Bearer ";
        if (header == null || !header.startsWith(prefix)) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }
        return header.substring(prefix.length());
    }

}
