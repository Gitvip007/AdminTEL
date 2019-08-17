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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        //处理自己的用户对象 封装成UserDetails的实现类 返回
        User user = new User(userInfo.getUsername(),userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
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

    /**
     * 查询所有的用户信息，返回list集合
     * @return
     */
    public List<UserInfo> findAll() {
        List<UserInfo> infoList = userDao.findAll();
        return infoList;
    }

    /**
     * 以在配置文件中配置好对应的bean，在此处注入就行
     *
     * 还有一种方式是不用配置对应的bean，在utils包中new 对象，然后在此处调用
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 添加用户信息
     * @param userInfo
     */
    public void save(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }


    /**
     * 查询详情
     * 通过页面传来的id，查询user对象
     * @param id
     * @return
     */
    public UserInfo findById(String id) {
        UserInfo userInfo = userDao.findById(id);
        return userInfo;
    }
}
