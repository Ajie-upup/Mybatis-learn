package com.mybatis.mapper;

import com.mybatis.domain.Order;
import com.mybatis.domain.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;

@CacheNamespace(implementation = RedisCache.class)
public interface IOrderMapper {

    @Select("select * from `order` where id = #{id}")
    @Options(useCache = true)
    Order selectOrderById(int id);

    @Update("update `order` set ordertime = #{orderTime} , mount = #{mount} where id = #{id}")
    int updateOrderById(Order order);

    @Select("select * from `order`")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderTime", column = "ordertime"),
            @Result(property = "mount", column = "mount"),
            @Result(property = "user", column = "uid", javaType = User.class,
                    one = @One(select = "com.mybatis.mapper.IUserMapper.findUserById")
            )
    })
    List<Order> findAllOrder();

    @Select("select * from `order` where uid = #{uid}")
    List<Order> selectOrderByUid(int uid);


}
