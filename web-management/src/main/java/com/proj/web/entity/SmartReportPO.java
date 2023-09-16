package com.proj.web.entity;

import lombok.Data;

/**
 * 一级筛选条件
 */
@Data
public class SmartReportPO {

    private String month;//03
    private String time;//2023-03
    private String lastTime;//2022-03
    private String year;//2023
    private String lastYear;//2022

    //当月
    private String startTime;
    private String endTime;

    //去年当月
    private String lastStartTime;

    //本年累计时间-到当月
    private String accStartTime;
    private String accEndTime;

    //去年累计时间-到去年当月
    private String accLastStartTime;
    private String accLastEndTime;


}
