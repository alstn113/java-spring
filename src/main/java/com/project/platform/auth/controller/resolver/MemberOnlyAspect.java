package com.project.platform.auth.controller.resolver;

import com.project.platform.auth.domain.Accessor;
import com.project.platform.global.exception.ErrorCode;
import com.project.platform.global.exception.ForbiddenException;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MemberOnlyAspect {

    @Before("@annotation(com.project.platform.auth.controller.resolver.MemberOnly)")
    public void check(final JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs())
                .filter(Accessor.class::isInstance)
                .map(Accessor.class::cast)
                .filter(Accessor::isMember)
                .findFirst()
                .orElseThrow(() -> new ForbiddenException(ErrorCode.ACCESS_DENIED));
    }
}


