package com.proj.web.mapper;

import com.proj.web.entity.SmartReportSecondPO;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 导出数据源
 */
@Mapper
@Component
public interface ExportMapper {
    //招标采购 国网公司物资采购数量及金额/北京公司物资类实施采购金额/北京公司服务类实施采购金额
    ArrayList<Map<String, Object>> selectExportZb001ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购 北京公司物资类实施采购金额
    ArrayList<Map<String, Object>> selectExportZb002ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购 北京公司服务类实施采购金额
    ArrayList<Map<String, Object>> selectExportZb003ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购 零星物资电商化请购条目及金额
    ArrayList<Map<String, Object>> selectExportZb004ByMonth(@Param("request") SmartReportSecondPO request);


    //招标采购 服务类框架协议执行条目及金额
    ArrayList<Map<String, Object>> selectExportZb005ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购——监控 物资类采购流标率
    ArrayList<Map<String, Object>> selectExportZb006ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购——监控 服务类采购流标率
    ArrayList<Map<String, Object>> selectExportZb007ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购——监控 一次采购成功率-暂无逻辑
    ArrayList<Map<String, Object>> selectExportZb008ByMonth(@Param("request") SmartReportSecondPO request);



    //招标采购——监控 物资类采购资金节约率
    ArrayList<Map<String, Object>> selectExportZb009ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购——监控 服务类采购资金节约率
    ArrayList<Map<String, Object>> selectExportZb010ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购——监控 物资类公开采购率
    ArrayList<Map<String, Object>> selectExportZb011ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购——监控 服务类公开采购率
    ArrayList<Map<String, Object>> selectExportZb012ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购——监控 服务类框架协议执行完成率——暂无 需要调整
    ArrayList<Map<String, Object>> selectExportZb013ByMonth(@Param("request") SmartReportSecondPO request);

    //招标采购——监控 原材料价格波动情况
    ArrayList<Map<String, Object>> selectExportZb014ByMonth(@Param("request") SmartReportSecondPO request);

    //合同 物资合同条目及金额
    ArrayList<Map<String, Object>> selectExportHt001ByMonth(@Param("request") SmartReportSecondPO request);

    //合同 采购供货单条目及金额
    ArrayList<Map<String, Object>> selectExportHt002ByMonth(@Param("request") SmartReportSecondPO request);

    //合同 合同变更条目及金额
    ArrayList<Map<String, Object>> selectExportHt003ByMonth(@Param("request") SmartReportSecondPO request);

    //合同——监控 合同签订及时率
    ArrayList<Map<String, Object>> selectExportHt004ByMonth(@Param("request") SmartReportSecondPO request);

    //合同 集中支付条目及金额
    ArrayList<Map<String, Object>> selectExportHt005ByMonth(@Param("request") SmartReportSecondPO request);

    //合同 非电商采购支付逾期条目及金额
    ArrayList<Map<String, Object>> selectExportHt006ByMonth(@Param("request") SmartReportSecondPO request);

    /*
    计划
     */
    //计划 物资类批次计划条目及金额
    ArrayList<Map<String, Object>> selectExportJh001ByMonth(@Param("request") SmartReportSecondPO request);

    //计划 物资类协议库存计划条目及金额
    ArrayList<Map<String, Object>> selectExportJh002ByMonth(@Param("request") SmartReportSecondPO request);

    //计划 物资类框架计划条目及金额
    ArrayList<Map<String, Object>> selectExportJh003ByMonth(@Param("request") SmartReportSecondPO request);

    //计划 服务类批次计划条目及金额
    ArrayList<Map<String, Object>> selectExportJh004ByMonth(@Param("request") SmartReportSecondPO request);

    //计划 服务类框架计划条目及金额
    ArrayList<Map<String, Object>> selectExportJh005ByMonth(@Param("request") SmartReportSecondPO request);

    //计划 协议库存匹配计划条目及金额
    ArrayList<Map<String, Object>> selectExportJh006ByMonth(@Param("request") SmartReportSecondPO request);

    //计划 跨单位利库条目及金额——暂时没写
    ArrayList<Map<String, Object>> selectExportJh007ByMonth(@Param("request") SmartReportSecondPO request);

    //计划 协议库存合同执行完成率——暂时没写
    ArrayList<Map<String, Object>> selectExportJh008ByMonth(@Param("request") SmartReportSecondPO request);

    //计划 物资采购计划报送准确率——暂时没写
    ArrayList<Map<String, Object>> selectExportJh009ByMonth(@Param("request") SmartReportSecondPO request);

    //计划 物资采购及时率——暂时没写
    ArrayList<Map<String, Object>> selectExportJh010ByMonth(@Param("request") SmartReportSecondPO request);
    //计划 平衡利库执行完成率—暂时没写
    ArrayList<Map<String, Object>> selectExportJh011ByMonth(@Param("request") SmartReportSecondPO request);
    //计划 废旧物资报废处置条目及金额
    ArrayList<Map<String, Object>> selectExportJh012ByMonth(@Param("request") SmartReportSecondPO request);
    //计划 报废处置流标率
    ArrayList<Map<String, Object>> selectExportJh013ByMonth(@Param("request") SmartReportSecondPO request);
    //计划 报废处置溢价率
    ArrayList<Map<String, Object>> selectExportJh014ByMonth(@Param("request") SmartReportSecondPO request);


    /*
    质监
     */
    //质监 设备抽检
    ArrayList<Map<String, Object>> selectExportZj001ByMonth(@Param("request") SmartReportSecondPO request);
    //质监 抽检合格率
    ArrayList<Map<String, Object>> selectExportZj002ByMonth(@Param("request") SmartReportSecondPO request);
    //质监 平均抽检总时长
    ArrayList<Map<String, Object>> selectExportZj003ByMonth(@Param("request") SmartReportSecondPO request);
    //质监 平均取样时长
    ArrayList<Map<String, Object>> selectExportZj004ByMonth(@Param("request") SmartReportSecondPO request);
    //质监 平均检测时长
    ArrayList<Map<String, Object>> selectExportZj005ByMonth(@Param("request") SmartReportSecondPO request);
    //质监 A类任务平均抽检总时长
    ArrayList<Map<String, Object>> selectExportZj006ByMonth(@Param("request") SmartReportSecondPO request);
    //质监 B类任务平均抽检总时长
    ArrayList<Map<String, Object>> selectExportZj007ByMonth(@Param("request") SmartReportSecondPO request);
    //质监 C类任务平均抽检总时长
    ArrayList<Map<String, Object>> selectExportZj008ByMonth(@Param("request") SmartReportSecondPO request);
    //质监 设备监造——暂无
    ArrayList<Map<String, Object>> selectExportZj009ByMonth(@Param("request") SmartReportSecondPO request);
    //质监 不良行为
    ArrayList<Map<String, Object>> selectExportZj010ByMonth(@Param("request") SmartReportSecondPO request);
    //质监 非电网零星物资人工评价率
    ArrayList<Map<String, Object>> selectExportZj011ByMonth(@Param("request") SmartReportSecondPO request);




}
