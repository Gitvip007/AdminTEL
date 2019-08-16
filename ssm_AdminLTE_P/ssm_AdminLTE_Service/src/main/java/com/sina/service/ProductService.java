package com.sina.service;

import com.sina.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public void save(Product product);
}
