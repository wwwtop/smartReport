package com.proj.web.controller;

import com.proj.model.ApiResult;
import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.second.SmartReportSecondIndexVO;
import com.proj.web.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 二级运营通报Controller
 */
@RestController
@RequestMapping("/gylyyReport/second")
@Api(tags = "新版二级月报")
public class SmartReportSecondController {

    /**
     * 二级月报汇总
     */
    @Resource
    private ReportSecondService reportSecondService;


    /**
     * 手动添加数据集
     *
     * @return
     */
    @PostMapping("/getSecondCircularMap")
    @ResponseBody
    @ApiOperation(value = "手动添加数据集")
    public ApiResult getSecondCircularMap(@RequestBody ReportRequest request) {
        Boolean aBoolean = reportSecondService.smartReportAdd(request);
        if (aBoolean){
            return new ApiResult().ok();
        }
        return new ApiResult().err("添加数据集失败");
    }

    /**
     * 查询表格数据
     *
     * @return
     */
    @PostMapping("/getSecondCircular")
    @ResponseBody
    @ApiOperation(value = "查询表格数据")
    public ApiResult getSecondCircular(@RequestBody ReportRequest request) {
        SmartReportSecondIndexVO vo = reportSecondService.smartReportQueryByCircular(request);
        return new ApiResult().ok(vo);
    }

    /**
     * 查询数据
     *
     * @return
     */
    @PostMapping("/getSecondMainBady")
    @ResponseBody
    @ApiOperation(value = "查询正文")
    public ApiResult getSecondMainBady(@RequestBody ReportRequest request) {
        SmartReportSecondIndexVO vo = reportSecondService.smartReportQueryByMain(request);
        return new ApiResult().ok(vo);
    }
}
