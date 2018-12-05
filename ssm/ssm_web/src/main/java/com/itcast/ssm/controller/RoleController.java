package com.itcast.ssm.controller;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(path = "/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)String id){
        Role role = iRoleService.findRoleByIdAndAllPermission(id);
        List<Permission> permissionList = iRoleService.findAllPermissionById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }
    @RequestMapping(path = "/addPermissionToRole")
    public ModelAndView addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true) String ids){
        iRoleService.addPermissionToRole(roleId,ids);
        Role role = iRoleService.findRoleByIdAndAllPermission(roleId);
        List<Permission> permissionList = iRoleService.findAllPermissionById(roleId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }
}
