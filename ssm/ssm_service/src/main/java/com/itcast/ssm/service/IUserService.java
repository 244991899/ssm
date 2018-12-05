package com.itcast.ssm.service;

import com.itcast.ssm.domain.Role;
import com.itcast.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public abstract List<UserInfo> findAll();
    public abstract UserInfo findById(String id);
    public void save(UserInfo userInfo);
    public abstract List<Role> findUserByIdAndAllRole(String id);
    public abstract void addRoleToUser(String userId,String roleId);
}
