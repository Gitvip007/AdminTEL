package com.sina.controller;

import com.sina.domain.Permission;
import com.sina.domain.Role;
import com.sina.domain.UserInfo;
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

    /**
     * 通过页面传类的roleId查询 role对象
     * 通过中间表查询role对象没有的 权限信息（集合）
     * @param id
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mav = new ModelAndView();
        Role role = roleService.findByRoleId(id);
        List<Permission> permissionList = roleService.findOthersPermission(id);
        mav.addObject("role",role);
        mav.addObject("permissionList",permissionList);
        mav.setViewName("role-permission-add");
        return mav;
    }

    /**
     * 给角色添加权限信息
     * @param roleId
     * @param ids
     * @return
     */
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(String roleId,String[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll";
    }

}
