package com.proj.web.mapper;

import com.proj.model.bo.request.ReportRequest;
import com.proj.web.entity.SmartReportAddPO;
import com.proj.web.entity.SmartReportSecondPO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 自定义
 */
@Mapper
@Component
public interface DIYMapper {


    Integer upDiy(@Param("request") ReportRequest paramReportRequest, @Param("diy") String paramString);

    BigDecimal selectCircular015(@Param("request") SmartReportSecondPO paramSmartReportSecondPO, @Param("term") String paramString);

    Integer deleteByTime(@Param("zsj") String paramString1, @Param("label") String paramString2);

    Integer basicDataStatisticsAdd(@Param("request") SmartReportAddPO paramSmartReportAddPO);


}
