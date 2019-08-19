package com.sina.service.impl;

import com.sina.dao.RoleDao;
import com.sina.domain.Permission;
import com.sina.domain.Role;
import com.sina.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有的用户信息
     * @return
     */
    public List<Role> findAll() {
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }

    /**
     *添加角色信息
     * @param role
     */
    public void save(Role role) {
        roleDao.save(role);
    }


    /**
     * 查询role对象
     * @param id
     * @return
     */
    public Role findByRoleId(String id) {
        return roleDao.findByRoleId(id);
    }

    /**
     * 通过roleId查询Permission的集合（通过中间表）
     * @param id
     * @return
     */
    public List<Permission> findOthersPermission(String id) {
        List<Permission> permissions =  roleDao.findOthersPermission(id);
        return permissions;
    }

    /**
     * 给角色添加权限信息
     * @param roleId
     * @param ids
     */
    public void addPermissionToRole(String roleId,String[] ids) {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
