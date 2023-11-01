package com.project.platform.config;

import com.project.platform.module.auth.service.BCryptPasswordEncoder;
import com.project.platform.module.auth.service.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
