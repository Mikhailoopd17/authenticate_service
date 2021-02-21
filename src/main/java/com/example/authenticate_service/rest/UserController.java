package com.example.authenticate_service.rest;

import com.example.authenticate_service.dao.UserProfileDAO;
import com.example.authenticate_service.pojo.Authenticate;
import com.example.authenticate_service.pojo.Credentials;
import com.example.authenticate_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.util.Collections;

@RestController
@RequestMapping(value = "${web.prefix}/auth", produces = "application/json; charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileDAO userProfileDAO;

    @PostMapping("/login")
    public HttpStatus userAuthorize(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody Credentials credentials) throws AccessDeniedException {
        Cookie cookie = userService.authorize(credentials);
        response.addCookie(cookie);
        response.addHeader(cookie.getName(), cookie.getValue());
        String profile = userProfileDAO.getUserProfileByParams(Collections.singletonMap("token", cookie.getValue())).getJson();
        response.addHeader("profile", profile);
        return HttpStatus.OK;
    }
}
