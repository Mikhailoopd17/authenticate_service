package com.example.authenticate_service.rest;

import com.example.authenticate_service.dao.UserProfileDAO;
import com.example.authenticate_service.pojo.Authenticate;
import com.example.authenticate_service.pojo.Credentials;
import com.example.authenticate_service.service.AuthtenticateService;
import com.example.authenticate_service.service.UserService;
import com.example.authenticate_service.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "${web.prefix}/auth", produces = "application/json; charset=UTF-8")
public class UserController {
    private static final String COOKIE_NAME = "token";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthtenticateService authtenticateService;

    @PostMapping("/login")
    public HttpServletResponse userAuthorize(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody Credentials credentials) throws Exception {
        Cookie cookie = userService.authorize(credentials);
        response.addCookie(cookie);
        return response;
    }

    @GetMapping("/current")
    public HttpServletResponse current(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = CookieUtil.extractCookie(COOKIE_NAME, request);
        if (token.isEmpty()) {
            response.sendError(403, "Access denied");
        }

        if (authtenticateService.checkToken(token)) {
            response.setStatus(200);

        } else {
            response.sendError(403, "Access denied");
        }
        return response;
    }
}
