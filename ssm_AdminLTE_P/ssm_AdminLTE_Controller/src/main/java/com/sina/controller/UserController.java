package com.sina.controller;

import com.sina.domain.UserInfo;
import com.sina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}

