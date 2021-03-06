package com.sina.service;

import com.sina.domain.Role;
import com.sina.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * 查询所有的用户信息，返回list集合
     * @return
     */
    public List<UserInfo> findAll();

    /**
     * 添加用户信息
     * @param userInfo
     */
    public void save(UserInfo userInfo);

    /**
     * 通过User中的id查询user对象
     * @param id
     * @return
     */
    public UserInfo findById(String id);


    /**
     * 查询没有选定的role角色信息
     * @param id
     * @return
     */
    List<Role> findOtherRoles(String id);

    /**
     * 给指定的用户添加角色信息
     * 操作user 和 role 的中间表
     * @param userId
     * @param ids
     */
    void saveUserIdAndRoleId(String userId, String[] ids);
}
