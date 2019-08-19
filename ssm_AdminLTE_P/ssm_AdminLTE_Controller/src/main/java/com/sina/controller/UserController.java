package com.sina.controller;

import com.sina.domain.Role;
import com.sina.domain.UserInfo;
import com.sina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 调用service层，查询user的全部信息
     * @return
     */
    @RequestMapping("/findAll")
    @PreAuthorize("principal.username='tom' and hasAnyRole('ROLE_USER')")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        List<UserInfo> infoList = userService.findAll();
        mav.addObject("userList",infoList);
        mav.setViewName("user-list");
        return mav;
    }


    /**
     * 添加用户信息，添加完了之后跳转到findAll方法，等于刷新了一下
     * @param userInfo
     * @return
     */
    @RequestMapping("/save")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll";
    }

    /**
     * 查询详情
     * 通过前端页面传过来的user 的 id 查询user对象（多表联查，user中还有其他属性。）
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mav = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mav.addObject("user",userInfo);
        mav.setViewName("user-show");
        return mav;
    }

    /**
     *通过前端页面传过来的user 的id，查询user对象
     *
     * 通过userId查询中间表中的roleid， 由roleId查询role对象
     *
     * 将查到的user对象，role对象存到request
     * @param id
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mav = new ModelAndView();

        UserInfo userInfo = userService.findById(id);
        List<Role> roleList =  userService.findOtherRoles(id);

        mav.addObject("user",userInfo);
        mav.addObject("roleList",roleList);
        mav.setViewName("user-role-add");
        return mav;
    }


    /**
     * 给指定用户添加角色信息
     * 操作用户和角色的中间表，将两者进行关联即可
     * @param userId
     * @param ids
     * @return
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId,String[] ids){
        userService.saveUserIdAndRoleId(userId,ids);
        return "redirect:findAll";
    }
}

