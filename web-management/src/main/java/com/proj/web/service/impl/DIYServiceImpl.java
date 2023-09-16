package com.proj.web.service.impl;

import com.proj.core.utils.exception.ProjException;
import com.proj.model.bo.request.ReportRequest;
import com.proj.web.entity.SmartReportAddPO;
import com.proj.web.entity.SmartReportSecondPO;
import com.proj.web.mapper.DIYMapper;
import com.proj.web.service.DIYService;
import com.proj.web.service.MainBadySecondService;
import com.proj.web.util.QuartUtil;
import com.proj.web.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

/**
 * 自定义设置
 */
@Service
@Transactional(rollbackFor = ProjException.class)
public class DIYServiceImpl implements DIYService {
    @Resource
    DIYMapper diyMapper;

    /**
     * 正文
     */
    @Resource
    MainBadySecondService mainBadySecondService;


    /**
     * 自定义设置
     *
     * @param request
     */
    @Override
    public Boolean upDiy(ReportRequest request) {
        SmartReportSecondPO reportPO = new SmartReportSecondPO();
        String startTime = request.getStartTime();//2022-03-01
        String endTime = request.getEndTime();//2022-03-31
        String time = request.getTime();//2022-03
        String year = request.getYear();//2022
        reportPO.setYear(year);
        reportPO.setTime(time);
        reportPO.setStartTime(startTime);
        reportPO.setEndTime(endTime);
        //本年累计时间-到当月
        reportPO.setAccStartTime(year + "-01-01");
        reportPO.setAccEndTime(endTime);
        //去年累计时间-到去年当月
        reportPO.setAccLastStartTime(TimeUtil.lastTime(year) + "-01-01");
        reportPO.setAccLastEndTime(TimeUtil.lastTime(endTime));
        return getdiy(reportPO, request);
    }

    /**
     * 自定义配置
     * @param reportPO
     * @param request
     */
    private Boolean getdiy(SmartReportSecondPO reportPO, ReportRequest request) {
        Boolean aBoolean = this.updateCorp(reportPO);
        if (aBoolean) {
            this.upSecondMainBady(request);
        }
        return aBoolean;
    }

    /**
     * 更新正文
     *
     * @param reportRequest
     */
    private void upSecondMainBady(ReportRequest reportRequest) {
        try {
            diyMapper.deleteByTime(reportRequest.getTime(), "secondMainBady");//删除所有数据集通过月份
            Map<String, Object> basicDataStatisticsMap = mainBadySecondService.getsecondMainBadyList(reportRequest);//正文
            smartReportAccessoryByAdd(basicDataStatisticsMap, reportRequest, "secondMainBady");
        } catch (ProjException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新 非电网零星物资人工评价率 数据集
     *
     * @param reportPO
     * @return
     */
    private Boolean updateCorp(SmartReportSecondPO reportPO) {
        Boolean flag = true;
        String[] city = this.getCity();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < city.length; i++) {
            String term = city[i];
            System.out.println(term);
            list.add(format(diyMapper.selectCircular015(reportPO, term)) + "%");//非电网零星物资人工评价率
        }
        String[] targetName = this.getTargetName();
        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setTime(reportPO.getTime());
        reportRequest.setZtype("corp");
        for (int i = 0; i < targetName.length; i++) {
            reportRequest.setTargetName(targetName[i]);
            Integer integer = diyMapper.upDiy(reportRequest, list.get(i));
            if (integer != 1) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    /**
     * 添加数据集到结果表
     *
     * @param map
     * @param request
     * @param label
     * @return
     */
    private void smartReportAccessoryByAdd(Map<String, Object> map, ReportRequest request, String label) throws ProjException {
        for (int i = 1; i <= map.size(); i++) {
            SmartReportAddPO datail = new SmartReportAddPO();
            datail.setIndexNumber(label + "_" + String.format("%03d", i));//指标编号
            Object obj = map.get(label + i);
            datail.setValueIndex(String.valueOf(QuartUtil.off(obj)));//指标数值

            datail.setCreationTime(request.getTime());
            datail.setSource(label);
            System.out.println(i + "||:" + datail);
            Integer integer = diyMapper.basicDataStatisticsAdd(datail);
            if (integer != 1) {
                throw new ProjException();
            }
        }
    }


    /**
     * 获取所有城区
     *
     * @return
     */
    private String[] getCity() {
        String[] city = {"BP02", "BP08", "BP03", "BP04", "BP05",
                "BP06", "BP07", "BP09", "BP10", "BP11", "BP12",
                "BP13", "BP14", "BP15", "BP16", "BP17", "BP34",
                "BP32", "BPM2", "BP33", "BP37", "BP30", "BP36"};
        return city;
    }

    /**
     * 获取非电网零星物资人工评价率所有指标编号
     *
     * @return
     */
    private String[] getTargetName() {
        String[] targetName = {"corp_015", "corp_031", "corp_047", "corp_063", "corp_079",
                "corp_095", "corp_111", "corp_127", "corp_143", "corp_159", "corp_175",
                "corp_191", "corp_207", "corp_223", "corp_239", "corp_255", "corp_271",
                "corp_287", "corp_303", "corp_319", "corp_335", "corp_351", "corp_367"};
        return targetName;
    }

    /**
     * 保留两位小数或者直接显示整数
     *
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    private static final DecimalFormat format = new DecimalFormat("#.##");

    public static String format(BigDecimal bigDecimal1) {
        if (bigDecimal1.scale() == 0) {
            return String.valueOf(bigDecimal1.setScale(0));
        } else {
            return format.format(bigDecimal1);
        }
    }


}
