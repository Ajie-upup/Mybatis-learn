package com.jdbc;

import com.jdbc.domain.User;

import java.sql.*;


public class JDBCDemo {
    /**
     * 运行 jdbc 代码首先需要先将 lib 文件夹标记为 Ad as library
     */
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 通过驱动管理类获取数据库链接
            connection = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/db-mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");
            // 定义sql语句  ？表示占位符
            String sql = "select * from user where username = ?";
            // 获取预处理 statement
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数，第⼀个参数为sql语句中参数的序号(从1开始)，第⼆个参数为设置的参数值
            preparedStatement.setString(1, "tom");
            // 向数据库发出sql执⾏查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            // 遍历查询结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                // 封装User
                user.setId(id);
                user.setUsername(username);
            }
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
