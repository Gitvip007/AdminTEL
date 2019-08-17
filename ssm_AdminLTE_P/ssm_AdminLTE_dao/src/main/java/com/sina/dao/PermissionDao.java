package com.sina.dao;

import com.sina.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {


    /**
     * 查询permission对象
     * @param id
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findByid(String id);

    /**
     * 查询所有的资源权限信息
     * 返回list集合
     * @return
     */
    @Select("select * from  permission")
    public List<Permission> findAll();

    /**
     * 添加资源权限信息
     * @param permission
     */
    @Insert("insert into permission (permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission);
}
