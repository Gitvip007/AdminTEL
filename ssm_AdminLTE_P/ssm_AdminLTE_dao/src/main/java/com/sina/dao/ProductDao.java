package com.sina.dao;

import com.sina.domain.Product;

import java.util.List;

public interface ProductDao {

    /**
     * 查询数据库中对应的信息
     * @return
     */
    public List<Product> findAll();

    /**
     * 添加产品信息
     * @param product
     */
    public void save(Product product);


    /**
     * 通过id查找产品信息
     * @param id
     * @return
     */
    public Product findById(String id);
}
