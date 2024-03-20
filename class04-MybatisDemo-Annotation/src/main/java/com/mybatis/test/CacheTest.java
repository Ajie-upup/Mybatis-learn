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

public class CacheTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("MyBatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void testCache1TwiceSelect() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);

        //第⼀次查询，发出sql语句，并将查询出来的结果放进缓存中
        User u1 = userMapper.findUserById(1);
        System.out.println(u1);

        // 第⼆次查询，由于是同⼀个sqlSession,会在缓存中查询结果
        // 如果有，则直接从缓存中取出来，不和数据库进⾏交互
        User u2 = userMapper.findUserById(1);
        System.out.println(u2);
        sqlSession.close();
    }

    @Test
    public void testCache1SelectAndUpdate() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);
        // 第⼀次查询，发出sql语句，并将查询的结果放⼊缓存中
        User u1 = userMapper.findUserById(3);
        System.out.println(u1);

        // 在第二次查询之前进⾏了⼀次更新操作
        u1.setUsername("user3");
        userMapper.updateUser(u1);

        // 执行 sqlSession.commit(),会清空缓存信息
        sqlSession.commit();

        // 则第二次查询也会发出 sql 语句
        User u2 = userMapper.findUserById(3);
        System.out.println(u2);

        sqlSession.close();
    }

    @Test
    public void testCache2SqlSession() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        IUserMapper userMapper1 = sqlSession1.getMapper(IUserMapper.class);
        IUserMapper userMapper2 = sqlSession2.getMapper(IUserMapper.class);

        // 第⼀次查询，发出sql语句，并将查询的结果放⼊缓存中
        User u1 = userMapper1.findUserById(1);
        System.out.println(u1);

        // 第⼀次查询完后关闭 sqlSession
        sqlSession1.close();

        // 第⼆次查询，即使sqlSession1已经关闭了，这次查询依然不发出sql语句
        User u2 = userMapper2.findUserById(1);
        System.out.println(u2);
        sqlSession2.close();
        System.out.println(u1.equals(u2));
    }

    @Test
    public void testCache2WithCommit() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        IUserMapper userMapper1 = sqlSession1.getMapper(IUserMapper.class);
        IUserMapper userMapper2 = sqlSession2.getMapper(IUserMapper.class);
        IUserMapper userMapper3 = sqlSession2.getMapper(IUserMapper.class);

        // 第⼀次查询，发出sql语句，并将查询的结果放⼊缓存中
        User u1 = userMapper1.findUserById(3);
        System.out.println(u1);
        // 第⼀次查询完后关闭sqlSession
        sqlSession1.close();

        // 执⾏更新操作，commit()
        u1.setUsername("cacheUserCommit");
        userMapper3.updateUser(u1);
        sqlSession3.commit();

        // 第⼆次查询，由于上次更新操作，缓存数据已经清空(防⽌数据脏读)，这⾥必须再次发出sql查询
        User u2 = userMapper2.findUserById(3);
        System.out.println(u2);
        sqlSession2.close();
    }


    @Test
    public void testRedisCache() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        IOrderMapper mapper1 = sqlSession1.getMapper(IOrderMapper.class);
        IOrderMapper mapper2 = sqlSession2.getMapper(IOrderMapper.class);
        IOrderMapper mapper3 = sqlSession3.getMapper(IOrderMapper.class);

        Order order1 = mapper1.selectOrderById(1);
        // 清空⼀级缓存
        sqlSession1.close();

        Order order = new Order();
        order.setId(3);
        order.setMount(1111.111);
        mapper3.updateOrderById(order);

        sqlSession3.commit();
        Order order2 = mapper2.selectOrderById(1);
        System.out.println(order2 == order2);
    }

}
