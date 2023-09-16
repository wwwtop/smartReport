package com.proj.proxy.dbhandler;

import com.proj.proxy.dbhandler.dto.DbBfpsQueryPartialDTO;

/**
 * 生成，用于外部引用的此数据库mapper查询请求实体DTO
 * <p>
 * 需要【自行】在mapper中，新建一个xml或者xml的sql方法（并且支持此DbBfpsQueryPartialDTO类的转换和接入）
 * <p>
 * 可随时嵌入到各个查询的select中（建议select）
 * <p>
 * 可嵌入SQL语句，模板示例（详见请参考proxy模块下，默认提供的xml【dbHandlerMapperJoin.xml】）【提示悬浮框，也可以鼠标拖拽复制粘贴】：
 *
 * <ol>
 *      <li>
 *         JOIN连接查询（详见请参考getQueryDtoWhereFixedProxy）：
 *         <pre>
 *             SELECT *** as main
 *             LEFT JOIN ads_mat_esc_bd_ywsjgdcspzb as dict
 *             ON (main.key = dict.cszbmc)
 *             WHERE dict.ywlx = ''
 *             {@code <if test="(dto.rqsj != null and dto.rqsj != '')">}
 *                AND dict.rqsj = {@code #{dto.rqsj}}
 *             {@code </if>}
 *         </pre>
 *      </li>
 *      <li>
 *         主表SELECT子句（详见请参考getQueryDtoSelectFixedProxy）：
 *         <pre>
 *         (
 *         CASE
 *         WHEN dict.cclx = 1 THEN dict.gdz
 *         ELSE main.value
 *         END
 *         ) as ZBSZ
 *         </pre>
 *      </li>
 * </ol>
 *
 * @author dong.ning
 */
public class DbHandler {

    /**
     * 生成。用于外部引用的此数据库mapper查询请求实体DTO
     *
     * @param btype
     * @param bfieldkey
     * @param btime
     * @param tableNameMain
     * @param tableNameDict
     * @return
     */
    public DbBfpsQueryPartialDTO getQueryDto(String btype, String bfieldkey, String btime
            , String tableNameMain, String tableNameDict) {
        DbBfpsQueryPartialDTO dto = new DbBfpsQueryPartialDTO();
        dto.setBtype(btype);
        dto.setBfieldkey(bfieldkey);
        dto.setBtime(btime);

        dto.setTableNameMain(tableNameMain);
        dto.setTableNameDict(tableNameDict);

        return dto;
    }

    /**
     * 生成。用于外部引用的此数据库mapper查询请求实体DTO
     *
     * @param btype
     * @param bfieldkey
     * @param btime
     * @return
     */
    public DbBfpsQueryPartialDTO getQueryDto(String btype, String bfieldkey, String btime) {
        DbBfpsQueryPartialDTO dto = this.getQueryDto(btype, bfieldkey, btime
                , null, null);
        return dto;
    }

}
