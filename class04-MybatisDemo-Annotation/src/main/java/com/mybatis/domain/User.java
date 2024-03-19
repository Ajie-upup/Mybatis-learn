package com.mybatis.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 5954349334740133716L;
    /**
     * 实现二级缓存的实体类需要实现 serializable 接口
     */

    private Integer id;

    private String username;

    private String password;

    private List<Order> orderList;

    private List<Role> roleList;


}
