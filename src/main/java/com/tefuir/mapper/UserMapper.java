package com.tefuir.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tefuir.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select user.password from user where account = #{account}")
    String selectPasswordByAccount(@Param("account") String account);
}