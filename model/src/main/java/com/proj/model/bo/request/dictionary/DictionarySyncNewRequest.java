package com.proj.model.bo.request.dictionary;

import lombok.Data;

/**
 * 数据字典同步新的配置项，请求实体。用在某数据业务，从本月自动同步到下个月（或者是上个月同步到本月）
 *
 * @author dong.ning
 */
@Data
public class DictionarySyncNewRequest {

    /**
     * 业务类型，字符串
     */
    private String btype;

    /**
     * 同步之前的日期
     */
    private String btimeSrc;

    /**
     * 同步之后的新日期
     */
    private String btimeDest;

}
