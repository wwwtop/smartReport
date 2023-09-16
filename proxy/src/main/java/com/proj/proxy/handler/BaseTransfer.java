package com.proj.proxy.handler;

import cn.hutool.json.JSONUtil;
import com.proj.proxy.ProxyConfig;
import com.proj.core.utils.JsonUtil;
import com.proj.model.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * 基础数据通信类
 *
 * @author dong.ning
 */
public abstract class BaseTransfer {

    /**
     * 基础网络协议
     */
    private final String PREFIX_PROTOCOL = "http://";


    /**
     * HTTP-GET方式
     *
     * @param url
     * @param protocol
     * @param inputParam
     * @return
     */
    private String transferNetworkGet(String url, int protocol, Object... inputParam) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class, inputParam);
        return response;
    }

    /**
     * HTTP-POST方式
     *
     * @param url
     * @param protocol
     * @param inputParam
     * @return
     */
    private String transferNetworkPost(Map<String, Object> headerMapConfig
            , String url, int protocol, Object inputParam) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (null != headerMapConfig && headerMapConfig.size() > 0) {
            for (String key : headerMapConfig.keySet()) {
                headers.set(key, headerMapConfig.get(key).toString());
            }
        }

        String json = null;
        if (null != inputParam) {
            json = JsonUtil.toJsonStr(inputParam);
        }

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        String response = restTemplate.postForEntity(url, entity, String.class).getBody();
        return response;
    }

    /**
     * 拼接URL-GET地址参数。参数的数据源map
     *
     * @param url
     * @param params
     * @return
     */
    protected final String getUrlPrepareConcatParams(String url, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder(url);
        if (null != params && params.size() > 0) {

            for (String key : params.keySet()) {
                String value = params.get(key);

                if (null == value) {
                    value = "null";
                }

                this.getUrlPrepareConcatParamsAnd(stringBuilder);

                stringBuilder.append(key).append("=");
                stringBuilder.append(value.toString());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 拼接URL-GET地址参数。<b>结尾是追加?或者&符号</b>
     *
     * @param stringBuilder
     * @return
     */
    private StringBuilder getUrlPrepareConcatParamsAnd(StringBuilder stringBuilder) {
        if (stringBuilder.indexOf("?") == -1) {
            stringBuilder.append("?");
        } else {
            stringBuilder.append("&");
        }
        return stringBuilder;
    }

    /**
     * 网络传输
     *
     * @param headerMapConfig
     * @param url
     * @param protocol
     * @param inputParam
     * @return
     */
    protected final String transferNetwork(Map<String, Object> headerMapConfig
            , String url, int protocol, Object... inputParam) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        String prefix = ProxyConfig.getGlobalConfig().getUrl();
        if (StringUtils.isEmpty(prefix)
                || !prefix.toLowerCase().startsWith(this.PREFIX_PROTOCOL) || prefix.toLowerCase().equals(this.PREFIX_PROTOCOL)) {
            return null;
        }

        if (url.toLowerCase().equals(prefix)) {
            return null;
        }

        if (!url.toLowerCase().startsWith(this.PREFIX_PROTOCOL)) {
            url = prefix.concat(url);
        }

        try {
            switch (protocol) {
                case 1:
                    //get
                    return this.transferNetworkGet(url, protocol, inputParam);
                case 2:
                    //post
                    Object inputParamObjSingle = null;
                    if (null != inputParam && inputParam.length > 0) {
                        inputParamObjSingle = inputParam[0];
                    }
                    return this.transferNetworkPost(headerMapConfig, url, protocol, inputParamObjSingle);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * HTTP执行请求之后，对返回结果，进行JSON-OBJECT转换。可以转换为任意类对象
     *
     * @param tClass
     * @param url
     * @param protocol
     * @param inputParam
     * @param <T>
     * @return
     */
    protected final <T> T transferNetworkToClass(Class<T> tClass, String url, int protocol, Object... inputParam) {
        String response = this.transferNetwork(null, url, protocol, inputParam);
        if (StringUtils.isEmpty(response)) {
            return null;
        }

        return JSONUtil.toBean(response, tClass);
    }

    /**
     * HTTP执行请求之后，对返回结果，进行JSON-OBJECT转换。可以转换为ApiResult
     *
     * @param url
     * @param protocol
     * @param inputParam
     * @return
     */
    protected final ApiResult transferNetworkToApiResult(String url, int protocol, Object... inputParam) {
        ApiResult result = this.transferNetworkToClass(ApiResult.class, url, protocol, inputParam);
        return result;
    }

    /**
     * HTTP执行请求之后，对返回结果，进行JSON-OBJECT转换。可以转换为ApiResult里面，data的单个实体对象
     *
     * @param tClass
     * @param url
     * @param protocol
     * @param inputParam
     * @param <T>
     * @return
     */
    protected final <T> T transferNetworkToApiResultData(Class<T> tClass, String url, int protocol, Object... inputParam) {
        ApiResult result = this.transferNetworkToApiResult(url, protocol, inputParam);
        T t = JSONUtil.toBean(result.getData().toString(), tClass);
        return t;
    }

    /**
     * HTTP执行请求之后，对返回结果，进行JSON-OBJECT转换。可以转换为ApiResult里面，data的实体List对象
     *
     * @param tClass
     * @param url
     * @param protocol
     * @param inputParam
     * @param <T>
     * @return
     */
    protected final <T> List<T> transferNetworkToApiResultDataList(Class<T> tClass, String url, int protocol, Object... inputParam) {
        ApiResult result = this.transferNetworkToApiResult(url, protocol, inputParam);
        List<T> tList = JSONUtil.toList(result.getData().toString(), tClass);
        return tList;
    }

}
