package com.mybatis.test;


import com.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestUpdate {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("Mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(11);
        user.setUsername("mybatis-update");
        user.setPassword("mybatis-update");

        int res = sqlSession.update("userMapper.update", user);

        System.out.println(res);

        sqlSession.commit();

        sqlSession.close();
    }
}
