package com.sina.controller;

import com.sina.domain.Permission;
import com.sina.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 资源权限管理
     * 查询资源权限信息
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mav.addObject("permissionList",permissionList);
        mav.setViewName("permission-list");
        return mav;
    }

    /**
     * 添加资源权限 信息
     * @param permission
     * @return
     */
    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll";
    }
}
