package com.proj.proxy.handler.dictionary;

import com.proj.proxy.handler.BaseTransfer;
import com.proj.model.ApiResult;
import com.proj.model.bo.request.dictionary.DictionaryDeleteRequest;
import com.proj.model.bo.request.dictionary.DictionarySyncNewRequest;
import com.proj.model.bo.request.dictionary.DictionaryUpdateRequest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 数据字典的代理
 *
 * @author dong.ning
 */
public class DictionaryProxy extends BaseTransfer {

    /**
     * 获取list
     *
     * @return
     */
    public ApiResult getList(String btype, String btime) {
        String url = "/api/dictionary/getList";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("btype", btype);
        map.put("btime", btime);
        url = super.getUrlPrepareConcatParams(url, map);
        ApiResult result = super.transferNetworkToApiResult(url, 1);
        return result;
    }

    /**
     * 获取值类型列表
     *
     * @return
     */
    public ApiResult getValueTypeList() {
        String url = "/api/dictionary/getValueTypeList";
        ApiResult result = super.transferNetworkToApiResult(url, 1);
        return result;
    }

    /**
     * 获取值类型列表
     *
     * @return
     */
    public ApiResult getDetail(String btype, String bfieldKey, String btime) {
        String url = "/api/dictionary/getDetail";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("btype", btype);
        map.put("bfieldKey", bfieldKey);
        map.put("btime", btime);
        url = super.getUrlPrepareConcatParams(url, map);
        ApiResult result = super.transferNetworkToApiResult(url, 1);
        return result;
    }

    /*---------------------------*/

    public ApiResult updateConfig(DictionaryUpdateRequest request) {
        String url = "/api/dictionary/updateConfig";
        ApiResult result = super.transferNetworkToApiResult(url, 2, request);
        return result;
    }

    public ApiResult deleteConfig(DictionaryDeleteRequest request) {
        String url = "/api/dictionary/deleteConfig";
        ApiResult result = super.transferNetworkToApiResult(url, 2, request);
        return result;
    }

    public ApiResult syncNewConfig(DictionarySyncNewRequest request) {
//        DictionarySyncNewRequest request = new DictionarySyncNewRequest();
        String url = "/api/dictionary/syncNewConfig";
        ApiResult result = super.transferNetworkToApiResult(url, 2, request);
        return result;
    }

}
