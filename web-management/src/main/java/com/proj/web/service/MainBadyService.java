package com.proj.web.service;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.UndulationIndexDataVO;

import java.text.ParseException;
import java.util.Map;

/**
 * 月报正文
 *
 * @author dong.ning
 */
public interface MainBadyService {


    /*
    正文
     */
    Map<String, Object> getMainBadyList(ReportRequest request);

    /*
    折线图
     */
    UndulationIndexDataVO getUndulation(ReportRequest request) throws ParseException;

    /*
   折线图正文
    */
    UndulationIndexDataVO getUndulationMain(ReportRequest request) throws ParseException;


    /*
    月报正文二级运营预警
    */
    Map<String, Object> getSecondaryWarning(ReportRequest request);


}
