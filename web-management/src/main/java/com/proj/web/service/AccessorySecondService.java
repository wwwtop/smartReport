package com.proj.web.service;

import com.proj.model.bo.request.ReportRequest;

import java.util.Map;

/**
 * 月报附件
 */
public interface AccessorySecondService {
    /**
     * 二级供应链运营指标通报明细表 的key和value 并添加到结果表
     * @param request
     * @return
     */
    Map<String, Object> getSecondCircularMap(ReportRequest request);

}
