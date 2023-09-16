package com.proj.model.bo.request.dictionary;

import lombok.Data;

/**
 * 数据字典删除时候的请求实体
 *
 * @author dong.ning
 */
@Data
public class DictionaryDeleteRequest extends BaseDictionary {

    /**
     * 要删除的数据id
     */
    private int id;

}
