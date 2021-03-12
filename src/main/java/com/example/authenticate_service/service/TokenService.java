package com.example.authenticate_service.service;

import com.example.authenticate_service.dao.AuthenticateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private AuthenticateDAO authenticateDAO;

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
