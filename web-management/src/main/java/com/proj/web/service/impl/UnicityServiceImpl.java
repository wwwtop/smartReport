package com.proj.web.service.impl;

import com.proj.model.ApiResult;
import com.proj.model.bo.request.ReportRequest;
import com.proj.model.bo.request.dictionary.DictionaryDeleteRequest;
import com.proj.model.bo.request.dictionary.DictionarySyncNewRequest;
import com.proj.model.bo.request.dictionary.DictionaryUpdateRequest;
import com.proj.model.bo.request.value.ValueQueryRequest;
import com.proj.proxy.handler.dictionary.DictionaryProxy;
import com.proj.web.mapper.ReportMapper;
import com.proj.web.service.UnicityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 固定值配置
 */
@Service
public class UnicityServiceImpl implements UnicityService {

    /**
     * 月报注入
     */
    @Resource
    ReportMapper reportMapper;


    /**
     * 查询单一字段
     *
     * @param request
     * @return
     */
    @Override
    public ApiResult getByOnly(ReportRequest request) {
        String s = reportMapper.selectByOnly(request);
        return new ApiResult().ok(s);
    }

    /**
     * 查询配置数据项
     *
     * @param request
     * @return
     */
    @Override
    public ApiResult getUnicityField(ValueQueryRequest request) {
        DictionaryProxy proxy = new DictionaryProxy();
        ApiResult detail = proxy.getDetail(request.getBtype(), request.getBfieldKey(), request.getBtime());
        return detail;
    }

    /**
     * 修改数据项
     *
     * @param request
     * @return
     */
    @Override
    public ApiResult getUnicityFieldDel(DictionaryDeleteRequest request) {
        DictionaryProxy proxy = new DictionaryProxy();
        ApiResult apiResult = proxy.deleteConfig(request);
        return apiResult;
    }

    /**
     * 修改数据项
     *
     * @param request
     * @return
     */
    @Override
    public ApiResult getUnicityFieldUp(DictionaryUpdateRequest request) {
        DictionaryProxy proxy = new DictionaryProxy();
        ApiResult apiResult = proxy.updateConfig(request);
        return apiResult;
    }

    /**
     * 修改数据项
     *
     * @param request
     * @return
     */
    @Override
    public ApiResult getUnicityFieldSync(DictionarySyncNewRequest request) {
        DictionaryProxy proxy = new DictionaryProxy();
        ApiResult apiResult = proxy.syncNewConfig(request);
        return apiResult;
    }
}
