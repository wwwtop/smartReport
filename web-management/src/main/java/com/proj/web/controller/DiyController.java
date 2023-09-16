package com.proj.web.controller;

import com.proj.model.ApiResult;
import com.proj.model.bo.request.ReportRequest;
import com.proj.web.service.DIYService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/gylyyReport/diy")
@Api(tags = "自定义")
public class DiyController {

    @Resource
    DIYService diyService;

    /**
     * 自定义修改数据
     *
     * @return
     */
    @PostMapping("/upDiy")
    @ResponseBody
    @ApiOperation(value = "自定义")
    public ApiResult upDiy(@RequestBody ReportRequest request) {
        Boolean aBoolean = diyService.upDiy(request);
        if (aBoolean){
            return new ApiResult().ok();
        }else {
            return new ApiResult().err();
        }
    }
}
