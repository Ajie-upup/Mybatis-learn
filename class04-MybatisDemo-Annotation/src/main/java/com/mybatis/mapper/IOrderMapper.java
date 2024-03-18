package com.mybatis.mapper;

import com.mybatis.domain.Order;
import com.mybatis.domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrderMapper {

    @Select("select * from `order`")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderTime", column = "ordertime"),
            @Result(property = "mount", column = "mount"),
            @Result(property = "user",column = "uid",javaType = User.class,
                    one=@One(select = "com.mybatis.mapper.IUserMapper.findUserById")
            )
    })
    List<Order> findAllOrder();

    @Select("select * from `order` where uid = #{uid}")
    List<Order> selectOrderByUid(int uid);


}
