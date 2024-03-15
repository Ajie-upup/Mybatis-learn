package com.mybatis.mapper;

import com.mybatis.domain.Order;

import java.util.List;

public interface OrderMapper {
    List<Order> findAll();

}
