package com.proj.proxy.handler.value;

import com.proj.proxy.handler.BaseTransfer;
import com.proj.model.ApiResult;
import com.proj.model.bo.request.value.ValueQueryRequest;

/**
 * 值查询代理
 *
 * @author dong.ning
 */
public class ValueProxy extends BaseTransfer {

    /**
     * 获取值
     *
     * @return
     */
    public ApiResult getValueConfig() {
        String url = "/api/value/getValueConfig";

        ValueQueryRequest valueQueryRequest = new ValueQueryRequest();
        valueQueryRequest.setBtype("yb");
        valueQueryRequest.setBfieldKey("m001");
        valueQueryRequest.setBtime("");

        ApiResult result = super.transferNetworkToApiResult(url, 2, valueQueryRequest);
        return result;
    }

}
