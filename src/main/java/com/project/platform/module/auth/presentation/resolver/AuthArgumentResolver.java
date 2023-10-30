package com.project.platform.module.auth.presentation.resolver;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.project.platform.exception.ErrorCode;
import com.project.platform.exception.InvalidTokenException;
import com.project.platform.module.auth.domain.Accessor;
import com.project.platform.module.auth.application.JwtProvider;
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
            jwtProvider.validateAccessToken(accessToken);
            final Long memberId = Long.valueOf(jwtProvider.getSubject(accessToken));
            return Accessor.member(memberId);
        } catch (final InvalidTokenException e) {
            return Accessor.guest();
        }
    }

    private String extractAccessTokenFromHeader(String header) {
        final String prefix = "Bearer ";
        if (header == null || !header.startsWith(prefix)) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }
        return header.substring(prefix.length());
    }

}
