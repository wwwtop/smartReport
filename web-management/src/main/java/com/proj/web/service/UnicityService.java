package com.proj.web.service;

import com.proj.model.ApiResult;
import com.proj.model.bo.request.ReportRequest;
import com.proj.model.bo.request.dictionary.DictionaryDeleteRequest;
import com.proj.model.bo.request.dictionary.DictionarySyncNewRequest;
import com.proj.model.bo.request.dictionary.DictionaryUpdateRequest;
import com.proj.model.bo.request.value.ValueQueryRequest;

/**
 * 固定值配置
 */
public interface UnicityService {


    /**
     * 查询配置数据项
     * @param request
     * @return
     */
    ApiResult getByOnly(ReportRequest request);

    /**
     * 查询配置数据项
     * @param request
     * @return
     */
    ApiResult getUnicityField(ValueQueryRequest request);

    /**
     * 修改数据项
     * @param request
     * @return
     */
    ApiResult getUnicityFieldDel(DictionaryDeleteRequest request);

    /**
     * 修改数据项
     * @param request
     * @return
     */
    ApiResult getUnicityFieldUp(DictionaryUpdateRequest request);

    /**
     * 修改数据项
     * @param request
     * @return
     */
    ApiResult getUnicityFieldSync(DictionarySyncNewRequest request);
}
