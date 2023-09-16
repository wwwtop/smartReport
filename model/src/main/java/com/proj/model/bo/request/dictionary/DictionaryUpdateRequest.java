package com.proj.model.bo.request.dictionary;

import lombok.Data;

/**
 * 数据字典更新时候的请求实体
 *
 * @author dong.ning
 */
@Data
public class DictionaryUpdateRequest extends BaseDictionary {

    /**
     * 新增=0。修改=要修改的id
     */
    private int id;

    /**
     * 值配置类型，1=原始数据值，2=固定值，int
     */
    private int valueType;

    /**
     * 如果是固定值的值，字符串
     */
    private String value;

}
