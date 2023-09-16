package com.proj.web.mapper;

import com.proj.web.entity.SmartReportSecondPO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 二级通报表格数据Mapper
 */
@Mapper
@Component
public interface SecondCircularMapper {

    //ESC预警问题整改率
    String selectCircular001(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    盘活利库任务完成率
    BigDecimal selectCircular002fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    盘活利库任务完成率
    BigDecimal selectCircular002fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    盘活利库任务完成率
    BigDecimal selectCircular002fzbf(@Param("request") SmartReportSecondPO request, @Param("term") String term);


    //    计划申报正确率1 0.3 因0.3这个需要线下算，故不算结果取值为0
    BigDecimal selectCircular003fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    计划申报正确率1 0.3 因0.3这个需要线下算，故不算结果取值为1
    BigDecimal selectCircular003fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    计划申报正确率2 0.5
    BigDecimal selectCircular00301fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    计划申报正确率2 0.5
    BigDecimal selectCircular00301fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    计划申报正确率3  0.2
    BigDecimal selectCircular00302fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    计划申报正确率3  0.2
    BigDecimal selectCircular00302fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);


    //报废物资处置计划申报规范率
    BigDecimal selectCircular004fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);
    //报废物资处置计划申报规范率
    BigDecimal selectCircular004fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);
    //报废物资处置计划申报规范率
    BigDecimal selectCircular00402fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);
    //报废物资处置计划申报规范率
    BigDecimal selectCircular00402fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    服务一次采购成功率
    BigDecimal selectCircular005fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    服务一次采购成功率
    BigDecimal selectCircular005fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    物资一次采购成功率
    BigDecimal selectCircular005_1fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    物资一次采购成功率
    BigDecimal selectCircular005_1fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //服务类框架协议执行率fz
    BigDecimal selectCircular006fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //服务类框架协议执行率fm
    BigDecimal selectCircular006fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //技术规范书退回率-物资-暂无
    BigDecimal selectCircular007(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //技术规范书退回率-工程及服务-暂无
    BigDecimal selectCircular008(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    零星物资电商化请购总金额（万元）没有分子分母
    BigDecimal selectCircular009(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //逾期货款清理完成指数
    BigDecimal selectCircular010(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    供应计划完成率
    BigDecimal selectCircular011fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    供应计划完成率
    BigDecimal selectCircular011fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //物资供应计划调整率
    BigDecimal selectCircular012(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    到货交接单签署正确率
    BigDecimal selectCircular013fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    到货交接单签署正确率
    BigDecimal selectCircular013fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //     库存积压监控
    BigDecimal selectCircular014fz(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    库存积压监控
    BigDecimal selectCircular014fm(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //    非电网零星物资人工评价率-没有分母分子
    BigDecimal selectCircular015(@Param("request") SmartReportSecondPO request, @Param("term") String term);

    //出厂验收参与率
    BigDecimal selectCircular016(@Param("request") SmartReportSecondPO request, @Param("term") String term);


}
