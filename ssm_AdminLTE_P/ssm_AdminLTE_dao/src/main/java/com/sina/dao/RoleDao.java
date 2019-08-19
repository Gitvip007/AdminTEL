package com.sina.dao;

import com.sina.domain.Permission;
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

    /**
     * 通过roleId查询role对象
     * @param id
     * @return
     */
    @Select("select * from role where id=#{id}")
    Role findByRoleId(String id);

    /**
     * 通过roleId---中间表---查询permission的集合
     * @param id
     * @return
     */
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOthersPermission(String id);



    /**
     * 操作中间表，给角色添加权限
     * 两者相关连
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
