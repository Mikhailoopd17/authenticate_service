package com.example.authenticate_service.dao;

import com.example.authenticate_service.pojo.Credentials;
import com.example.authenticate_service.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDAO {
    @Select("SELECT id FROM users " +
            "where login = #{login} " +
            "and password = #{password} " +
            "and not is_deleted")
    User getUserByCredentials(Credentials credentials);
}
