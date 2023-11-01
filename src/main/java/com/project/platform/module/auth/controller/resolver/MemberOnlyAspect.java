package com.project.platform.module.auth.controller.resolver;

import com.project.platform.exception.AuthException;
import com.project.platform.exception.ErrorCode;
import com.project.platform.module.auth.domain.Accessor;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MemberOnlyAspect {

    @Before("@annotation(com.project.platform.module.auth.controller.resolver.MemberOnly)")
    public void check(final JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs())
                .filter(Accessor.class::isInstance)
                .map(Accessor.class::cast)
                .filter(Accessor::isMember)
                .findFirst()
                .orElseThrow(() -> new AuthException(ErrorCode.UNAUTHORIZED));
    }
}


