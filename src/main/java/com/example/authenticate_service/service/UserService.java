package com.example.authenticate_service.service;

import com.example.authenticate_service.dao.UserDAO;
import com.example.authenticate_service.pojo.Authenticate;
import com.example.authenticate_service.pojo.Credentials;
import com.example.authenticate_service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

@Service
public class UserService {
    @Value("${password.salt}")
    private String salt;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    public AuthtenticateService authtenticateService;

    public Cookie authorize(Credentials credentials) throws AccessDeniedException {
        String md5WithSalt = DigestUtils.md5DigestAsHex((credentials.getPassword() + salt).toUpperCase().getBytes());
        credentials.setPassword(md5WithSalt);
        User user = userDAO.getUserByCredentials(credentials);
        if (user == null) {
            return null;
        }
        return authtenticateService.getAuthenticate(user);
    }
}
