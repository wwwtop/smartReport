package com.proj.web.service.impl;

import com.proj.web.service.AccessorySecondService;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.second.SecondCircularVO;
import com.proj.web.entity.SmartReportSecondPO;
import com.proj.web.mapper.SecondCircularMapper;
import com.proj.web.util.AccessoryUtils;
import com.proj.web.util.BigDecimalUtil;
import com.proj.web.util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.proj.web.util.ReflectionExample.*;

/**
 * 二级月报
 */
@Service
public class AccessorySecondServiceImpl implements AccessorySecondService {

    private final static String corp = "corp";//二级供应链运营指标通报明细表标识

    /**
     * 二级供应链运营指标通报明细
     */
    @Resource
    SecondCircularMapper secondCircularMapper;

    /**
     * 二级供应链运营指标通报明细表 的key和value 并添加到结果表
     *
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> getSecondCircularMap(ReportRequest request) {
        SmartReportSecondPO reportPO = new SmartReportSecondPO();
        String startTime = request.getStartTime();//2022-03-01
        String endTime = request.getEndTime();//2022-03-31
        String time = request.getTime();//2022-03
        String year = request.getYear();//2022
        //当月时间
        LocalDateTime localDateTime = LocalDateTime.of(Integer.valueOf(year), 1, 1, 0, 0, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String yearFirstDayStr = localDateTime.format(formatter);

        localDateTime = localDateTime.now().minusMonths(1);
        String yearLastDayStr = localDateTime.format(formatter);

        reportPO.setStartTime(startTime);
        reportPO.setEndTime(endTime);
        //本年累计时间-到当月
        reportPO.setAccStartTime(year + "-01-01");
        reportPO.setAccEndTime(endTime);
        //去年累计时间-到去年当月
        reportPO.setAccLastStartTime(TimeUtil.lastTime(year) + "-01-01");
        reportPO.setAccLastEndTime(TimeUtil.lastTime(endTime));


        SmartReportSecondPO reportPO2 = reportPO;
        //2022
        String nian = TimeUtil.lastTime(request.getYear());
        reportPO2.setYear(nian);
        reportPO2.setLastYear(TimeUtil.lastTime(nian));
        reportPO2.setAccLastStartTime(TimeUtil.lastTime(startTime));
        String termPO = "";


        HashMap<String, SecondCircularVO> getCorp1 = getCorp1(reportPO, termPO);//城区
        HashMap<String, SecondCircularVO> getCorp2 = getCorp2(reportPO, termPO);//通州
        HashMap<String, SecondCircularVO> getCorp3 = getCorp3(reportPO, termPO);//朝阳
        HashMap<String, SecondCircularVO> getCorp4 = getCorp4(reportPO, termPO);//海淀
        HashMap<String, SecondCircularVO> getCorp5 = getCorp5(reportPO, termPO);//丰台
        HashMap<String, SecondCircularVO> getCorp6 = getCorp6(reportPO, termPO);//石景山
        HashMap<String, SecondCircularVO> getCorp7 = getCorp7(reportPO, termPO);//亦庄
        HashMap<String, SecondCircularVO> getCorp8 = getCorp8(reportPO, termPO);//昌平
        HashMap<String, SecondCircularVO> getCorp9 = getCorp9(reportPO, termPO);//门头沟
        HashMap<String, SecondCircularVO> getCorp10 = getCorp10(reportPO, termPO);//房山
        HashMap<String, SecondCircularVO> getCorp11 = getCorp11(reportPO, termPO);//大兴
        HashMap<String, SecondCircularVO> getCorp12 = getCorp12(reportPO, termPO);//平谷--
        HashMap<String, SecondCircularVO> getCorp13 = getCorp13(reportPO, termPO);//怀柔
        HashMap<String, SecondCircularVO> getCorp14 = getCorp14(reportPO, termPO);//密云
        HashMap<String, SecondCircularVO> getCorp15 = getCorp15(reportPO, termPO);//顺义
        HashMap<String, SecondCircularVO> getCorp16 = getCorp16(reportPO, termPO);//延庆
        HashMap<String, SecondCircularVO> getCorp17 = getCorp17(reportPO, termPO);//经研院
        HashMap<String, SecondCircularVO> getCorp18 = getCorp18(reportPO, termPO);//电科院
        HashMap<String, SecondCircularVO> getCorp19 = getCorp19(reportPO, termPO);//工程公司
        HashMap<String, SecondCircularVO> getCorp20 = getCorp20(reportPO, termPO);//检修公司
        HashMap<String, SecondCircularVO> getCorp21 = getCorp21(reportPO, termPO);//电缆公司
        HashMap<String, SecondCircularVO> getCorp22 = getCorp22(reportPO, termPO);//信通公司
        HashMap<String, SecondCircularVO> getCorp23 = getCorp23(reportPO, termPO);//建咨公司

        Map<String, SecondCircularVO> allMap = new HashMap<>();//创建一个新的map用来将上述数据进行合并
        allMap.putAll(getCorp1);//招标采购
        allMap.putAll(getCorp2);//合同签订
        allMap.putAll(getCorp3);//合同履约
        allMap.putAll(getCorp4);//合同结算
        allMap.putAll(getCorp5);//仓储管理
        allMap.putAll(getCorp6);//平衡利库
        allMap.putAll(getCorp7);//废旧物资
        allMap.putAll(getCorp8);//质量监督
        allMap.putAll(getCorp9);//供应商管理
        allMap.putAll(getCorp10);//招标采购
        allMap.putAll(getCorp11);//合同签订
        allMap.putAll(getCorp12);//合同履约
        allMap.putAll(getCorp13);//合同结算
        allMap.putAll(getCorp14);//仓储管理
        allMap.putAll(getCorp15);//平衡利库
        allMap.putAll(getCorp16);//废旧物资
        allMap.putAll(getCorp17);//质量监督
        allMap.putAll(getCorp18);//供应商管理
        allMap.putAll(getCorp19);//合同结算
        allMap.putAll(getCorp20);//仓储管理
        allMap.putAll(getCorp21);//平衡利库
        allMap.putAll(getCorp22);//废旧物资
        allMap.putAll(getCorp23);//质量监督

        //创建map用来进行添加到数据库结果表
        Map<String, Object> newMap = new HashMap<>();
        int x = 1;
        //遍历数据信息依次添加到新数据表
        for (int i = 1; i <= allMap.size(); i++) {
            SecondCircularVO storage = allMap.get(corp + i);
            List<String> valuesList = printValues(storage);
            System.out.println(valuesList);//控制台输出
            for (int y = 0; y < valuesList.size(); y++) {
                newMap.put(corp + x++, valuesList.get(y));
            }
        }
        return newMap;
    }

    /**
     * 城区公司:BP02
     * 0：暂无 1:有
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp1(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP02";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"城区");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
            circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//一次采购成功率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }

//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//到货交接单签署正确率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//库存积压监控
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");
        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp1", circular);
        return map;
    }

    /**
     * 通州公司
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp2(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP08";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"通州");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }


        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");
        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }

        map.put("corp2", circular);
        return map;
    }

    /**
     * 朝阳
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp3(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP03";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"朝阳");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率

        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");
        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp3", circular);
        return map;
    }

    /**
     * 海淀
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp4(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP04";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"海淀");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");
        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }

        map.put("corp4", circular);
        return map;
    }

    /**
     * 丰台
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp5(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP05";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"丰台");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");
        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }

        map.put("corp5", circular);
        return map;
    }

    /**
     * 石景山
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp6(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP06";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"石景山");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");
        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp6", circular);
        return map;
    }

    /**
     * 亦庄
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp7(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP07";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"亦庄");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp7", circular);
        return map;
    }

    /**
     * 昌平
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp8(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP09";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"昌平");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }

        map.put("corp8", circular);
        return map;
    }

    /**
     * 门头沟
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp9(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP10";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"门头沟");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp9", circular);
        return map;
    }

    /**
     * 房山
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp10(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP11";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"房山");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp10", circular);
        return map;
    }

    /**
     * 大兴
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp11(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP12";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"大兴");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }

        map.put("corp11", circular);
        return map;
    }

    /**
     * 平谷
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp12(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP13";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"平谷");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }
        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");
        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp12", circular);
        return map;
    }

    /**
     * 怀柔
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp13(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP14";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"怀柔");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp13", circular);
        return map;
    }

    /**
     * 密云
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp14(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP15";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"密云");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率

        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp14", circular);
        return map;
    }

    /**
     * 顺义
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp15(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP16";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"顺义");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp15", circular);
        return map;
    }

    /**
     * 延庆
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp16(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP17";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"延庆");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp16", circular);
        return map;
    }

    /**
     * 经研院
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp17(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP34";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
//        BigDecimal circular001 = secondCircularMapper.selectCircular001(reportPO,termPO);//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }


        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast("-");
        linkedList.addLast("-");
        linkedList.addLast(circular003);
        linkedList.addLast("-");
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast("-");
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp17", circular);
        return map;
    }

    /**
     * 电科院
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp18(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP32";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
//        BigDecimal circular001 = secondCircularMapper.selectCircular001(reportPO,termPO);//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast("-");
        linkedList.addLast("-");
        linkedList.addLast(circular003);
        linkedList.addLast("-");
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast("-");
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp18", circular);
        return map;
    }


    /**
     * 工程公司
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp19(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BPM2";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
//        BigDecimal circular001 = secondCircularMapper.selectCircular001(reportPO,termPO);//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }

        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast("-");
        linkedList.addLast("-");
        linkedList.addLast("-");
        linkedList.addLast("-");
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast("-");
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp19", circular);
        return map;
    }

    /**
     * 检修公司
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp20(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP33";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"检修");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }
        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp20", circular);
        return map;
    }

    /**
     * 电缆公司
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp21(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP37";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"电缆");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }
        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast(circular014);
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp21", circular);
        return map;
    }

    /**
     * 信通公司
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp22(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP30";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"信通");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }
        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast(circular004);
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast("-");
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp22", circular);
        return map;
    }

    /**
     * 建咨公司
     *
     * @param reportPO
     * @return
     */
    public HashMap<String, SecondCircularVO> getCorp23(SmartReportSecondPO reportPO, String termPO) {
        termPO = "BP36";
        HashMap<String, SecondCircularVO> map = new HashMap<>();
        LinkedList<String> linkedList = new LinkedList<>();
        SecondCircularVO circular = new SecondCircularVO();
        String circular001 = secondCircularMapper.selectCircular001(reportPO,"建咨");//ESC预警问题整改率
        BigDecimal circular002fm = secondCircularMapper.selectCircular002fm(reportPO, termPO);//盘活利库任务完成率
        String circular002 = "-";
        if (circular002fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular002fz = secondCircularMapper.selectCircular002fz(reportPO, termPO);//盘活利库任务完成率
            BigDecimal selectCircular002fzbf = secondCircularMapper.selectCircular002fzbf(reportPO, termPO);//盘活利库任务完成率
            BigDecimal div = BigDecimalUtil.getDiv(circular002fz, circular002fm);
            
            circular002 = BigDecimalUtil.getSub(div, selectCircular002fzbf) + "%";
        }
        BigDecimal circular003fm = secondCircularMapper.selectCircular003fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00301fm = secondCircularMapper.selectCircular00301fm(reportPO, termPO);//计划申报正确率
        BigDecimal circular00302fm = secondCircularMapper.selectCircular00302fm(reportPO, termPO);//计划申报正确率
        String circular003 = "-";
        if (circular003fm.compareTo(BigDecimal.ZERO) != 0 || circular00301fm.compareTo(BigDecimal.ZERO) != 0 || circular00302fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular003fz = secondCircularMapper.selectCircular003fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00301fz = secondCircularMapper.selectCircular00301fz(reportPO, termPO);//计划申报正确率
            BigDecimal circular00302fz = secondCircularMapper.selectCircular00302fz(reportPO, termPO);//计划申报正确率
            BigDecimal div = BigDecimalUtil.getDiv(circular003fz, circular003fm);
            BigDecimal multiply = BigDecimalUtil.getMul(div, BigDecimal.valueOf(0.3));
            BigDecimal div1 = BigDecimalUtil.getDiv(circular00301fz, circular00301fm);
            BigDecimal multiply1 = BigDecimalUtil.getMul(BigDecimalUtil.getSub(BigDecimal.valueOf(1),div1), BigDecimal.valueOf(0.5));
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00302fz, circular00302fm);
            BigDecimal multiply2 = BigDecimalUtil.getMul(div2, BigDecimal.valueOf(0.2));
            BigDecimal bigDecimal = BigDecimalUtil.getAdd(multiply, multiply1).setScale(2, BigDecimal.ROUND_HALF_UP);
            circular003 = BigDecimalUtil.getAdd1(multiply2, bigDecimal)+ "%";
        }
        BigDecimal circular004fm = secondCircularMapper.selectCircular004fm(reportPO, termPO);//报废物资处置计划申报规范率
        BigDecimal circular00402fm = secondCircularMapper.selectCircular00402fm(reportPO, termPO);
        String circular004 = "-";
        if (circular004fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular004fz = secondCircularMapper.selectCircular004fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal circular00402fz = secondCircularMapper.selectCircular00402fz(reportPO, termPO);//报废物资处置计划申报规范率
            BigDecimal div1 = BigDecimalUtil.getDiv(circular004fz, circular004fm);
            BigDecimal div2 = BigDecimalUtil.getDiv(circular00402fz, circular00402fm);
            BigDecimal addDiv = BigDecimalUtil.getAdd(div1, div2);
             circular004 =BigDecimalUtil.getMul(addDiv, BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(100))+"%";
        }
        BigDecimal circular005fm = secondCircularMapper.selectCircular005fm(reportPO, termPO);//一次采购成功率
        String circular005 = "-";
        if (circular005fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005fz = secondCircularMapper.selectCircular005fz(reportPO, termPO);//盘活利库任务完成率
            circular005 = BigDecimalUtil.getDivs(circular005fz, circular005fm);
        }
        BigDecimal circular005_1fm = secondCircularMapper.selectCircular005_1fm(reportPO, termPO);//一次采购成功率
        String circular005_1 = "-";
        if (circular005_1fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular005_1fz = secondCircularMapper.selectCircular005_1fz(reportPO, termPO);//一次采购成功率
            circular005_1 = BigDecimalUtil.getDivs(circular005_1fz, circular005_1fm);
        }

        BigDecimal circular006fm = secondCircularMapper.selectCircular006fm(reportPO, termPO);//服务类框架协议执行率
        String circular006 = "-";
        if (circular006fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular006fz = secondCircularMapper.selectCircular006fz(reportPO, termPO);//盘活利库任务完成率
            circular006 = BigDecimalUtil.getDivs(circular006fz, circular006fm);
        }
//        BigDecimal circular007 = secondCircularMapper.selectCircular007(reportPO,termPO);//技术规范书退回率-物资
//        BigDecimal circular008 = secondCircularMapper.selectCircular008(reportPO,termPO);//技术规范书退回率-工程及服务
        BigDecimal circular009 = secondCircularMapper.selectCircular009(reportPO, termPO);//零星物资电商化请购总金额（万元）-1
        BigDecimal circular010 = secondCircularMapper.selectCircular010(reportPO, termPO);//逾期货款清理完成指数（万元）
        BigDecimal circular011fm = secondCircularMapper.selectCircular011fm(reportPO, termPO);//供应计划完成率-1
        String circular011 = "-";
        if (circular011fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular011fz = secondCircularMapper.selectCircular011fz(reportPO, termPO);//盘活利库任务完成率
            circular011 = BigDecimalUtil.getDivs(circular011fz, circular011fm);
        }

//        BigDecimal circular012 = secondCircularMapper.selectCircular012(reportPO,termPO);//物资供应计划调整率
        BigDecimal circular013fm = secondCircularMapper.selectCircular013fm(reportPO, termPO);//到货交接单签署正确率
        String circular013 = "-";
        if (circular013fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular013fz = secondCircularMapper.selectCircular013fz(reportPO, termPO);//盘活利库任务完成率
            circular013 = BigDecimalUtil.getDivs(circular013fz, circular013fm);
        }

        BigDecimal circular014fm = secondCircularMapper.selectCircular014fm(reportPO, termPO);//库存积压监控
        String circular014 = "-";
        if (circular014fm.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal circular014fz = secondCircularMapper.selectCircular014fz(reportPO, termPO);//盘活利库任务完成率
            circular014 = BigDecimalUtil.getDivs(circular014fz, circular014fm);
        }


        BigDecimal circular015 = secondCircularMapper.selectCircular015(reportPO, termPO);//非电网零星物资人工评价率
//        BigDecimal circular016 = secondCircularMapper.selectCircular016(reportPO,termPO);//出厂验收参与率


        //有数据时可以取消//
        linkedList.addLast(circular001.equals("0.00%")?"0%":circular001);
        linkedList.addLast(circular002);
        linkedList.addLast(circular003);
        linkedList.addLast("-");
        linkedList.addLast(circular005);
        linkedList.addLast(circular005_1);
        linkedList.addLast(circular006);
        linkedList.addLast("*");
        linkedList.addLast("*");
//        linkedList.addLast(AccessoryUtils.getYoy(circular006));
//        linkedList.addLast(AccessoryUtils.getYoy(circular007));
//        linkedList.addLast(AccessoryUtils.getYoy(circular008));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular009, 10000));
        linkedList.addLast(BigDecimalUtil.getExchangeRate(circular010, 10000));
        linkedList.addLast(circular011);
        linkedList.addLast("*");
        linkedList.addLast(circular013);
        linkedList.addLast("-");
        linkedList.addLast(circular015+"%");
        linkedList.addLast("-");

        List<String> fieldList = printFields(circular);
        for (int i = 0; i < fieldList.size(); i++) {
            setFields(circular, fieldList.get(i), linkedList.get(i));
        }
        map.put("corp23", circular);
        return map;
    }
}
