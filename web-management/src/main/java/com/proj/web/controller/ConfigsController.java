package com.proj.web.controller;

import com.proj.model.ApiResult;
import com.proj.model.bo.request.ReportRequest;
import com.proj.model.bo.request.UnicityRequest;
import com.proj.model.bo.request.dictionary.DictionaryDeleteRequest;
import com.proj.model.bo.request.dictionary.DictionarySyncNewRequest;
import com.proj.model.bo.request.dictionary.DictionaryUpdateRequest;
import com.proj.model.bo.request.value.ValueQueryRequest;
import com.proj.web.service.UnicityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/gylyyReport/config")
@Api(tags = "配置表里面的数据")
public class ConfigsController {




    @Resource
    UnicityService unicityService;



    /**
     * 查询数值
     *
     * @return
     */
    @PostMapping("/getByOnly")
    @ResponseBody
    @ApiOperation(value = "查询数值")
    public ApiResult getByOnly(@RequestBody ReportRequest request) {
        return unicityService.getByOnly(request);
    }

    /**
     * 删除单一数据项配置
     *
     * @return
     */
    @PostMapping("/getUnicityFieldDel")
    @ResponseBody
    @ApiOperation(value = "删除单一数据项配置")
    public ApiResult getUnicityFieldDel(@RequestBody DictionaryDeleteRequest request) {
        return unicityService.getUnicityFieldDel(request);
    }


    /**
     * 修改单一数据项
     *
     * @return
     */
    @PostMapping("/getUnicityFieldUp")
    @ResponseBody
    @ApiOperation(value = "修改单一数据项")
    public ApiResult getUnicityFieldUp(@RequestBody DictionaryUpdateRequest request) {
        return unicityService.getUnicityFieldUp(request);
    }


    /**
     * 查看单一数据项
     *
     * @return
     */
    @PostMapping("/getUnicityField")
    @ResponseBody
    @ApiOperation(value = "查看单一数据项")
    public ApiResult getUnicityField(@RequestBody ValueQueryRequest request) {
        return unicityService.getUnicityField(request);
    }


    /**
     * 查看单一数据项
     *
     * @return
     */
    @PostMapping("/getUnicityFieldSync")
    @ResponseBody
    @ApiOperation(value = "查看单一数据项")
    public ApiResult getUnicityFieldSync(@RequestBody DictionarySyncNewRequest request) {
        return unicityService.getUnicityFieldSync(request);
    }
}
