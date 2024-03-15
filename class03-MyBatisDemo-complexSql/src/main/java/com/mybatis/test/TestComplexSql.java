package com.mybatis.test;

import com.mybatis.domain.Order;
import com.mybatis.domain.Role;
import com.mybatis.domain.User;
import com.mybatis.mapper.OrderMapper;
import com.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestComplexSql {

    /**
     * 获取时间
     */
    public static void main(String[] args) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(date);
    }

    @Test
    public void testOneToOne() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("MyBatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        // order 表在 mybatis 文件中引用需要使用 `` ，关键字冲突
        List<Order> orders = orderMapper.findAll();
        for (Order order : orders) {
            System.out.println(order);
        }

        sqlSession.close();
    }

    @Test
    public void testOneToMore() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("MyBatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = userMapper.selectUserWithOrder();
        for (User user : users) {
            System.out.println(user.getUsername());
            for (Order order : user.getOrderList()) {
                System.out.println(order);
            }
            System.out.println("---------------------------");
        }
        sqlSession.close();
    }

    @Test
    public void testMoreToMore() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("MyBatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = userMapper.findUserWithRole();
        for (User user : users) {
            System.out.println(user.getUsername());
            for (Role role : user.getRoleList()) {
                System.out.println(role);
            }
            System.out.println("---------------------------");
        }
        sqlSession.close();
    }


}
