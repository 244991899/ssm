package com.itcast.ssm.controller;

import com.itcast.ssm.domain.UserInfo;
import com.itcast.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @RequestMapping(path = "/findAll")
    public ModelAndView findAll(){
        List<UserInfo> infoList = iUserService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",infoList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }
    @RequestMapping(path = "/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id){
        UserInfo user = iUserService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }
    @RequestMapping(path = "/save")
    public String save(UserInfo userInfo){
        iUserService.save(userInfo);
        return "redirect:findAll";
    }
}
