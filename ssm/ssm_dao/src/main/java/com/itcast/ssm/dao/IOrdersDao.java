package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Member;
import com.itcast.ssm.domain.Orders;
import com.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "Orders",property = "Orders"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "com.itcast.ssm.dao.IProductDao.findProductById"))
    })
    public List<Orders> findAll();

    /**
     * 根据订单id查询数据库
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "Orders",property = "Orders"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "com.itcast.ssm.dao.IProductDao.findProductById")),
            @Result(column = "memberId",property = "member",one = @One(select = "com.itcast.ssm.dao.IMemberDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "com.itcast.ssm.dao.ITravellerDao.findByID"))
    })
    public Orders findById(String id);

    /**
     * 删除order
     * @param id
     */
    @Delete("delete from orders where id = #{id}")
    public void del_order(String id);

    /**
     * 删除关联的中间表
     * @param id
     */
    @Delete("delete from order_traveller where orderId = #{id}")
    public void del_order_traveller(String id);
}
