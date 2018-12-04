package com.itcast.ssm.service;

import com.itcast.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {
    public abstract List<Permission> findAll();
    public abstract void save(Permission permission);
}
