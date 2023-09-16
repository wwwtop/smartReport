package com.proj.web.controller;


import com.proj.model.bo.request.ReportRequest;
import com.proj.web.service.*;
import com.proj.web.util.TimeUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

import static com.proj.web.util.TimeUtil.lastMonth;

/**
 * 定时
 */
@Component
@EnableScheduling
public class GylyyReportScheduleController {

    @Resource
    private SmartReportController smartReportController;

    @Resource
    private SmartReportSecondController smartReportSecondController;

    @Resource
    private DiyController diyController;


//    @Scheduled(cron ="0/10 * * * * ?")//每五秒执行一次
//    @Scheduled(cron ="0 0/1 * * * ?")//每3分钟执行一次
    @Scheduled(cron ="0 0 7 1 1/1 ?")//每月一日7点执行一次
//    @Scheduled(cron ="0 0 0 28 1/1 ?")//每月28号执行一次
    public void addGylyyReport(){
        System.out.println("月报任务开始-------------------------");
        try {
            ReportRequest request = lastMonth();
            smartReportController.getBasicDataStatisticsMap(request);
            smartReportSecondController.getSecondCircularMap(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("月报任务结束-------------------------");
    }


    @Scheduled(cron ="0 0 1 * * ?")//每天凌晨一点执行一次
    public void updateDiy(){
        System.out.println("自定义配置月报开始-------------------------");
        try {
            ReportRequest request = lastMonth();
            diyController.upDiy(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("自定义配置月报结束-------------------------");
    }

}
