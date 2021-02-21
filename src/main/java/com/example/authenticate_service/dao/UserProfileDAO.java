package com.example.authenticate_service.dao;

import commons.users.UserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserProfileDAO {
    UserProfile getUserProfileByParams(@Param("params") Map params);
}
