package com.itcast.ssm.service.impl;

import com.itcast.ssm.dao.IRoleDao;
import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IRoleDaoImpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;
    @Override
    public List<Role> findAll() {
        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) {
        iRoleDao.save(role);
    }

    @Override
    public Role findRoleByIdAndAllPermission(String id) {
        return iRoleDao.findRoleByIdAndAllPermission(id);
    }

    @Override
    public List<Permission> findAllPermissionById(String roleId) {
        return iRoleDao.findAllPermissionById(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionId) {
        for (String id : permissionId) {
            iRoleDao.addPermissionToRole(roleId,id);
        }
    }

}
