package com.proj.web.service;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.DetailVO;
import com.proj.model.vo.MainBadyVO;
import com.proj.model.vo.SecondaryWarnVO;
import com.proj.model.vo.SmartReportIndexVO;
import com.proj.web.entity.SmartReportSecondPO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 月报汇总
 * 这个service用来进行查询/添加等操作 作用于结果表
 */
public interface ReportService {

    /**
     * 查看详情
     * 2023.05.24
     * @return
     */
    SmartReportIndexVO getDetail(SmartReportSecondPO smartReportSecond) ;
    /**
     * 添加数据集
     *
     * @return
     */
    Boolean smartReportAdd(ReportRequest request);

    /**
     * 查询数据集-针对正文
     *
     * @return
     */
    MainBadyVO smartReportQueryByMain(ReportRequest request);

    /**
     * 查询数据集-针对预警正文
     *
     * @return
     */
    SecondaryWarnVO smartReportQueryBySecondaryWarn(ReportRequest request);

    /**
     * 查询数据集-针对基础数据统计表
     *
     * @return
     */
    SmartReportIndexVO smartReportQueryByBasicData(ReportRequest request);

    /**
     * 查询数据集-针对监控统计表
     *
     * @return
     */
    SmartReportIndexVO smartReportQueryByMonitorData(ReportRequest request);

    /**
     * 查询数据集-物资公司预警情况统计表
     *
     * @return
     */
    HashMap<String, Object> smartReportQueryBymaterials(ReportRequest request);

    /**
     * 查询数据集-三级运营中心预警情况统计表
     *
     * @return
     */
    HashMap<String, Object> smartReportQueryByThreeLevel(ReportRequest request);


    /**
     * 导出物资公司
     * @param response
     * @throws IOException
     */
    void getWzgsByDc(HttpServletResponse response,ReportRequest request) throws IOException;

    /**
     * 导出各单位
     * @param response
     * @throws IOException
     */
    void getGdwByDc(HttpServletResponse response) throws IOException;

}
