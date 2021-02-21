package com.example.authenticate_service.base;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity {
    private Integer id;
    private LocalDateTime createdAt;
    private Boolean isDeleted;
}
