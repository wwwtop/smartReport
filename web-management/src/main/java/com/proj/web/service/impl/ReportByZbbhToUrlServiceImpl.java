package com.proj.web.service.impl;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.UrlVo;
import com.proj.web.service.ReportByZbbhToUrlService;
import org.springframework.stereotype.Service;

@Service
public class ReportByZbbhToUrlServiceImpl implements ReportByZbbhToUrlService {

    /**
     * 通过前端传递的指标编号或者中文来返给前端URL地址
     * @param request
     * @return
     */
    @Override
    public UrlVo getUrl(ReportRequest request) {
        return null;
    }
}
