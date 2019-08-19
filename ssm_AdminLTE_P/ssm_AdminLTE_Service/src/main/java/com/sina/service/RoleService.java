package com.sina.service;

import com.sina.domain.Permission;
import com.sina.domain.Role;
import com.sina.domain.UserInfo;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有的用户信息
     * @return
     */
    public List<Role> findAll();

    /**
     * 添加角色信息
     * @param role
     */
    public void save(Role role);


    /**
     * 通过id查询role对象
     * @param id
     * @return
     */
    Role findByRoleId(String id);

    /**
     * 通过roid查询中间表的permissionId
     * 通过permissionId查询permission对象
     * @param id
     * @return
     */
    List<Permission> findOthersPermission(String id);

    /**
     * 给角色添加权限信息
     * @param roleId
     * @param ids
     */
    void addPermissionToRole(String roleId,String[] ids);
}
