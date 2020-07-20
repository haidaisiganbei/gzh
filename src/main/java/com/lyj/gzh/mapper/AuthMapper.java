package com.lyj.gzh.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


public interface AuthMapper {

    @Insert("insert into wx values(#{token})")
    int setAccessToken(String token);
}
