package com.proj.web.mapper;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.UndulationVO;
import com.proj.web.entity.SmartReportAddPO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

@Mapper
@Component
public interface MainBadyMapper {

    /*
    折线图
     */
   // 正文铝价最大和最小
    List<LinkedHashMap> selectUndulation001(@Param("request") ReportRequest request);
    //当前铝价最近的
    BigDecimal selectUndulation001p1(@Param("time") String time);
    //当前上月铝价最近的
    BigDecimal selectUndulation001p2(@Param("time") String time);

    //正文铜价最大和最小
    List<LinkedHashMap> selectUndulation002(@Param("request") ReportRequest request);

    //当前铜价最近的
    BigDecimal selectUndulation002p1(@Param("time") String time);
    //当前上月铜价最近的
    BigDecimal selectUndulation002p2(@Param("time") String time);

    //角钢最大和最小
    List<LinkedHashMap> selectUndulation006(@Param("request") ReportRequest request);

    //当前铜价最近的
    BigDecimal selectUndulation006p1(@Param("time") String time);

    //当前上月铜价最近的
    BigDecimal selectUndulation006p2(@Param("time") String time);

    List<UndulationVO> selectUndulation003(@Param("time") String time,@Param("lastTime") String lastTime);

    List<UndulationVO> selectUndulation004(@Param("time") String time,@Param("lastTime") String lastTime);

    List<UndulationVO> selectUndulation005(@Param("time") String time,@Param("lastTime") String lastTime);



    BigDecimal selectMainBady001(@Param("request") ReportRequest request);
    BigDecimal selectMainBady002(@Param("request") ReportRequest request);
    //暂无
    BigDecimal selectMainBady003(@Param("request") ReportRequest request);

    BigDecimal selectMainBady004(@Param("request") ReportRequest request);
    Integer selectMainBady005(@Param("request") ReportRequest request);
    Integer selectMainBady006(@Param("request") ReportRequest request);
    BigDecimal selectMainBady007(@Param("request") ReportRequest request);
    BigDecimal selectMainBady008(@Param("request") ReportRequest request);
    BigDecimal selectMainBady009(@Param("request") ReportRequest request);
    BigDecimal selectMainBady010(@Param("request") ReportRequest request);
//    BigDecimal selectMainBady011(@Param("request") ReportRequest request);
//    BigDecimal selectMainBady012(@Param("request") ReportRequest request);
//    BigDecimal selectMainBady013(@Param("request") ReportRequest request);
//    BigDecimal selectMainBady014(@Param("request") ReportRequest request);
//    BigDecimal selectMainBady015(@Param("request") ReportRequest request);

    /**
     * （一）二级运营预警情况
     * 1.基本情况（取数项8个）
     */

//    //本月，对北京公司11项指标进行监控
//    Integer selectSecondaryBasic001(@Param("request") ReportRequest request);

    //共分发6项（取数项：本月实际有已分发预警信息的监控指标个数统计）指标
    Integer selectSecondaryBasic002(@Param("request") ReportRequest request);
    //963条（取数项：发送日期“不为空”）预警
    Integer selectSecondaryBasic003(@Param("request") ReportRequest request);
    //从分发对象看，物资公司各业务部门460条
    Integer selectSecondaryBasic004(@Param("request") ReportRequest request);
    //各三级运营中心503条
    Integer selectSecondaryBasic005(@Param("request") ReportRequest request);
    //从风险等级看，“提示”阶段504条
    Integer selectSecondaryBasic006(@Param("request") ReportRequest request);
    //“逾期”阶段459条
    Integer selectSecondaryBasic007(@Param("request") ReportRequest request);
    //从办结情况看，完成预警办结911条
    Integer selectSecondaryBasic008(@Param("request") ReportRequest request);
    //其中完成业务闭环办结531条
    Integer selectSecondaryBasic009(@Param("request") ReportRequest request);


    //其中完成业务闭环办结531条
    List<LinkedHashMap> selectSecondaryAnalyse001(@Param("request") ReportRequest request);




}
