package com.sina.service.impl;

import com.sina.dao.UserDao;
import com.sina.domain.Role;
import com.sina.domain.UserInfo;
import com.sina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //处理自己的用户对象封装成UserDetails
        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    /**
     * 作用就是返回一个List集合，集合中装入的是角色描述
     * @param roles
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        //创建一个集合，将传入的参数（角色）进行遍历，然后处理后存入集合。
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            //应为"访问系统的人，必须有ROLE_USER的角色" -->
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }
}
