package com.proj.web.mapper;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.DetailVO;
import com.proj.web.entity.SmartReportAddPO;
import com.proj.web.entity.SmartReportSecondPO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 月报注入情况
 */
@Mapper
@Component
public interface ReportMapper {

    /**
     * 单独对正文进行添加数据到结果表xin_001
     * @param request
     * @return
     */
    Integer basicDataStatisticsAdd(@Param("request") SmartReportAddPO request);

    /**
     * 查询所有结果表数据xin_001
     * @param request
     * @return
     */
    List<LinkedHashMap> selectAll(@Param("request") ReportRequest request);

    /**
     * 查询所有结果表数据xin_001
     * @param request
     * @return
     */
    String selectByOnly(@Param("request") ReportRequest request);

    Integer deleteByTime(@Param("zsj") String zsj,@Param("label") String label);


    /**
     * 通过指标编号获取详情数据
     * 2023.05.24
     * @param request
     * @return
     */
    DetailVO selectDatsils(@Param("request")SmartReportSecondPO request);




}
