package com.sina.service.impl;

import com.sina.dao.SysLogDao;
import com.sina.domain.SysLog;
import com.sina.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    public void saveSysLog(SysLog sysLog) {
        sysLogDao.saveSysLog(sysLog);
    }

    /**
     * 查询日志信息
     * 返回list《日志对象》
     * @return
     */
    public List<SysLog> findAll() {
        List<SysLog> sysLogList = sysLogDao.findAll();
        return sysLogList;
    }
}
