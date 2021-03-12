package com.example.authenticate_service.rest;

import com.example.authenticate_service.pojo.Credentials;
import com.example.authenticate_service.service.AuthtenticateService;
import com.example.authenticate_service.service.TokenService;
import com.example.authenticate_service.service.UserService;
import com.example.authenticate_service.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.authenticate_service.AuthenticateServiceApplication.COOKIE_NAME;

@RestController
@RequestMapping(value = "${web.prefix}/auth", produces = "application/json; charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthtenticateService authtenticateService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public void userAuthorize(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody Credentials credentials) throws Exception {
        Cookie cookie = userService.authorize(credentials);
        if (cookie == null) {
            response.sendError(403, "Wrong login or password");
            return;
        }
        response.addCookie(cookie);
    }

    @GetMapping("/current")
    public void current(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = CookieUtil.extractCookie(COOKIE_NAME, request);
        if (token == null) {
            response.sendError(401, "Access denied");
            return;
        }
        if (tokenService.checkToken(token)) {
            authtenticateService.updateAuthenticate(token);
            response.setStatus(200);
            return;
        }
        response.sendError(403, "Access denied");
    }
}
