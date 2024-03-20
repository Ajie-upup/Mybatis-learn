package com.mybatis.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 3310042801093863255L;

    private Integer id;
    private Date orderTime;
    private double mount;

    private User user;

}
