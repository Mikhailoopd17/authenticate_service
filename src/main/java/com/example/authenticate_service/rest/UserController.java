package com.example.authenticate_service.rest;

import com.example.authenticate_service.dao.UserProfileDAO;
import com.example.authenticate_service.pojo.Authenticate;
import com.example.authenticate_service.pojo.Credentials;
import com.example.authenticate_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.util.Collections;

@RestController
@RequestMapping(value = "${web.prefix}/auth",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserProfileDAO userProfileDAO;

    @PostMapping("/login")
    public String userAuthorize(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody MultiValueMap<String, String> map) throws Exception {
        assert map != null;
        if (map.isEmpty()) {
            throw new Exception("null");
        }
        Credentials credentials = new Credentials(map.get("login").get(0), map.get("password").get(0));
        Cookie cookie = userService.authorize(credentials);
        response.addCookie(cookie);
//        response.addHeader(cookie.getName(), cookie.getValue());
//        String profile = userProfileDAO.getUserProfileByParams(Collections.singletonMap("token", cookie.getValue())).getJson();
//        response.addHeader("profile", profile);
        return "redirect:/home";
    }
}
