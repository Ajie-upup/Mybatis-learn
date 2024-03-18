package com.mybatis.test;

import com.mybatis.domain.User;
import com.mybatis.mapper.IUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserMapperTest {

    private IUserMapper userMapper;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("MyBatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        userMapper = sqlSession.getMapper(IUserMapper.class);
    }


    @Test
    public void testFindAllUser() {
        List<User> users = userMapper.findAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setId(4);
        user.setUsername("testInsert");
        user.setPassword("testInsert");
        int res = userMapper.insertUser(user);

        List<User> users = userMapper.findAllUser();
        for (User dbUser : users) {
            System.out.println(dbUser);
        }
    }

    @Test
    public void testDeleteUser() {

        int res = userMapper.deleteUser(4);

        List<User> users = userMapper.findAllUser();
        for (User dbUser : users) {
            System.out.println(dbUser);
        }
    }


    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(4);
        user.setUsername("testUpdate");
        user.setPassword("testUpdate");

        int res = userMapper.updateUser(user);

        List<User> users = userMapper.findAllUser();
        for (User dbUser : users) {
            System.out.println(dbUser);
        }
    }
}
