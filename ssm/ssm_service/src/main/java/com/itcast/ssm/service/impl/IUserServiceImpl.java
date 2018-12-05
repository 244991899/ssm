package com.itcast.ssm.service.impl;

import com.itcast.ssm.dao.IUserDao;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.domain.UserInfo;
import com.itcast.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = iUserDao.findByUserName(username);
        /*这个user对象是UserDetails下的一个实现类，验证账号，密码，授权状态，。。。和权限等信息*/
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==1?true:false,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    /**
     * 把获取到的角色集合，交给SimpleGrantedAuthority，springSecurity会对其进行验证管理
     * @param roles
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authoritys;
    }

    @Override
    public List<UserInfo> findAll() {
        return iUserDao.findAll();
    }

    @Override
    public UserInfo findById(String id) {
        return iUserDao.findById(id);
    }

    @Override
    public void save(UserInfo userInfo) {
        /*给用户加密*/
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        iUserDao.save(userInfo);
    }

    @Override
    public List<Role> findUserByIdAndAllRole(String id) {
        return iUserDao.findUserByIdAndAllRole(id);
    }

    @Override
    public void addRoleToUser(String userId, String roleId) {
        iUserDao.addRoleToUser(userId, roleId);
    }
}
