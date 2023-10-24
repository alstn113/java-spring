package com.project.platform.module.auth.resolver;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginResolverConfig implements WebMvcConfigurer {

    private final LoginArgumentResolver loginArgumentResolver;

    public LoginResolverConfig(LoginArgumentResolver loginArgumentResolver) {
        this.loginArgumentResolver = loginArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginArgumentResolver);
    }
}
