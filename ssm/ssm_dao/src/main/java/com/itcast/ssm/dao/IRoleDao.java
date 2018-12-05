package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId = #{usersId})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",many = @Many(select = "com.itcast.ssm.dao.IPermissionDao.findById"))
    })
    public List<Role> findRoleById(String usersId);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from role")
    public List<Role> findAll();
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role);

    /**
     * 根据id查询角色不关联的的所有权限
     * @param id
     * @return
     */
    @Select("select * from role where id = #{id}")
    public abstract Role findRoleByIdAndAllPermission(String id);

    /**
     * 查询所有roleId
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
    public abstract List<Permission> findAllPermissionById(String roleId);
    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    public abstract void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId")String permissionId);
}
