package com.itcast.ssm.controller;

import com.itcast.ssm.domain.Product;
import com.itcast.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @RequestMapping("/findAll")
    @RolesAllowed("ROLE_VIP")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = iProductService.findAll();
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String save(Product product){
        iProductService.save(product);
        return "redirect:findAll";
    }
}
