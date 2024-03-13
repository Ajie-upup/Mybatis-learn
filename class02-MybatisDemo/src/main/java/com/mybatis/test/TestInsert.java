package com.mybatis.test;

import com.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestInsert {
    public static void main(String[] args) throws IOException {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");

        //获取sqlSession 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取 sqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(12);
        user.setUsername("mybatis");
        user.setPassword("mybatis");

        //执行 sql 语句
        int res = sqlSession.insert("userMapper.insert", user);

        // 同一个 sqlSession 会话对象的作用域 -- 能查询到没有提交事务的数据
       /* List<User> users = sqlSession.selectList("userMapper.findAll");

        for (User user2 : users) {
            System.out.println(user2);
        }*/

        System.out.println(res);

        // 涉及数据库的变化，都需要提交事务
        sqlSession.commit();

        sqlSession.close();
    }
}
