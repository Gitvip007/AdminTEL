package com.sina.dao;

import com.sina.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerfDao {


    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{id})")
    public List<Traveller> findById(String id);
}
