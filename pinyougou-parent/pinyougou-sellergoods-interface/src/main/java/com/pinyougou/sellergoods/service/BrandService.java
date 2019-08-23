package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.TbBrand;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有的品牌记录
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 查询所有品牌记录，分页
     * @param currPage 当前页
     * @param pageSize 每页显示的条数
     * @return
     */
    PageResult<TbBrand> findBypage(Integer currPage, Integer pageSize);

    /**
     * 添加品牌信息
     * @param tbBrand
     */
    void add(TbBrand tbBrand);

    /**
     * 通过id查询品牌
     * @param id
     * @return
     */
    public TbBrand findOne(long id);

    /**
     * 修改品牌
     * @param tbBrand
     */
    public void update(TbBrand tbBrand);

    /**
     * 批量删除
     * @param ids
     */
    public void dele(long[] ids);

    /**
     * 品牌分页
     * @param tbBrand 查询条件
     * @param currPage  当前页
     * @param pageSize  每页显示的条数
     * @return
     */
    public PageResult<TbBrand> findPage(TbBrand tbBrand,Integer currPage, Integer pageSize);
}
