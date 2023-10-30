package com.project.platform.module.auth.presentation.resolver;


import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import io.swagger.v3.oas.annotations.Hidden;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Hidden
@Target(METHOD)
@Retention(RUNTIME)
public @interface MemberOnly {
}
