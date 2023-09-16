package com.proj.web.service;

import com.proj.core.utils.exception.ProjException;
import com.proj.web.entity.SmartReportSecondPO;

import javax.servlet.http.HttpServletResponse;

public interface ExportService {
    /**
     * 导出
     * @param response
     * @return
     * @throws ProjException
     */
    void export1(SmartReportSecondPO request, HttpServletResponse response) throws ProjException;
}
