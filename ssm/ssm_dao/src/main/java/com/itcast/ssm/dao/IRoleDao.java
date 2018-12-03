package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId = #{usersId})")
    public List<Role> findRoleById(String usersId);
}
