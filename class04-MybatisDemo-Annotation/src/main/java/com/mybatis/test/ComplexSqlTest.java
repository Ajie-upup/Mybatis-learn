package com.mybatis.test;

import com.mybatis.domain.Order;
import com.mybatis.domain.User;
import com.mybatis.mapper.IOrderMapper;
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

public class ComplexSqlTest {

    private IOrderMapper orderMapper;

    private IUserMapper userMapper;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("MyBatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        orderMapper = sqlSession.getMapper(IOrderMapper.class);
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }


    @Test
    public void testFindAllOrderWithUser() {
        List<Order> orders = orderMapper.findAllOrder();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void testFindUserWithOrder() {
        List<User> users = userMapper.findUserWithOrder();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindUserWithRole() {
        List<User> users = userMapper.findUserWithRole();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
