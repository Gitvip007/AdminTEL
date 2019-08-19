package com.sina.service;

import com.sina.domain.SysLog;

import java.util.List;

public interface SysLogService {
    /**
     * 保存日志
     * @param sysLog
     */
    public void saveSysLog(SysLog sysLog);

    /**
     * 查询日志信息
     * @return
     */
    List<SysLog> findAll();
}
