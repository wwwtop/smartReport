package com.proj.model.bo.request;

import com.proj.model.bo.search.BaseSearchCriteria;
import lombok.Data;

/**
 * 报表输入参数
 */
@Data
public class ReportRequest extends BaseSearchCriteria {

    /**
     * 类型
     */
    private String ztype;

    /**
     * 时间
     * 如 ： 2023
     */
    private String year;

    /**
     * 时间
     * 如 ： 2
     */
    private String month;

    /**
     * 时间
     * 如 ： 31
     */
    private String day;
    /**
     * 时间
     * 如 ： 2023-03
     */
    private String time;

    /**
     * 起始时间
     * 如 ：2023-03-01
     */
    private String startTime;
    /**
     * 结束时间
     * 如 ：2023-03-31
     */
    private String endTime;

    /**
     * 指标名称
     * 如 ：jhgl_001 或者 tb（代表同比,导出使用）
     */
    private String targetName;

}
