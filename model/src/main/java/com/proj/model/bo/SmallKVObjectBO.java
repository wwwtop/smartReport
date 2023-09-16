package com.proj.model.bo;

import lombok.Data;

/**
 * 简短的kv（Object对象）实体BO
 *
 * @author dong.ning
 */
@Data
public class SmallKVObjectBO {

    /**
     * 键
     */
    private Object key;

    /**
     * 值
     */
    private Object value;

}
