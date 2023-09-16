package com.proj.web.mapper;

import com.proj.model.bo.request.ReportRequest;
import com.proj.web.entity.MainBadySecondPO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 二级通报正文数据元
 */
@Mapper
@Component
public interface MainBadySecondMapper {

    /**
     * 获取月报001表
     * @param num
     * @param request
     * @return
     */
    List<MainBadySecondPO> selectyb001ByTable( @Param("num") Integer num, @Param("request") ReportRequest request);

    //盘活利库完成率-暂无使用情况
    BigDecimal selectphlkwcv();
}
