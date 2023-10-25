package com.project.platform.module.auth.resolver;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import io.swagger.v3.oas.annotations.Hidden;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Hidden
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface Auth {
}
