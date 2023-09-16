package com.proj.model.vo;

import lombok.Data;

/**
 * 三级运营中心预警情况统计表
 * @author liuyafei
 */
@Data
public class ThreeLevelVO {
    /**
     * 合同签订后超期未开展履约
     */
    private String fieldset1;
    /**
     * 投运单办理预警
     */
    private String fieldset2;
    /**
     * 实体库库存物资超期未领用
     */
    private String fieldset3;
    /**
     * 虚拟库库存物资超期未领用
     */
    private String fieldset4;
    /**
     * 总计
     */
    private String fieldset5;
}
