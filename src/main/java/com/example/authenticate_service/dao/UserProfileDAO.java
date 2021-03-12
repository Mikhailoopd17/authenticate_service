package com.example.authenticate_service.dao;

import com.example.authenticate_service.pojo.Credentials;
import com.example.authenticate_service.pojo.User;
import commons.users.UserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserProfileDAO {

    @Select("SELECT id FROM users " +
            "where login = #{login} " +
            "and password = #{password} " +
            "and not is_deleted")
    User getUserByCredentials(Credentials credentials);

    UserProfile getUserProfileByParams(@Param("params") Map params);
}
