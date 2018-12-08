package com.itcast.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itcast.ssm.dao.IOrdersDao;
import com.itcast.ssm.domain.Orders;
import com.itcast.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao iOrdersDao;
    @Override
    public List<Orders> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        return iOrdersDao.findById(id);
    }

    /**
     * 先删除order和traveller中间表，每个订单必定有旅客
     * 再删除order
     * @param ids
     */
    @Override
    public void del(String[] ids) {
        for (String id : ids) {
            iOrdersDao.del_order_traveller(id);
            iOrdersDao.del_order(id);
        }
    }
}
