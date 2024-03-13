package com.mybatis.test;

import com.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestSelect {
    public static void main(String[] args) throws IOException {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");

        //获取sqlSession 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取 sqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行 sql 语句
        List<User> users = sqlSession.selectList("userMapper.findAll");

        for (User user : users) {
            System.out.println(user);
        }

        //关闭资源
        sqlSession.close();
    }
}
