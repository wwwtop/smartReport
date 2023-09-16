package com.proj.model.vo;

import lombok.Data;


/**
 * 基础数据统计表
 * @author liuyafei
 */
@Data
public class BasicDataStatisticsVO {
    /**
     * 本月数量
     */
    private String fieldset1;
    /**
     * 本月金额（万元）
     */
    private String fieldset2;
    /**
     * 本年累计数量
     */
    private String fieldset3;
    /**
     * 本年累计金额
     */
    private String fieldset4;
    /**
     * 去年数据（可能是万元也可能是其他）
     */
    private String fieldset5;
    /**
     * 同比
     */
    private String fieldset6;
    /**
     * 同比变化情况
     */
    private String fieldset7;
}
