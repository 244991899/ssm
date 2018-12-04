package com.itcast.ssm.service;

import com.itcast.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public abstract List<UserInfo> findAll();
    public abstract UserInfo findById(String id);
    public void save(UserInfo userInfo);
}
