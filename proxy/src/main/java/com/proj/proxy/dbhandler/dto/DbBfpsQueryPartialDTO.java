package com.proj.proxy.dbhandler.dto;

import lombok.Data;

/**
 * 用于外部引用的此数据库mapper查询请求实体DTO
 *
 * @author dong.ning
 */
@Data
public class DbBfpsQueryPartialDTO {

    /**
     * 业务类型
     */
    private String btype;

    /**
     * 字段key
     */
    private String bfieldkey;

    /**
     * 时间（如果有）
     */
    private String btime;

    /**
     * 查询的主表，别名（如果有）
     */
    private String tableNameMain;

    /**
     * 查询的数据字典表，别名（如果有）
     */
    private String tableNameDict;

}
