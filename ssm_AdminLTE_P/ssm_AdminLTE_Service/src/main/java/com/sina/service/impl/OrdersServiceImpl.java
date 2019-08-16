package com.sina.service.impl;

import com.github.pagehelper.PageHelper;
import com.sina.dao.OrdersDao;
import com.sina.domain.Orders;
import com.sina.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

/*    *//**
     * 未分页
     *//*
    public List<Orders> findAll() {
        List<Orders> ordersList = ordersDao.findAll();
        return ordersList;
    }*/

    /**
     * 未分页
     * @return
     * @param page
     * @param size
     */
    public List<Orders> findAll(int page, int size) {
        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        //必须写在执行sql的代码前面
        PageHelper.startPage(page,size);
        List<Orders> ordersList = ordersDao.findAll();
        return ordersList;
    }

    /**
     * 通过orders中的id查询order表中以及相关表中的信息，输入多表联查
     * @param id
     * @return
     */
    public Orders findById(String id){
        Orders orders = ordersDao.findById(id);
        return orders;
    }
}
