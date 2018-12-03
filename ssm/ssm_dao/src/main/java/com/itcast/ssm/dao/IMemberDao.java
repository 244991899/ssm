package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {
    @Select("select *from member where id = #{id}")
    public abstract Member findById(String id);
}
