package com.mybatis.mapper;

import com.mybatis.domain.User;

import java.util.List;

public interface UserMapper {

    User findByCondition(User user);

}
