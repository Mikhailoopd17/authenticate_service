package com.example.authenticate_service.rest;


import com.example.authenticate_service.service.TokenService;
import com.example.authenticate_service.service.UserService;
import com.example.authenticate_service.util.CookieUtil;
import commons.users.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.authenticate_service.AuthenticateServiceApplication.COOKIE_NAME;

@RestController
@RequestMapping(value = "${web.prefix}/auth/emul")
public class EmulApi {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping
    public void emulate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = CookieUtil.extractCookie(COOKIE_NAME, request);
        if (token != null && tokenService.checkToken(token)) {
            UserProfile user = userService.getUserProfile(token);
            response.setHeader("profile", user.getJson());
        }
    }
}
