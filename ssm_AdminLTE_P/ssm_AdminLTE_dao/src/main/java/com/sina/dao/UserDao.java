package com.sina.dao;

import com.sina.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserDao {

    /**
     * 用户访问时的权限验证查询，在service将查询到的数据进行处理返回，如果正确，才可以访问相关页面。
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",many = @Many(select = "com.sina.dao.RoleDao.findById"))
    })
    public UserInfo findByUsername(String username);

    /**
     * 查询所有的用户信息
     * @return
     */
    @Select("select * from users")
    public List<UserInfo> findAll();

    /**
     * 添加用户信息
     * @param UserInfo
     */
    @Insert("insert into users (username,email,password,phoneNum,status) values(#{username},#{email},#{password},#{phoneNum},#{status})")
    public void save(UserInfo UserInfo);


    /**
     * 查询详情
     *
     * 通过页面传来的user中的id 查询user（UserInfro）对象，UserIfon中有Role属性，role中有权限属性，所以属于多表联查
     * @param id
     * @return
     */
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",many = @Many(select = "com.sina.dao.RoleDao.findById"))
    })
    public UserInfo findById(String id);
}
