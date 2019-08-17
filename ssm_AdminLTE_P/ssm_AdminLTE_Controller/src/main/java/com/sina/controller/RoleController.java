package com.sina.controller;

import com.sina.domain.Role;
import com.sina.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色管理
     * 查询所有的用户信息
     * @param mav
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView mav){
        List<Role> roleList = roleService.findAll();
        mav.addObject("roleList",roleList);
        mav.setViewName("role-list");
        return mav;
    }

    /**
     * 添加用户信息
     * @param role
     * @return
     */
    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }

}
