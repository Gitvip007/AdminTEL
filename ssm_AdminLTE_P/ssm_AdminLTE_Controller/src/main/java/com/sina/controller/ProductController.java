package com.sina.controller;

import com.sina.domain.Product;
import com.sina.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有信息
     * @param mav
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView mav){
        List<Product> productList = productService.findAll();
        mav.addObject("productList",productList);
        mav.setViewName("product-list");
        return mav;
    }


    /**
     * 添加产品信息
     */
    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll";
    }
}
