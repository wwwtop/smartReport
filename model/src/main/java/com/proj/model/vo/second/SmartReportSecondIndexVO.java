package com.proj.model.vo.second;

import com.proj.model.bo.search.BaseSearchCriteria;
import com.proj.model.vo.BasicDataStatisticsVO;
import com.proj.model.vo.MainBadyVO;
import com.proj.model.vo.MonitorIndexDataVO;
import com.proj.model.vo.UndulationVO;
import lombok.Data;

import java.util.List;

/**
 * 二级通报-返回给前端VO
 */
@Data
public class SmartReportSecondIndexVO extends BaseSearchCriteria {

    /**
     * 正文
     * @author liuyafei
     */
    public SecondMainBadyVO secondMainBadyList;

    /**
     * 二级供应链运营指标通报明细表
     * @author liuyafei
     */
    public List<SecondCircularVO> secondCircularList;
}
