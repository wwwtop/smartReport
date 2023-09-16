package com.proj.model.vo;

import lombok.Data;


/**
 * 物资公司预警情况统计表
 * @author liuyafei
 */
@Data
public class MaterialsVO {
    /**
     * 财务预警数量
     */
    private String fieldset1;
    /**
     * 财务办结比例
     */
    private String fieldset2;
    /**
     * 合同预警数量
     */
    private String fieldset3;
    /**
     * 合同办结比例
     */
    private String fieldset4;
    /**
     * 质监预警数量
     */
    private String fieldset5;
    /**
     * 质监办结比例
     */
    private String fieldset6;
    /**
     * 仓储预警数量
     */
    private String fieldset7;

    /**
     * 仓储办结比例
     */
    private String fieldset8;
    /**
     * 总计
     */
    private String fieldset9;
}
