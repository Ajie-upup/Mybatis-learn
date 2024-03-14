package com.mybatis.testDao;

import com.mybatis.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserDao {

    List<User> findAll() throws IOException;
}
