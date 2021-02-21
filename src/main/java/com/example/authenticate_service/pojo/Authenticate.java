package com.example.authenticate_service.pojo;

import com.example.authenticate_service.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Authenticate extends BaseEntity {
    private Integer userId;
    private LocalDateTime lastVisit;
    private String token;
    private Integer singCount;

    public Authenticate(Integer userId) {
        this.userId = userId;
    }

}
