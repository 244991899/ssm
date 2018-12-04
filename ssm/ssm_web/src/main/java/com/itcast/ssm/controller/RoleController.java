package com.itcast.ssm.controller;

import com.itcast.ssm.domain.Role;
import com.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService iRoleService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Role> roleList = iRoleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("role-list");
        modelAndView.addObject("roleList",roleList);
        return modelAndView;
    }
    @RequestMapping(path = "/save")
    public String save(Role role){
        iRoleService.save(role);
        return "redirect:findAll";
    }
}
