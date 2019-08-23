package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }

    @Override
    public PageResult<TbBrand> findBypage(Integer currPage, Integer pageSize) {
        //使用mybatis的分页插件
        PageHelper.startPage(currPage,pageSize);
        Page page = (Page) tbBrandMapper.selectByExample(null);

        //返回总记录数 和当前页显示的数据
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    /**
     * 添加品怕信息
     * @param tbBrand
     */
    @Override
    public void add(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    /**
     * 通过id查询品牌
     * @param id
     * @return
     */
    @Override
    public TbBrand findOne(long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改品牌
     * @param tbBrand
     */
    @Override
    public void update(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }


    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void dele(long[] ids) {
        for (long id : ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult<TbBrand> findPage(TbBrand tbBrand, Integer currPage, Integer pageSize) {
        //分页
        PageHelper.startPage(currPage,pageSize);

        //构建查询条件
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();

        if (tbBrand != null){
            if (tbBrand.getName()!=null && tbBrand.getName().length()>0){
                //构建查询条件 赋具体的查询条件值
                criteria.andNameLike("%"+tbBrand.getName()+"%");
            }
            if (tbBrand.getFirstChar()!=null && tbBrand.getFirstChar().length()>0){
                //构建查询条件 赋具体的查询条件值
                criteria.andFirstCharLike("%"+tbBrand.getFirstChar()+"%");
            }
        }

        Page page = (Page) tbBrandMapper.selectByExample(example);
        return new PageResult<>(page.getTotal(),page.getResult());
    }
}
