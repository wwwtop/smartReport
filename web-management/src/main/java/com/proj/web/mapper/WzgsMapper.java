package com.proj.web.mapper;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.bo.request.UnicityRequest;
import com.proj.web.entity.SmartReportAddPO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 物资公司Mapper
 */
@Mapper
@Component
public interface WzgsMapper {

    /**
     * 表头
     * @return
     */
    List<String> getWzgsHander(@Param("request")ReportRequest request);

    /**
     * 列
     * @return
     */
    List<String> getWzgsColumn(@Param("request")ReportRequest request);


    /**
     * 内容
     * @return
     */
    List<LinkedHashMap> getWzgsBady(@Param("str") String str,@Param("str2") String str2,@Param("str3") String str3,@Param("request")ReportRequest request);

    /**
     * 尾部总计内容
     * @return
     */
    List<LinkedHashMap> getWzgsBady1(@Param("str") String str,@Param("request")ReportRequest request);



}
