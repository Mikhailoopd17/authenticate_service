package com.example.authenticate_service.service;

import com.example.authenticate_service.dao.AuthenticateDAO;
import com.example.authenticate_service.pojo.Authenticate;
import com.example.authenticate_service.pojo.User;
import com.example.authenticate_service.dao.UserProfileDAO;
import commons.users.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthtenticateService {
    @Value("${password.salt}")
    private String salt;

    @Value("${cookie.ttl}")
    private Integer ttl;

    @Autowired
    private AuthenticateDAO authenticateDAO;

    @Autowired
    private UserProfileDAO userProfileDAO;

    public Cookie getAuthenticate(User user) {
        if (user.getId() == null) {
            throw new RuntimeException("error: userId - null");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("userId", user.getId());
        Authenticate authenticate = authenticateDAO.getOneByParams(params);
        if (authenticate == null) {
            authenticate = new Authenticate(user.getId());
        }
        authenticate.setToken(getToken());
        save(authenticate);

        Cookie cookie = new Cookie("token", authenticate.getToken());
        cookie.setMaxAge(ttl);

        return cookie;
    }

    private void save(Authenticate authenticate) {
        if (authenticate.getId() == null) {
            authenticateDAO.create(authenticate);
        } else {
            authenticate.setSingCount(authenticate.getSingCount() + 1);
            authenticateDAO.update(authenticate);
        }
    }

    public Authenticate getOneById (Long id) {
        return authenticateDAO.getOneByParams(Collections.singletonMap("id", id));
    }


    public String getToken(){
        String token = UUID.randomUUID().toString();
        if (authenticateDAO.isExistsToken(token)) {
            getToken();
        }
        return token;
    }

    public Boolean checkToken(String token) {
        return authenticateDAO.checkToken(token);
    }


}
