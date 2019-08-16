package com.sina.service;

import com.sina.domain.Orders;

import java.util.List;

public interface OrdersService {
    public List<Orders> findAll(int page, int size);

    Orders findById(String id);
}
