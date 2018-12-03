package com.itcast.ssm.service;

import com.itcast.ssm.domain.Product;

import java.util.List;

public interface IProductService {
    public abstract List<Product> findAll();
    public abstract void save(Product product);
}
