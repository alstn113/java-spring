package com.project.platform.domain.auth.controller.resolver;

import com.project.platform.domain.auth.domain.Accessor;
import com.project.platform.exception.ErrorCode;
import jakarta.security.auth.message.AuthException;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MemberOnlyAspect {

    @Before("@annotation(com.project.platform.domain.auth.controller.resolver.MemberOnly)")
    public void check(final JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs())
                .filter(Accessor.class::isInstance)
                .map(Accessor.class::cast)
                .filter(Accessor::isMember)
                .findFirst()
                .orElseThrow(() -> new AuthException(ErrorCode.UNAUTHORIZED));
    }
}


