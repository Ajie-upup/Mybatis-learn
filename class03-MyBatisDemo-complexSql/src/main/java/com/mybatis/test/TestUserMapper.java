package com.mybatis.test;

import com.mybatis.domain.User;
import com.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestUserMapper {

    @Test
    public void testWhereIf() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("MyBatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setId(1);
        user.setUsername("tom");

        User targetUser = userMapper.findByCondition(user);

        System.out.println(targetUser);
        sqlSession.close();
    }


}
