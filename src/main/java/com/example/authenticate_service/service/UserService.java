package com.example.authenticate_service.service;

import com.example.authenticate_service.dao.UserProfileDAO;
import com.example.authenticate_service.pojo.Credentials;
import com.example.authenticate_service.pojo.User;
import commons.users.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.Map;

@Service
public class UserService {
    @Value("${password.salt}")
    private String salt;

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    public AuthtenticateService authtenticateService;

    public Cookie authorize(Credentials credentials) throws AccessDeniedException {
        String md5WithSalt = DigestUtils.md5DigestAsHex((credentials.getPassword() + salt).toUpperCase().getBytes());
        credentials.setPassword(md5WithSalt);
        User user = userProfileDAO.getUserByCredentials(credentials);
        if (user == null) {
            return null;
        }
        return authtenticateService.getAuthenticate(user);
    }

    public UserProfile getUserProfile(String token) {
        if (token != null && !token.isBlank()) {
            Map params = Collections.singletonMap("token", token);
            return userProfileDAO.getUserProfileByParams(params);
        }
        return null;
    }
}
