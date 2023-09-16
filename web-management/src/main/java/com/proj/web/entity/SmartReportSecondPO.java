package com.proj.web.entity;

import lombok.Data;

/*
二级通报筛选条件
 */
@Data
public class SmartReportSecondPO {
    //筛选条件
    private String term;

    private String time;//2022-03
    private String year;//2022
    private String lastYear;//2021

    //当月
    private String startTime;
    private String endTime;

    //本年累计时间-到当月
    private String accStartTime;
    private String accEndTime;

    //去年累计时间-到去年当月
    private String accLastStartTime;
    private String accLastEndTime;


    
}
