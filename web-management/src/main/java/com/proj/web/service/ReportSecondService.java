package com.proj.web.service;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.second.SmartReportSecondIndexVO;

import java.util.Map;

/**
 * 二级月报汇总
 * 这个service用来进行查询/添加等操作 作用于结果表
 */
public interface ReportSecondService {
    /**
     * 添加数据集
     * @return
     */
    Boolean smartReportAdd(ReportRequest request);

    /**
     * 查询数据集-针对正文
     * @return
     */
    SmartReportSecondIndexVO smartReportQueryByMain(ReportRequest request);


    /**
     * 查询数据集-针对二级供应链运营指标通报明细表
     * getSecondCircular
     * @return
     */
    SmartReportSecondIndexVO smartReportQueryByCircular(ReportRequest request);

}
