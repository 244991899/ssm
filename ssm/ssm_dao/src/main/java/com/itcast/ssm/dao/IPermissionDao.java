package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {
    @Select("select * from permission where id in(select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findById(String roleId);

    @Select("select * from permission")
    public abstract List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public abstract void save(Permission permission);
}
