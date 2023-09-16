package com.proj.model.vo;

import lombok.Data;

/**
 * 一级监控表
 */
@Data
public class MonitorIndexDataVO {
    /**
     * 当年数据
     */
    private String fieldset1;
    /**
     * 去年数据
     */
    private String fieldset2;
    /**
     * 同比
     */
    private String fieldset3;
}
