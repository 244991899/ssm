package com.itcast.ssm.dao;

import com.itcast.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface IUserDao {
    /**
     * 跟据姓名查询所有
     * @param username
     * @return
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",many = @Many(select = "com.itcast.ssm.dao.IRoleDao.findRoleById"))
    })
    public abstract UserInfo findByUserName(String username);

    /**
     * 查询所有的用户
     * @return
     */
    @Select("select *from Users")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = " status",property = " status"),
            @Result(column = "id",property = "roles",many = @Many(select = "com.itcast.ssm.dao.IRoleDao.findRoleById"))
    })
    public abstract List<UserInfo> findAll();
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",many = @Many(select = "com.itcast.ssm.dao.IRoleDao.findRoleById"))
    })
    public abstract UserInfo findById(String id);

    /**
     * 添加一个user
     * @param userInfo
     */
    @Insert("insert into users(username,email,password,phonenum,status) values(#{username},#{email},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo);
}
