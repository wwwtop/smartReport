package com.proj.web.service;

import com.proj.model.bo.request.ReportRequest;
import java.util.Map;

/**
 * 月报附件
 */
public interface AccessoryService {

    /**
     * 添加数据集
     *
     * @return
     */
    Boolean smartReportAdd(ReportRequest request);


    /**
     * 获取基础数据统计表 的key和value 并添加到结果表
     * @param request
     * @return
     */
    Map<String, Object> getBasicDataStatisticsMap(ReportRequest request);

    /**
     * 获取监控指标数据统计表 的key和value 并添加到结果表
     * @param request
     * @return
     */
    Map<String, Object> getMonitorIndexDataMap(ReportRequest request);

    /**
     * 物资公司预警情况统计表 的key和value 并添加到结果表
     * @param request
     * @return
     */
    Map<String, Object> getMaterialsMap(ReportRequest request);

    /**
     * 获取三级运营中心预警情况统计表 的key和value 并添加到结果表
     * @param request
     * @return
     */
    Map<String, Object> getThreeLevelMap(ReportRequest request);
}
