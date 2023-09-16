package com.proj.model.vo.second;

import com.proj.model.annotation.DefaultValueAnno;
import lombok.Data;
/*
二级通报表vo
 */
@Data
public class SecondCircularVO {
    /**
     * ESC预警问题整改率
     */
//    @DefaultValueAnno(defaultValue = "*", werkName = "BP28")
    private String fieldset1;
    /**
     * 盘活利库任务完成率
     */
    private String fieldset2;
    /**
     * 计划申报正确率
     */
    private String fieldset3;
    /**
     * 报废物资处置计划申报规范率
     */
    private String fieldset4;
    /**
     * 一次采购成功率
     */
    private String fieldset5;

    /**
     * 服务类框架协议执行率
     */
    private String fieldset6;
    /**
     * 技术规范书退回率-物资
     */
    private String fieldset7;
    /**
     * 技术规范书退回率-工程及服务
     */
    private String fieldset8;
    /**
     * 零星物资电商化请购总金额（万元）
     */
    private String fieldset9;
    /**
     * 逾期货款清理完成指数（万元）
     */
    private String fieldset10;
    /**
     * 供应计划完成率
     */
    private String fieldset11;
    /**
     * 物资供应计划调整率
     */
    private String fieldset12;
    /**
     * 到货交接单签署正确率
     */
    private String fieldset13;
    /**
     * 库存积压监控
     */
    private String fieldset14;
    /**
     * 非电网零星物资人工评价率
     */
    private String fieldset15;
    /**
     * 出厂验收参与率
     */
    private String fieldset16;

    /**
     * 出厂验收参与率
     */
    private String fieldset17;
}
