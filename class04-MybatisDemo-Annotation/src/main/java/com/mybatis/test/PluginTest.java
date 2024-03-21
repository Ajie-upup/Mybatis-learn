package com.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

public class PluginTest {
    private IUserMapper userMapper;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("MyBatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Test
    public void testMyPlugin() {
        List<User> users = userMapper.findAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testPageHelper() {
        // 设置分⻚参数
        PageHelper.startPage(1, 2);
        // 分页查询会执行两次 sql
        // 1. 查询总记录条数
        // 2. 查询 limit 下的数据
        List<User> users = userMapper.findAllUser();
        for (User user : users) {
            System.out.println(user);
        }

        PageInfo<User> pageInfo = new PageInfo<>(users);
        System.out.println("总条数：" + pageInfo.getTotal());
        System.out.println("总⻚数：" + pageInfo.getPages());
        System.out.println("当前⻚：" + pageInfo.getPageNum());
        System.out.println("每⻚显万⻓度：" + pageInfo.getPageSize());
        System.out.println("是否第⼀⻚：" + pageInfo.isIsFirstPage());
        System.out.println("是否最后⼀⻚：" + pageInfo.isIsLastPage());
    }

}
