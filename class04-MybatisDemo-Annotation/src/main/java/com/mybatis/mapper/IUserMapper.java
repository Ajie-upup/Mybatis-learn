package com.mybatis.mapper;

import com.mybatis.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserMapper {

    @Select("select * from user")
    List<User> findAllUser();

    @Insert("insert into user (id , username , password) values (#{id} , #{username}, #{password})")
    int insertUser(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);

    @Update("update user set username = #{username} , password = #{password} where id = #{id}")
    int updateUser(User user);

}
