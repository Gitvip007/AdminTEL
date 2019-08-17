package com.sina.service;

import com.sina.domain.Role;

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
}
