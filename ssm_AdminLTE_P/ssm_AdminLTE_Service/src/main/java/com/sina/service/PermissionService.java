package com.sina.service;

import com.sina.domain.Permission;

import java.util.List;

public interface PermissionService {
    public List<Permission> findAll();

    public void save(Permission permission);
}
