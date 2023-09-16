package com.proj.model.bo.request;

import lombok.Data;

/**
 * 配置单一数据项
 */
@Data
public class UnicityRequest {


    /**
     * 指标编号
     */
    private String zzbbh;
    /**
     * 当前字段数值
     */
    private String fixed;
    /**
     * 是否显示
     */
    private String isFixed;
    /**
     * 类型
     */
    private String ztype;
    /**
     * 时间
     */
    private String zsj;

}
