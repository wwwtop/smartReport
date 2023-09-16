package com.proj.web.entity;

import lombok.Data;

/**
 * 指标详情PO-暂时不用
 */
@Data
public class SmartReportAddPO {
    /**
     * 指标编号
     */
    public String indexNumber;
    /**
     * 指标数值
     */
    public String valueIndex;
    /**
     * 创建时间
     */
    public String creationTime;
    /**
     * 来源
     */
    public String source;
}
