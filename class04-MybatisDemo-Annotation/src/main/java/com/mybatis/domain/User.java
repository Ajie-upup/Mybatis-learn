package com.mybatis.domain;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private List<Order> orderList;

    private List<Role> roleList;
}
