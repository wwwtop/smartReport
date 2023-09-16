package com.proj.web.controller;

import com.proj.core.utils.exception.ProjException;
import com.proj.model.ApiResult;
import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.MainBadyVO;
import com.proj.model.vo.SecondaryWarnVO;
import com.proj.model.vo.SmartReportIndexVO;
import com.proj.model.vo.UndulationIndexDataVO;
import com.proj.web.entity.SmartReportSecondPO;
import com.proj.web.service.MainBadyService;
import com.proj.web.service.ReportService;
import com.proj.web.service.impl.ExportServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

/**
 * 月报Controller
 */
@RestController
@RequestMapping("/gylyyReport/export")
@Api(tags = "新版月报导出")
public class SmartReportExportController {

    /**
     * 月报导出
     */
    @Resource
    private ExportServiceImpl exportService;


    /**
     * 手动添加数据集
     *
     * @return
     */
    @PostMapping("/getExport")
    @ResponseBody
    @ApiOperation(value = "测试导出")
    public void getBasicDataStatisticsMap(@RequestBody SmartReportSecondPO request, HttpServletResponse response) {
        try {
            exportService.export1(request,response);
        } catch (ProjException e) {
            e.printStackTrace();
        }
    }




}
