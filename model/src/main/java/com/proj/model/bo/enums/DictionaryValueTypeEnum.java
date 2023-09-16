package com.proj.model.bo.enums;

import lombok.Getter;

/**
 * 数据字典值类型枚举
 *
 * @author dong.ning
 */
@Getter
public enum DictionaryValueTypeEnum {

    /**
     * 原始接口值
     */
    RAW(1, "原始值"),

    /**
     * 固定值
     */
    FIXED(2, "固定值"),

    ;

    /**
     * 编码、序号
     */
    private int code;

    /**
     * 备注
     */
    private String memo;


    private DictionaryValueTypeEnum(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

}
