package com.proj.model.vo;

import com.proj.model.bo.search.BaseSearchCriteria;
import lombok.Data;

import java.util.List;

/*
月报-返回给前端VO
 */
@Data
public class SmartReportIndexVO extends BaseSearchCriteria {
    /**
     * 基础数据统计表
     * @author liuyafei
     */
    public List<BasicDataStatisticsVO> basicDataStatisticsList;

    /**
     * 监控指标数据表
     * @author liuyafei
     */
    public List<MonitorIndexDataVO> monitorIndexDataList;

    /**
     * 物资公司预警情况统计表
     * @author liuyafei
     */
    public List<MaterialsVO> materialsList;

    /**
     * 三级运营中心预警情况统计表
     * @author liuyafei
     */
    public List<ThreeLevelVO> threeLevelList;

    /**
     * 正文
     * @author liuyafei
     */
    public List<MainBadyVO> mainBodylist;

    /**
     * 详情
     * @author liuyafei
     */
    public DetailVO detail;
}
