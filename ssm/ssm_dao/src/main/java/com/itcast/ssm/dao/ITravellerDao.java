package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ITravellerDao {
    /**
     * 根据id查询旅客
     * @param orderId
     * @return
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{orderId})")
    public abstract Traveller findByID(String orderId);
}
