package com.sina.service.impl;

import com.sina.dao.RoleDao;
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
}
