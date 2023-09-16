package com.proj.web.service;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.UrlVo;

/**
 * 通过前端传递的指标编号进行url跳转
 */
public interface ReportByZbbhToUrlService {
    UrlVo getUrl(ReportRequest request);
}
