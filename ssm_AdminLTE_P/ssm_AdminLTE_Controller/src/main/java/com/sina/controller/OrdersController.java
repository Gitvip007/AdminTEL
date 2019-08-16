package com.sina.controller;
import com.github.pagehelper.PageInfo;
import com.sina.domain.Orders;
import com.sina.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

   /* *//**
     * 未分页
     * @param mav
     * @return
     *//*
    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView mav){
        List<Orders> ordersList = ordersService.findAll();
        mav.addObject("ordersList",ordersList);
        mav.setViewName("orders-list");
        return mav;
    }*/


    /**
     * 分页显示
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") int page,@RequestParam(name = "size",required = true,defaultValue = "5") int size){
        ModelAndView mav = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);

        //PageInfo 就是一个分页bean，需要传入集合 参数
        PageInfo pageInfo = new PageInfo(ordersList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("orders-page-list");
        return mav;
    }


    /**
     * 通过页面传过来的orders中的id查询orders中的相关信息以及对应的表中的数据（多表联查）
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mav = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mav.addObject("orders",orders);
        mav.setViewName("orders-show");
        return mav;
    }

}
