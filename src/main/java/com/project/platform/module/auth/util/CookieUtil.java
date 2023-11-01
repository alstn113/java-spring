package com.project.platform.module.auth.util;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;

public class CookieUtil {
    private CookieUtil() {
    }

    private static final String REFRESH_TOKEN = "refresh-token";
    private static final int COOKIE_AGE_SECONDS = 60 * 60 * 24 * 7; // 7 days

    public static void setRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        final ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, refreshToken)
                .maxAge(COOKIE_AGE_SECONDS)
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .path("/")
                .build();
        response.setHeader("Set-Cookie", cookie.toString());
    }

    public static void clearRefreshTokenCookie(HttpServletResponse response) {
        final ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, "")
                .maxAge(0)
                .path("/")
                .build();
        response.setHeader("Set-Cookie", cookie.toString());
    }
}
