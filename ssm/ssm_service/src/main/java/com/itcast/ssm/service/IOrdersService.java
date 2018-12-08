package com.itcast.ssm.service;

import com.itcast.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {
    public List<Orders> findAll(int page,int size);
    public Orders findById(String id);
    public void del(String[] ids);
}
