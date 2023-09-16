package com.proj.web.mapper;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.BasicDataStatisticsVO;
import com.proj.web.entity.SmartReportAddPO;
import com.proj.web.entity.SmartReportPO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 基础数据统计表Mapper
 */
@Mapper
@Component
public interface BasicDataStatisticsMapper {

    /**
     * 采购计划好像sql重复了-需要重新写
     *
     * @param request
     * @return
     */
    //采购计划-物资类批次计划——0510
    List<LinkedHashMap> selectPurchase001(@Param("request") SmartReportPO request);

    //采购计划-物资类协议库存计划——0510
    List<LinkedHashMap> selectPurchase002(@Param("request") SmartReportPO request);

    //采购计划-物资类框架计划——0510
    List<LinkedHashMap> selectPurchase003(@Param("request") SmartReportPO request);

    //采购计划-协议库存匹配计划
    List<LinkedHashMap> selectPurchase004(@Param("request") SmartReportPO request);

    //采购计划-服务类批次计划——0510
    List<LinkedHashMap> selectPurchase005(@Param("request") SmartReportPO request);

    //采购计划-服务类框架计划——0510
    List<LinkedHashMap> selectPurchase006(@Param("request") SmartReportPO request);


    //招标采购-国网公司实施采购
    List<LinkedHashMap> selectTender001(@Param("request") SmartReportPO request);

    //招标采购-物资类
    List<LinkedHashMap> selectTender002(@Param("request") SmartReportPO request);

    //招标采购-服务类
    List<LinkedHashMap> selectTender003(@Param("request") SmartReportPO request);

    //招标采购-零星物资电商化请购金额
    List<LinkedHashMap> selectTender004(@Param("request") SmartReportPO request);

    //招标采购-.服务类框架协议执行
    List<LinkedHashMap> selectTender005(@Param("request") SmartReportPO request);


    /**
     * 合同签订
     */
    //合同签订-物资合同
    List<LinkedHashMap> selectSigned001(@Param("request") SmartReportPO request);


    //合同签订-采购供货单
    List<LinkedHashMap> selectSigned002(@Param("request") SmartReportPO request);

    //合同签订-合同变更
    List<LinkedHashMap> selectSigned003(@Param("request") SmartReportPO request);

    /**
     * 合同履约
     */
//    //合同履约-物资到货
    List<LinkedHashMap> selectExercise001(@Param("request") SmartReportPO request);

    //合同履约-ELP监控
    List<LinkedHashMap> selectExercise002(@Param("request") SmartReportPO request);

    /**
     * 合同结算
     */
//    //合同结算-集中支付
    List<LinkedHashMap> selectClearing001(@Param("request") SmartReportPO request);

    //合同结算-非电商采购支付逾期
    List<LinkedHashMap> selectClearing002(@Param("request") SmartReportPO request);


    /**
     * 仓储管理
     */
    //仓储管理-北京公司库存情况库存
    List<LinkedHashMap> selectStorage001(@Param("request") SmartReportPO request);

    //仓储管理-北京公司库存情况库存
    BigDecimal selectStorage001p1(@Param("request") SmartReportPO request);

    //仓储管理-北京公司库存情况库存
    List<LinkedHashMap> selectStorage001p2(@Param("request") SmartReportPO request);


    //仓储管理-北京公司库存情况入存
    List<LinkedHashMap> selectStorage002(@Param("request") SmartReportPO request);

    //仓储管理-北京公司库存情况出存
    List<LinkedHashMap> selectStorage003(@Param("request") SmartReportPO request);

    //仓储管理-北京公司库存情况代保管物资入库
    List<LinkedHashMap> selectStorage004(@Param("request") SmartReportPO request);

    //仓储管理-北京公司库存情况代保管物资出库
    List<LinkedHashMap> selectStorage005(@Param("request") SmartReportPO request);

    //仓储管理-专业仓情况库存数量
    Integer selectStorage006n1();

    //仓储管理-专业仓情况库存金额
    BigDecimal selectStorage006p1();

    //仓储管理-专业仓情况入仓
    List<LinkedHashMap> selectStorage007(@Param("request") SmartReportPO request);

    //仓储管理-专业仓情况出仓
    List<LinkedHashMap> selectStorage008(@Param("request") SmartReportPO request);

    //仓储管理-主动配送
    List<LinkedHashMap> selectStorage009(@Param("request") SmartReportPO request);


    /*
    平衡利库-跨单位利库条目及金额
     */
    List<LinkedHashMap> selectBalance001(@Param("request") SmartReportPO request);


    /*
    废旧物资
     */
    //废旧物资-报废处置
    List<LinkedHashMap> selectWaste001(@Param("request") SmartReportPO request);

    /*
    质量监督
     */
    // 质量监督-设备监造
    List<LinkedHashMap> selectSupervision001(@Param("request") SmartReportPO request);

    // 质量监督-设备抽检
    List<LinkedHashMap> selectSupervision002(@Param("request") SmartReportPO request);

    /*
    供应商管理
     */
    List<LinkedHashMap> selectSupplier001(@Param("request") SmartReportPO request);

}
