package com.example.authenticate_service.pojo;

import com.example.authenticate_service.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseEntity {
    private String firstName;
    private String secondName;
    private String lastName;
    private String login;
    private String password;
    private String email;
}
