package com.mybatis.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer id;
    private Date orderTime;
    private double mount;

    private User user;

}
