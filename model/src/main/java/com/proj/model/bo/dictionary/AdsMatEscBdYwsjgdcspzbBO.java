package com.proj.model.bo.dictionary;

import lombok.Data;

import java.util.Date;

/**
 * 固定值配置表实体BO
 *
 * @author dong.ning
 */
@Data
public class AdsMatEscBdYwsjgdcspzbBO {

    /**
     * id
     */
    private int id;

    /**
     * 业务类型
     */
    private String ywlx;

    /**
     * 页面的数据项参数指标名称
     */
    private String cszbmc;

    /**
     * 值存储类型 1=原始接口的数据值，2=固定值
     */
    private int cclx;

    /**
     * 固定值
     */
    private String gdz;

    /**
     * 日期时间
     */
    private String rqsj;

    /**
     * 最后更新时间
     */
    private Date zhgxsj;

}
