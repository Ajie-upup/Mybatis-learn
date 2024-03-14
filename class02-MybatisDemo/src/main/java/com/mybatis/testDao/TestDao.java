package com.mybatis.testDao;

import com.mybatis.domain.User;
import com.mybatis.testDao.impl.UserDaoImpl;

import java.io.IOException;
import java.util.List;

public class TestDao {
    public static void main(String[] args) throws IOException {
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
