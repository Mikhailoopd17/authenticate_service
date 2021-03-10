package com.example.authenticate_service.rest;

import com.example.authenticate_service.dao.UserProfileDAO;
import com.example.authenticate_service.pojo.Authenticate;
import com.example.authenticate_service.pojo.Credentials;
import com.example.authenticate_service.service.AuthtenticateService;
import com.example.authenticate_service.service.UserService;
import commons.users.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "${web.prefix}/auth", produces = "application/json; charset=UTF-8")
public class UserController {
    private final String COOKIE_NAME = "token";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthtenticateService authtenticateService;

//    @Autowired
//    private UserProfileDAO userProfileDAO;

    @PostMapping("/login")
    public HttpStatus userAuthorize(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody Credentials credentials) throws Exception {
        Cookie cookie = userService.authorize(credentials);
        response.addCookie(cookie);
//        response.addHeader(cookie.getName(), cookie.getValue());
//        String profile = userProfileDAO.getUserProfileByParams(Collections.singletonMap("token", cookie.getValue())).getJson();
//        response.addHeader("profile", profile);
        return HttpStatus.OK;
    }

    @GetMapping("/current")
    public HttpStatus current(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request == null) {
            return HttpStatus.FORBIDDEN;
        }
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }
        String token = "";
        for (Cookie c : cookies) {
            if (c.getName().equals(COOKIE_NAME)) {
                token = c.getValue();
            }
        }

        if (token.isEmpty()) {
            return HttpStatus.FORBIDDEN;
        }

        UserProfile userProfile = authtenticateService.checkToken(token);
        if (userProfile != null) {
            return HttpStatus.OK;
        } else {
            throw new Exception("Error! user - null");
        }


    }
}
