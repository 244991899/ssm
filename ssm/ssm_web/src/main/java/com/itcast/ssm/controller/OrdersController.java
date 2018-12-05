package com.itcast.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itcast.ssm.domain.Orders;
import com.itcast.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/Order")
public class OrdersController {
    @Autowired
    private IOrdersService iOrdersService;
    /*不分页*/
    /*@RequestMapping(path = "/findAll")
    public ModelAndView findAll(){
        List<Orders> ordersList = iOrdersService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ordersList",ordersList);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }*/
    /*分页*/
    @RequestMapping(path = "/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")int page, @RequestParam(name = "size",required = true,defaultValue = "4")int size){
        List<Orders> ordersList = iOrdersService.findAll(page,size);
        ModelAndView modelAndView = new ModelAndView();
        /*把查询到的结果封装到pageInfo对象中。*/
        PageInfo pageInfo = new PageInfo(ordersList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-page-list");
        return modelAndView;
    }
    /*跳转订单详情页面*/
    @RequestMapping(path = "/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id){
        Orders orders = iOrdersService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
