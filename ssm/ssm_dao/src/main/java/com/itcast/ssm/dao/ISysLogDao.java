package com.itcast.ssm.dao;

import com.itcast.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ISysLogDao {
    /**
     * 添加日志
     * @param sysLog
     */
    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public abstract void save(SysLog sysLog);

    /**
     * 查询所有日志
     * @return
     */
    @Select("select *from sysLog")
    public abstract List<SysLog> findAll();
}
