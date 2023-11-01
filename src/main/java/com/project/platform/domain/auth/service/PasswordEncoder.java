package com.project.platform.domain.auth.service;

public interface PasswordEncoder {
    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
