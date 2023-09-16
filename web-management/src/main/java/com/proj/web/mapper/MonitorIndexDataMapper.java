package com.proj.web.mapper;

import com.proj.model.vo.BasicDataStatisticsVO;
import com.proj.model.vo.MonitorIndexDataVO;
import com.proj.web.entity.SmartReportPO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 监控指标数据统计表Mapper
 */
@Mapper
@Component
public interface MonitorIndexDataMapper {
    //计划管理-协议库存合同执行完成率
    List<LinkedHashMap> selectPlan001(@Param("request") SmartReportPO request);

    //计划管理-物资采购计划报送准确率——（物资采购计划送达准确率——0510）
    List<LinkedHashMap> selectPlan002(@Param("request") SmartReportPO request);

    //计划管理-物资采购及时率-0510
    List<LinkedHashMap> selectPlan003(@Param("request") SmartReportPO request);
    //计划管理-物资采购及时率
//    List<LinkedHashMap> selectPlan00301(@Param("request") SmartReportPO request);

    //招标采购-采购流标率-物资
    List<LinkedHashMap> selectTender001(@Param("request") SmartReportPO request);

    //招标采购-采购流标率-服务
    List<LinkedHashMap> selectTender002(@Param("request") SmartReportPO request);

    //招标采购-一次性采购成功率-暂无
    List<LinkedHashMap> selectTender003(@Param("request") SmartReportPO request);

    //招标采购-采购资金节约率-物资
    List<LinkedHashMap> selectTender004(@Param("request") SmartReportPO request);

    //招标采购-采购资金节约率-服务
    List<LinkedHashMap> selectTender005(@Param("request") SmartReportPO request);

    //招标采购-公开采购率-物资
    List<LinkedHashMap> selectTender006(@Param("request") SmartReportPO request);
    //招标采购-公开采购率-服务
    List<LinkedHashMap> selectTender007(@Param("request") SmartReportPO request);
    //招标采购-服务类框架协议执行完成率-暂无
    List<LinkedHashMap> selectTender008(@Param("request") SmartReportPO request);

    //合同签订-合同签订及时率
    List<LinkedHashMap> selectSigned001(@Param("request") SmartReportPO request);

    //合同履约-供应计划完成率
    List<LinkedHashMap> selectExercise001(@Param("request") SmartReportPO request);
    //合同履约-到货准确率
    List<LinkedHashMap> selectExercise002(@Param("request") SmartReportPO request);
    //合同履约-供应计划调整率
    List<LinkedHashMap> selectExercise003(@Param("request") SmartReportPO request);
    //合同履约-确定交货期超过合同交货期一年的供应计划占比-暂无
    List<LinkedHashMap> selectExercise004(@Param("request") SmartReportPO request);
    //合同履约-到货交接单平均办理时长
    List<LinkedHashMap> selectExercise006(@Param("request") SmartReportPO request);
    //合同履约-到货交接单平均办理时长
    List<LinkedHashMap> selectExercise007(@Param("request") SmartReportPO request);

    //合同履约-ELP监控里程准确率
    List<LinkedHashMap> selectExercise008(@Param("request") SmartReportPO request);


    //仓储管理-库存周转率
    List<LinkedHashMap> selectStorage001(@Param("request") SmartReportPO request);

    //仓储管理-库存积压
    BigDecimal selectStorage002(@Param("request") SmartReportPO request);

    //仓储管理-库存积压占总库存金额比例
    BigDecimal selectStorage003(@Param("request") SmartReportPO request);

    //仓储管理-当前借用物资金额
    List<LinkedHashMap> selectStorage004(@Param("request") SmartReportPO request);

    //仓储管理-专业仓活跃度
    List<LinkedHashMap> selectStorage005(@Param("request") SmartReportPO request);

    //平衡利库-平衡利库执行完成率
    List<LinkedHashMap> selectBalance001(@Param("request") SmartReportPO request);

    //废旧物资-报废处置溢价率
    List<LinkedHashMap> selectWaste001(@Param("request") SmartReportPO request);

    //废旧物资-报废处置流标率
    List<LinkedHashMap> selectWaste002(@Param("request") SmartReportPO request);




    //质量监督-抽检合格率
    List<LinkedHashMap> selectSupervision001(@Param("request") SmartReportPO request);

    //质量监督-非电网零星物资人工评价率
    List<LinkedHashMap> selectSupervision002(@Param("request") SmartReportPO request);

    //质量监督-平均抽检总时长
    List<LinkedHashMap> selectSupervision003(@Param("request") SmartReportPO request);

    //质量监督-平均取样时长
    List<LinkedHashMap> selectSupervision004(@Param("request") SmartReportPO request);

    //质量监督-平均检测时长
    List<LinkedHashMap> selectSupervision005(@Param("request") SmartReportPO request);

    //质量监督-A类任务平均抽检总时长
    List<LinkedHashMap> selectSupervision006(@Param("request") SmartReportPO request);

    //质量监督-B类任务平均抽检总时长
    List<LinkedHashMap> selectSupervision007(@Param("request") SmartReportPO request);

    //质量监督-C类任务平均抽检总时长
    List<LinkedHashMap> selectSupervision008(@Param("request") SmartReportPO request);



}
