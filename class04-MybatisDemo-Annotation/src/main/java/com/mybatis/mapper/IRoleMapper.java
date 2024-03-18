package com.mybatis.mapper;

import com.mybatis.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleMapper {

    @Select("select * from `role` r ,user_role ur where r.id = ur.role_id and ur.user_id = #{uid}")
    List<Role> selectRoleById(int uid);
}
