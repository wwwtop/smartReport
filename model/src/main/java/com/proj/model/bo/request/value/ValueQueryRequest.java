package com.proj.model.bo.request.value;

import lombok.Data;

/**
 * 值查询请求实体
 *
 * @author dong.ning
 */
@Data
public class ValueQueryRequest {

    /**
     * 业务类型，字符串
     */
    private String btype;

    /**
     * 业务字段key，字符串
     */
    private String bfieldKey;


    /**
     * 时间配置（如果有），字符串
     */
    private String btime;

}
