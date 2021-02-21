package com.example.authenticate_service;

import com.example.authenticate_service.pojo.Credentials;
import com.example.authenticate_service.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import java.nio.file.AccessDeniedException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthenticateServiceApplicationTests {

    @Value("${cookie.ttl}")
    private Integer ttl;

    @Autowired
    private UserService userService;


    @Test
    void authUserOk() {
        System.out.println("test ok auth");
        Credentials credentials = new Credentials("admin", "admin");
        try {
            Cookie cookie = userService.authorize(credentials);
            Assert.isTrue("token".equals(cookie.getName()), "error name!");
            Assert.isTrue(cookie.getValue() != null, "error value!");
            Assert.isTrue(cookie.getMaxAge() == ttl, "error max age!");
        } catch (AccessDeniedException e) {
            Assert.isTrue(false, "error auth!");
        }

    }

    @Test
    void authUserFail() {
        System.out.println("test fail auth");
        Credentials credentials = new Credentials("admin", "admin12");
        try {
            userService.authorize(credentials);
            Assert.isTrue(false, "OK auth!");
        } catch (AccessDeniedException e) {
            Assert.isTrue(true);
        }
    }


}
