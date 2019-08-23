package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    /**
     * 查询所有记录
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }


    /**
     * 分页查询
     * @param currPage 当前页
     * @param pageSize 每页显示的条数
     * @return   返回PageResult对象（自定义类：只有两个属性 总记录数，当前页的结果集合）
     */
    @RequestMapping("/findByPage.do")
    public PageResult<TbBrand> findByPage(Integer currPage,Integer pageSize){
        return brandService.findBypage(currPage,pageSize);
    }

    /**
     * 添加品牌信息
     * @RequestBody前端传递给后端的是json格式的对象
     * @param tbBrand
     * @return
     */
    @RequestMapping("/add.do")
    public Result add(@RequestBody TbBrand tbBrand){
        try {
            brandService.add(tbBrand);
            return new Result(true,"保存成功");
        } catch (Exception e) {
            return new Result(false,"保存失败");
        }

    }

    /**
     * 通过id查询品牌
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbBrand findOne(long id){
        return brandService.findOne(id);
    }

    /**
     * 修改品牌
     * @param tbBrand
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand){
        try {
            brandService.update(tbBrand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            return new Result(false,"修改失败");
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/dele")
    public Result dele(long[] ids){
        try {
            brandService.dele(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            return new Result(false,"删除失败");
        }
    }

    /**
     * 分页   条件查询
     * @param tbBrand   查询条件
     * @param currPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/search")
    public PageResult<TbBrand> search(@RequestBody TbBrand tbBrand,Integer currPage,Integer pageSize){
        return brandService.findPage(tbBrand,currPage,pageSize);
    }

}
