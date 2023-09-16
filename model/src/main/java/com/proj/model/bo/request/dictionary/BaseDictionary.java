package com.proj.model.bo.request.dictionary;

import lombok.Data;

/**
 * 数据字典操作的通用实体字段封装
 *
 * @author dong.ning
 */
@Data
public class BaseDictionary {

    /**
     * 业务类型，字符串
     */
    private String btype;

    /**
     * 业务字段key，字符串
     */
    private String bfieldkey;

    /**
     * 时间配置（如果有），字符串
     */
    private String btime;

}
