package com.example.authenticate_service.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    @NonNull
    private String login;
    @NonNull
    private String password;
}
