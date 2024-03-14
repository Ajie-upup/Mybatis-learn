package com.mybatis.testMapper;

import com.mybatis.domain.User;

public interface UserMapper {

    User findById(Integer id);
}
