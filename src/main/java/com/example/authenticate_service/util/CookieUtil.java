package com.example.authenticate_service.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
    public static String extractCookie(String cookieName, HttpServletRequest request) {
        if (request == null || request.getCookies() == null) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals(cookieName)) {
                return c.getValue();
            }
        }
        return null;
    }
}
