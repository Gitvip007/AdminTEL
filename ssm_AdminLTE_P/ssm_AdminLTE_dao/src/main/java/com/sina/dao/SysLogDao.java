package com.sina.dao;

import com.sina.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {
    /**
     * 保存日志信息
     * @param sysLog
     */
    @Insert("insert into sysLog (visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void saveSysLog(SysLog sysLog);

    /**
     * 查询日志信息
     * 返回集合对象
     * @return
     */
    @Select("select * from sysLog")
    List<SysLog> findAll();
}
