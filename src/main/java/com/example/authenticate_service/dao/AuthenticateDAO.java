package com.example.authenticate_service.dao;

import com.example.authenticate_service.pojo.Authenticate;
import org.apache.ibatis.annotations.*;

import java.util.Map;


@Mapper
public interface AuthenticateDAO {

    Authenticate getOneByParams(@Param("params") Map params);

    void create(Authenticate authenticate);

    void update(Authenticate authenticate);

    @Select("SELECT EXISTS (" +
            "SELECT 1 FROM authentications WHERE token = #{token})")
    boolean isExistsToken(@Param("token") String token);


}

