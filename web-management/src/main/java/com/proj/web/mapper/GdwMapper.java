package com.proj.web.mapper;

import com.proj.model.bo.request.ReportRequest;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 各单位Mapper
 */
@Mapper
@Component
public interface GdwMapper {
    /**
     * 表头
     * @return
     */
    List<String> getGdwHander(@Param("request") ReportRequest request);

    /**
     * 列
     * @return
     */
    List<String> getGdwColumn(@Param("request")ReportRequest request);


    /**
     * 内容
     * @return
     */
    String getGdwBady(@Param("str") String str, @Param("str2") String str2,@Param("request")ReportRequest request);

    /**
     * 内容
     * @return
     */
    String getGdwBady2(@Param("str") String str, @Param("str2") String str2,@Param("request")ReportRequest request);

    /**
     * 内容
     * @return
     */
    String getGdwBady3(@Param("str") String str, @Param("str2") String str2,@Param("request")ReportRequest request);
}
