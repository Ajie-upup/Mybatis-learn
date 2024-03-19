package com.mybatis.mapper;

import com.mybatis.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@CacheNamespace(blocking = true) // 开启二级缓存
public interface IUserMapper {

    @Select("select * from user")
    List<User> findAllUser();

    @Insert("insert into user (id , username , password) values (#{id} , #{username}, #{password})")
    int insertUser(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);

    @Update("update user set username = #{username} , password = #{password} where id = #{id}")
    int updateUser(User user);

    @Select("select * from User where id = #{id}")
    User findUserById(int id);

    @Select("select * from user")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "orderList", column = "id", javaType = List.class,
                    many = @Many(select = "com.mybatis.mapper.IOrderMapper.selectOrderByUid")
            )
    })
    List<User> findUserWithOrder();


    @Select("select * from user")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "roleList", column = "id", javaType = List.class,
                    many = @Many(select = "com.mybatis.mapper.IRoleMapper.selectRoleById")
            )
    })
    List<User> findUserWithRole();

}
