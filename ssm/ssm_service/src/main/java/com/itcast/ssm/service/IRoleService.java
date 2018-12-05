package com.itcast.ssm.service;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;
public interface IRoleService {
    public List<Role> findAll();
    public void save(Role role);
    public abstract Role findRoleByIdAndAllPermission(String id);
    public abstract List<Permission> findAllPermissionById(String roleId);
    public abstract void addPermissionToRole(String roleId, String[] permissionId);
}
