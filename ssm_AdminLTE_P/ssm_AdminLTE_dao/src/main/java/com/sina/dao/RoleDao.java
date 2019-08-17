package com.sina.dao;

import com.sina.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    /**
     * 查询users_role中的roleId
     * 通过roleId查询role 中的角色
     *
     * @param id
     * @return
     */
    @Select("select * from role where id in(select roleId from users_role where userId=#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",many = @Many(select = "com.sina.dao.PermissionDao.findByid"))
    })
    public List<Role> findById(String id);

    /**
     * 查询所有的用户信息，返回list集合
     * @return
     */
    @Select("select * from role")
    public List<Role> findAll();


    /**
     * 添加一个角色信息
     * @param role
     */
    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role);

}
