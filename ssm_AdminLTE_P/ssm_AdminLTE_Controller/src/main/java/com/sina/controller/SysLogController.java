package com.sina.controller;

import com.sina.domain.SysLog;
import com.sina.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询所有日志信息
     * @param mav
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView mav){
       List<SysLog> sysLogList =  sysLogService.findAll();
       mav.addObject("sysLogs",sysLogList);
       mav.setViewName("syslog-list");
       return mav;
    }
}
