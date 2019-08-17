package com.sina.service.impl;

import com.sina.dao.PermissionDao;
import com.sina.domain.Permission;
import com.sina.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询所有的资源权限信息
     * 返回list集合
     * @return
     */
    public List<Permission> findAll() {
        List<Permission> permissionList = permissionDao.findAll();
        return permissionList;
    }

    /**
     * 添加资源权限信息
     * @param permission
     */
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
