package com.sina.dao;

import com.sina.domain.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> findAll();
}
