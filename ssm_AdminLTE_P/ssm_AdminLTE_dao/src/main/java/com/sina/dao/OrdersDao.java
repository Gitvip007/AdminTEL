package com.sina.dao;

import com.sina.domain.Member;
import com.sina.domain.Orders;
import com.sina.domain.Product;
import com.sina.domain.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
    /**
     * 查询订单信息
     * @return
     */

    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.sina.dao.ProductDao.findById")),
    })
    public List<Orders> findAll();


    /**
     * 通过页面传过来的order中的id查询对应orders表中的信息，以及关联表中的信息，输入多表联查
     * @param id
     * @return
     */

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.sina.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.sina.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "com.sina.dao.TravellerfDao.findById"))
    })
    public Orders findById(String id);
}
