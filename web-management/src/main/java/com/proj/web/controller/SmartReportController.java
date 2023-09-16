package com.proj.web.controller;

import com.proj.model.ApiResult;
import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.*;
import com.proj.web.entity.SmartReportSecondPO;
import com.proj.web.service.MainBadyService;
import com.proj.web.service.ReportService;
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
@RequestMapping("/gylyyReport")
@Api(tags = "新版月报")
public class SmartReportController {

    /**
     * 月报汇总
     */
    @Resource
    private ReportService reportService;

    @Resource
    MainBadyService mainBadyService;


    /**
     * 手动添加数据集
     *
     * @return
     */
    @PostMapping("/getBasicDataStatisticsMap")
    @ResponseBody
    @ApiOperation(value = "手动添加数据集")
    public ApiResult getBasicDataStatisticsMap(@RequestBody ReportRequest request) {
        if (reportService.smartReportAdd(request)){
            return new ApiResult().ok();
        }
        return new ApiResult().err("添加数据集失败");
    }


    /**
     * 基础数据统计表
     *
     * @return
     */
    @PostMapping("/smartReportQueryByBasicData")
    @ResponseBody
    @ApiOperation(value = "获取基础数据统计表数据")
    public ApiResult smartReportQueryByBasicData(@RequestBody ReportRequest request) {
        SmartReportIndexVO vo = reportService.smartReportQueryByBasicData(request);
        return new ApiResult().ok(vo);
    }


    /**
     * 物资公司预警情况统计表
     *
     * @return
     */
    @PostMapping("/smartReportQueryBymaterials")
    @ResponseBody
    @ApiOperation(value = "物资公司预警情况统计表")
    public ApiResult smartReportQueryBymaterials(@RequestBody ReportRequest request) {
        HashMap<String, Object> vo = reportService.smartReportQueryBymaterials(request);
        return new ApiResult().ok(vo);
    }

    /**
     * 三级运营中心预警情况统计表
     *
     * @return
     */
    @PostMapping("/smartReportQueryByThreeLevel")
    @ResponseBody
    @ApiOperation(value = "三级运营中心预警情况统计表")
    public ApiResult smartReportQueryByThreeLevel(@RequestBody ReportRequest request) {
        HashMap<String, Object> vo = reportService.smartReportQueryByThreeLevel(request);
        return new ApiResult().ok(vo);
    }

    /**
     * 获取监控统计表
     *
     * @return
     */
    @PostMapping("/smartReportQueryByMonitorData")
    @ResponseBody
    @ApiOperation(value = "获取监控统计表")
    public ApiResult smartReportQueryByMonitorData(@RequestBody ReportRequest request) {
        SmartReportIndexVO vo = reportService.smartReportQueryByMonitorData(request);
        return new ApiResult().ok(vo);
    }

    /**
     * 获取正文
     *
     * @return
     */
    @PostMapping("/smartReportQueryByMain")
    @ResponseBody
    @ApiOperation(value = "获取正文")
    public ApiResult smartReportQueryByMain(@RequestBody ReportRequest request) {
        MainBadyVO maps = reportService.smartReportQueryByMain(request);
        return new ApiResult().ok(maps);
    }

    /**
     * 三级运营中心预警情况统计表
     *
     * @return
     */
    @PostMapping("/smartReportQueryBySecondaryWarn")
    @ResponseBody
    @ApiOperation(value = "二级预警正文")
    public ApiResult smartReportQueryBySecondaryWarn(@RequestBody ReportRequest request) {
        SecondaryWarnVO secondaryWarnVO = reportService.smartReportQueryBySecondaryWarn(request);
        return new ApiResult().ok(secondaryWarnVO);
    }

    /**
     * 实时查询铝铜折线图
     *
     * @return
     */
    @PostMapping("/getUndulationList")
    @ResponseBody
    @ApiOperation(value = "铝铜折线图")
    public ApiResult getUndulationList(@RequestBody ReportRequest request) throws ParseException {
        UndulationIndexDataVO vo = mainBadyService.getUndulation(request);
        return new ApiResult().ok(vo);
    }

    /**
     * 实时查询铝铜折线图正文
     *
     * @return
     */
    @PostMapping("/getUndulationMain")
    @ResponseBody
    @ApiOperation(value = "铝铜折线图正文")
    public ApiResult getUndulationMain(@RequestBody ReportRequest request) throws ParseException {
        UndulationIndexDataVO vo = mainBadyService.getUndulationMain(request);
        return new ApiResult().ok(vo);
    }


    /**
     * 测试word合并导出
     *
     * @return
     */
    @GetMapping("/exportWord")
    @ResponseBody
    @ApiOperation(value = "测试")
    public void exportWord(@RequestBody ReportRequest request,HttpServletResponse response) throws ParseException {
        try {
            reportService.getWzgsByDc(response,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getDetail")
    @ResponseBody
    @ApiOperation(value = "获取详情")
    public ApiResult getDetail(@RequestBody SmartReportSecondPO request) {
        SmartReportIndexVO detail = reportService.getDetail(request);
        return new ApiResult().ok(detail);
    }


}
