package com.proj.web.service.impl;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.BasicDataStatisticsVO;
import com.proj.model.vo.MonitorIndexDataVO;
import com.proj.web.entity.SmartReportAddPO;
import com.proj.web.entity.SmartReportPO;
import com.proj.web.mapper.*;
import com.proj.web.service.AccessoryService;
import com.proj.web.util.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

import static com.proj.web.util.AccessoryUtils.*;
import static com.proj.web.util.BigDecimalUtil.getSub;
import static com.proj.web.util.QuartUtil.contrastByTable;
import static com.proj.web.util.ReflectionExample.*;

/**
 * 附件
 */
@Service
public class AccessoryServiceImpl implements AccessoryService {

    private final static String BASIC = "basic";//基础数据统计表标识
    private final static String MONITOR = "monitor";//监控指标数据统计表标识
    private final static String MATERIALS = "materials";//物资公司预警情况统计表标识
    private final static String THREELEVEL = "threeLevel";//三级运营中心预警情况统计表标识
    /**
     * 基础数据统计表
     */
    @Resource
    private BasicDataStatisticsMapper basicDataStatisticsMapper;
    /**
     * 获取监控指标数据统计表
     */
    @Resource
    private MonitorIndexDataMapper monitorIndexDataMapper;
    /**
     * 获取物资公司
     */
    @Resource
    private WzgsMapper wzgsMapper;
    /**
     * 获取各单位
     */
    @Resource
    private GdwMapper gdwMapper;

    @Resource
    private ReportMapper reportMapper;

    /**
     * 添加数据集
     *
     * @param request
     * @return
     */
    @Override
    public Boolean smartReportAdd(ReportRequest request) {
        Map<String, Object> basicDataStatisticsMap = this.getBasicDataStatisticsMap(request);//基础数据统计表数据
        smartReportAccessoryByAdd(basicDataStatisticsMap, request, BASIC);
        Map<String, Object> monitorIndexDataMap = this.getMonitorIndexDataMap(request);//监控指标数据统计表数据
        smartReportAccessoryByAdd(monitorIndexDataMap, request, MONITOR);
        Map<String, Object> materialsMap = this.getMaterialsMap(request);//物资公司预警情况统计表数据
        smartReportAccessoryByAdd(materialsMap, request, MATERIALS);
        Map<String, Object> threeLevelMap = this.getThreeLevelMap(request);//各单位情况统计表数据
        smartReportAccessoryByAdd(threeLevelMap, request, THREELEVEL);
        return true;
    }

    /**
     * 添加数据集到结果表
     *
     * @param map
     * @param request
     * @param label
     * @return
     */
    private void smartReportAccessoryByAdd(Map<String, Object> map, ReportRequest request, String label) {
        for (int i = 1; i <= map.size(); i++) {
            SmartReportAddPO datail = new SmartReportAddPO();
            datail.setIndexNumber(label + "_" + String.format("%03d", i));//指标编号
            Object obj = map.get(label + i);
            datail.setValueIndex(String.valueOf(QuartUtil.off(obj)));//指标数值

            datail.setCreationTime(request.getTime());
            datail.setSource(label);
            System.out.println(i + "||:" + datail);
            reportMapper.basicDataStatisticsAdd(datail);
        }
    }


    /**
     * 获取基础数据统计表
     *
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> getBasicDataStatisticsMap(ReportRequest request) {
        SmartReportPO reportPO = new SmartReportPO();
        String startTime = request.getStartTime();//2022-03-01
        String endTime = request.getEndTime();//2022-03-31
        String time = request.getTime();//2022-03
        String year = request.getYear();//2022
        //
        reportPO.setYear(year);
        reportPO.setLastYear(TimeUtil.lastTime(year));
        //当月时间
        reportPO.setStartTime(startTime);
        reportPO.setEndTime(endTime);
        //本年累计时间-到当月
        reportPO.setAccStartTime(year + "-01-01");
        reportPO.setAccEndTime(endTime);
        //去年累计时间-到去年当月
        reportPO.setAccLastStartTime(TimeUtil.lastTime(year) + "-01-01");
        reportPO.setAccLastEndTime(TimeUtil.lastTime(endTime));


        SmartReportPO reportPO2 = new SmartReportPO();
        //2022
        String nian = TimeUtil.lastTime(request.getYear());
        reportPO2.setStartTime(startTime);
        reportPO2.setEndTime(endTime);
        reportPO2.setAccStartTime(year + "-01-01");
        reportPO2.setAccEndTime(endTime);
        reportPO2.setYear(nian);
        reportPO2.setLastYear(TimeUtil.lastTime(nian));
        reportPO2.setAccLastStartTime(TimeUtil.lastTime(startTime));
        reportPO2.setAccLastEndTime(TimeUtil.lastTime(endTime));


        HashMap<String, BasicDataStatisticsVO> purchaseInBasic = getPurchaseInBasic(reportPO);//采购计划-有
        HashMap<String, BasicDataStatisticsVO> tenderInBasic = getTenderInBasic(reportPO);//招标采购-缺少
        HashMap<String, BasicDataStatisticsVO> signedInBasic = getSignedInBasic(reportPO);//合同签订-有
        HashMap<String, BasicDataStatisticsVO> exerciseInBasic = getExerciseInBasic(reportPO);//合同履约-缺少
        HashMap<String, BasicDataStatisticsVO> clearingInBasic = getClearingInBasic(reportPO);//合同结算-缺少
        HashMap<String, BasicDataStatisticsVO> storageInBasic = getStorageInBasic(reportPO2, reportPO);//仓储管理-缺少
        HashMap<String, BasicDataStatisticsVO> balanceInBasic = getBalanceInBasic(reportPO);//平衡利库-有
        HashMap<String, BasicDataStatisticsVO> wasteInBasic = getWasteInBasic(reportPO);//废旧物资-有
        HashMap<String, BasicDataStatisticsVO> supervisionInBasic = getSupervisionInBasic(reportPO);//质量监督-缺少
        HashMap<String, BasicDataStatisticsVO> supplierInBasic = getSupplierInBasic(reportPO);//供应商管理-暂无
        Map<String, BasicDataStatisticsVO> allMap = new HashMap<>();
        allMap.putAll(purchaseInBasic);//采购计划
        allMap.putAll(tenderInBasic);//招标采购
        allMap.putAll(signedInBasic);//合同签订
        allMap.putAll(exerciseInBasic);//合同履约
        allMap.putAll(clearingInBasic);//合同结算
        allMap.putAll(storageInBasic);//仓储管理
        allMap.putAll(balanceInBasic);//平衡利库
        allMap.putAll(wasteInBasic);//废旧物资
        allMap.putAll(supervisionInBasic);//质量监督
        allMap.putAll(supplierInBasic);//供应商管理
        Map<String, Object> map = new HashMap<>();//创建新map对所用数据进行遍历并添加特定标识
        int x = 1;
        for (int i = 1; i <= allMap.size(); i++) {
            BasicDataStatisticsVO storage = allMap.get(BASIC + i);
            List<String> valuesList = printValues(storage);
            System.out.println(valuesList);
            for (int y = 0; y < valuesList.size(); y++) {
                map.put(BASIC + x++, valuesList.get(y));
            }
        }
        return map;
    }

    /**
     * 供应商管理
     *
     * @param
     * @return
     */
    private HashMap<String, BasicDataStatisticsVO> getSupplierInBasic(SmartReportPO request) {
        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
        List<LinkedHashMap> maps = basicDataStatisticsMapper.selectSupplier001(request);
        BasicDataStatisticsVO vo1 = getEntityOnlyAmount(maps);
        map.put("basic32", vo1);
        return map;
    }

    /**
     * 质量监督
     *
     * @param request
     * @return
     */
    private HashMap<String, BasicDataStatisticsVO> getSupervisionInBasic(SmartReportPO request) {
        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
        List<LinkedHashMap> maps1 = basicDataStatisticsMapper.selectSupervision001(request);
        BasicDataStatisticsVO vo = getEntityOnlyAmount(maps1);
        map.put("basic30", vo);
        List<LinkedHashMap> maps2 = basicDataStatisticsMapper.selectSupervision002(request);
        BasicDataStatisticsVO vo2 = getEntityOnlyAmount(maps2);
        map.put("basic31", vo2);
        return map;
    }

    /**
     * 废旧物资
     *
     * @param request
     * @return
     */
    private HashMap<String, BasicDataStatisticsVO> getWasteInBasic(SmartReportPO request) {
        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
        List<LinkedHashMap> maps = basicDataStatisticsMapper.selectWaste001(request);//废旧物资——报废处置
        BasicDataStatisticsVO vo1 = getEntityToAll(maps);
        map.put("basic29", vo1);
        return map;
    }


    /**
     * 平衡利库
     *
     * @param request
     * @return
     */
    private HashMap<String, BasicDataStatisticsVO> getBalanceInBasic(SmartReportPO request) {
        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
        List<LinkedHashMap> maps = basicDataStatisticsMapper.selectBalance001(request);
        BasicDataStatisticsVO vos = getEntityToAll(maps);
        map.put("basic28", vos);
        return map;
    }

    /**
     * 合同结算
     *
     * @param request
     * @return
     */
    private HashMap<String, BasicDataStatisticsVO> getClearingInBasic(SmartReportPO request) {
        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
        List<LinkedHashMap> maps1 = basicDataStatisticsMapper.selectClearing001(request);//合同结算————资金支付
        BasicDataStatisticsVO vo1 = getEntityToAll(maps1);
        map.put("basic17", vo1);
        List<LinkedHashMap> maps2 = basicDataStatisticsMapper.selectClearing002(request);//合同结算————资金支付
        BasicDataStatisticsVO vo2 = getEntityToAll(maps2);//合同结算————非电商采购支付逾期
//        vo2.setFieldset1("-");
//        vo2.setFieldset2("-");
//        vo2.setFieldset3("-");
//        vo2.setFieldset4("-");
//        vo2.setFieldset5("-");
//        vo2.setFieldset6("-");
//        vo2.setFieldset7("-");
        map.put("basic18", vo2);
        return map;
    }

    /**
     * 合同签订
     *
     * @param request
     * @return
     */
    private HashMap<String, BasicDataStatisticsVO> getSignedInBasic(SmartReportPO request) {
        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
        List<LinkedHashMap> maps1 = basicDataStatisticsMapper.selectSigned001(request);
        BasicDataStatisticsVO vo = getEntityToAll(maps1);
        map.put("basic12", vo);
        List<LinkedHashMap> maps2 = basicDataStatisticsMapper.selectSigned002(request);
        BasicDataStatisticsVO vo2 = getEntityToAll(maps2);
        map.put("basic13", vo2);
        List<LinkedHashMap> maps3 = basicDataStatisticsMapper.selectSigned003(request);
        BasicDataStatisticsVO vo3 = getEntityToAll(maps3);
        map.put("basic14", vo3);
        return map;
    }

    /**
     * 合同履约
     *
     * @param request
     * @return
     */
    private HashMap<String, BasicDataStatisticsVO> getExerciseInBasic(SmartReportPO request) {
        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
        List<LinkedHashMap> maps = basicDataStatisticsMapper.selectExercise001(request);
        BasicDataStatisticsVO vo1 = getEntityToAll(maps);
        map.put("basic15", vo1);
        List<LinkedHashMap> maps2 = basicDataStatisticsMapper.selectExercise002(request);
        BasicDataStatisticsVO vo2 = getEntityToAll(maps2);
        map.put("basic16", vo2);
        return map;
    }


    /**
     * 仓储管理
     *
     * @param request
     * @return
     */
    private HashMap<String, BasicDataStatisticsVO> getStorageInBasic(SmartReportPO request, SmartReportPO request2) {
        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
        //因为这个特殊
        List<LinkedHashMap> mapNew = basicDataStatisticsMapper.selectStorage001(request);//仓储管理-北京公司库存情况库存数量
        Integer basic01 = AccessoryUtils.getActualValueByN(mapNew, "1");//数量
        BigDecimal basic02 = basicDataStatisticsMapper.selectStorage001p1(request);//金额(当月)
        List<LinkedHashMap> p2 = basicDataStatisticsMapper.selectStorage001p2(request);//仓储管理-北京公司库存情况金额（今年/去年）
        BigDecimal basic04 = AccessoryUtils.getActualValueByP(p2, "1");//金额
        BigDecimal basic05 = AccessoryUtils.getActualValueByP(p2, "2");//金额
        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
        String basic07 = QuartUtil.udByTable(basic06);//变化情况
        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
        vo.setFieldset1(String.valueOf(basic01));
        vo.setFieldset2(BigDecimalUtil.getExchangeRate(basic02, 10000));
        vo.setFieldset3(String.valueOf("-"));//数量
        vo.setFieldset4(BigDecimalUtil.getExchangeRate(basic04, 10000));
        vo.setFieldset5(BigDecimalUtil.getExchangeRate(basic05, 10000));
        vo.setFieldset6(basic06 + "%");
        vo.setFieldset7(basic07);
        map.put("basic19", vo);
        List<LinkedHashMap> mapNew2 = basicDataStatisticsMapper.selectStorage002(request2);//仓储管理-北京公司库存情况入库
        BasicDataStatisticsVO vo2 = getEntityToAll(mapNew2);
        map.put("basic20", vo2);

        List<LinkedHashMap> mapNew3 = basicDataStatisticsMapper.selectStorage003(request2);//仓储管理-北京公司库存情况出库
        BasicDataStatisticsVO vo3 = getEntityToAll(mapNew3);
        map.put("basic21", vo3);

        List<LinkedHashMap> mapNew4 = basicDataStatisticsMapper.selectStorage004(request2);//仓储管理-代保管物资入库
        BasicDataStatisticsVO vo4 = getEntityOnlyAmount(mapNew4);
        map.put("basic22", vo4);
        List<LinkedHashMap> mapNew5 = basicDataStatisticsMapper.selectStorage005(request2);//仓储管理-代保管物资出库
        BasicDataStatisticsVO vo5 = getEntityOnlyAmount(mapNew5);
        map.put("basic23", vo5);
        //主动配送
        List<LinkedHashMap> mapNew6 = basicDataStatisticsMapper.selectStorage009(request2);
        BasicDataStatisticsVO vo6 = getEntityToAll(mapNew6);
        map.put("basic24", vo6);
        Integer n1 = basicDataStatisticsMapper.selectStorage006n1();//专业仓情况库存数量
        BigDecimal p1 = basicDataStatisticsMapper.selectStorage006p1();//专业仓情况库存金额
        BasicDataStatisticsVO vo7 = new BasicDataStatisticsVO();
        vo7.setFieldset1(String.valueOf(n1));//43
        vo7.setFieldset2(BigDecimalUtil.getExchangeRate(p1, 10000));//44
        vo7.setFieldset3("-");//45
        vo7.setFieldset4("-");//46
        vo7.setFieldset5("-");//47
        vo7.setFieldset6("-");//48
        vo7.setFieldset7("-");//49
        map.put("basic25", vo7);
        List<LinkedHashMap> mapNew8 = basicDataStatisticsMapper.selectStorage007(request2);//仓储管理-专业仓情况入仓
        BasicDataStatisticsVO vo8 = getEntityToAll(mapNew8);
        map.put("basic26", vo8);
        List<LinkedHashMap> mapNew9 = basicDataStatisticsMapper.selectStorage008(request2);//仓储管理-专业仓情况出仓
        BasicDataStatisticsVO vo9 = getEntityToAll(mapNew9);
        map.put("basic27", vo9);
        return map;
    }

    /**
     * 招标采购
     *
     * @param request
     * @return
     */
    private HashMap<String, BasicDataStatisticsVO> getTenderInBasic(SmartReportPO request) {
        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
        List<LinkedHashMap> mapNew = basicDataStatisticsMapper.selectTender001(request);//招标采购-国网公司实施采购
        BasicDataStatisticsVO vo = getEntityToAll(mapNew);
        map.put("basic7", vo);
        List<LinkedHashMap> mapNew2 = basicDataStatisticsMapper.selectTender002(request);//招标采购-物资类
        BasicDataStatisticsVO vo2 = getEntityOnlyPrice(mapNew2);
        map.put("basic8", vo2);
        List<LinkedHashMap> mapNew3 = basicDataStatisticsMapper.selectTender003(request);//招标采购-服务类 没有数量
        BasicDataStatisticsVO vo3 = getEntityOnlyPrice(mapNew3);
        map.put("basic9", vo3);
        //零星物资电商化请购金额
        List<LinkedHashMap> mapNew4 = basicDataStatisticsMapper.selectTender004(request);//招标采购-服务类 没有数量
        BasicDataStatisticsVO vo4 = getEntityToAll(mapNew4);
        map.put("basic10", vo4);
        //服务类框架协议执行金额
        List<LinkedHashMap> mapNew5 = basicDataStatisticsMapper.selectTender005(request);//招标采购-服务类 没有数量
        BasicDataStatisticsVO vo5 = getEntityToAll(mapNew5);
//        BasicDataStatisticsVO vo5 = new BasicDataStatisticsVO();
//        vo5.setFieldset1("-");
//        vo5.setFieldset2("-");
//        vo5.setFieldset3("-");
//        vo5.setFieldset4("-");
//        vo5.setFieldset5("-");
//        vo5.setFieldset6("-");
//        vo5.setFieldset7("-");
        map.put("basic11", vo5);
        System.out.println(map);
        return map;
    }

    /**
     * 采购计划
     *
     * @param request
     * @return
     */
    private HashMap<String, BasicDataStatisticsVO> getPurchaseInBasic(SmartReportPO request) {
        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
        List<LinkedHashMap> mapNew1 = basicDataStatisticsMapper.selectPurchase001(request);//采购计划-物资类批次计划数量
        BasicDataStatisticsVO vo = getEntityToAll(mapNew1);
        map.put("basic1", vo);
        List<LinkedHashMap> mapNew2 = basicDataStatisticsMapper.selectPurchase002(request);//采购计划-物资类协议库存计划
        BasicDataStatisticsVO vo2 = getEntityToAll(mapNew2);
        map.put("basic2", vo2);
        List<LinkedHashMap> mapNew3 = basicDataStatisticsMapper.selectPurchase003(request);//采购计划-物资类框架计划
        BasicDataStatisticsVO vo3 = getEntityToAll(mapNew3);
        map.put("basic3", vo3);
        List<LinkedHashMap> mapNew4 = basicDataStatisticsMapper.selectPurchase004(request);//采购计划-协议库存匹配计划
        BasicDataStatisticsVO vo4 = getEntityToAll(mapNew4);
        map.put("basic4", vo4);
        List<LinkedHashMap> mapNew5 = basicDataStatisticsMapper.selectPurchase005(request);//采购计划-服务类批次计划
        BasicDataStatisticsVO vo5 = getEntityToAll(mapNew5);
        map.put("basic5", vo5);
        List<LinkedHashMap> mapNew6 = basicDataStatisticsMapper.selectPurchase006(request);//采购计划-服务类框架计划
        BasicDataStatisticsVO vo6 = getEntityToAll(mapNew6);
        map.put("basic6", vo6);
        return map;
    }


    /**
     * 通过两个数据集取数
     *
     * @param mapNew1
     * @param mapNew2
     * @return
     */
    public BasicDataStatisticsVO getEntityTwoMap(List<LinkedHashMap> mapNew1, List<LinkedHashMap> mapNew2) {
        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
        Integer basic01 = AccessoryUtils.getActualValueByN(mapNew1, "1");//数量
        BigDecimal basic02 = AccessoryUtils.getActualValueByP(mapNew2, "1");//金额
        Integer basic03 = AccessoryUtils.getActualValueByN(mapNew1, "2");//数量
        BigDecimal basic04 = AccessoryUtils.getActualValueByP(mapNew2, "2");//金额
        BigDecimal basic05 = AccessoryUtils.getActualValueByP(mapNew2, "3");//金额
        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
        String basic07 = QuartUtil.udByTable(basic06);//变化情况
        vo.setFieldset1(String.valueOf(basic01));
        vo.setFieldset2(BigDecimalUtil.getExchangeRate(basic02, 10000));
        vo.setFieldset3(String.valueOf(basic03));
        vo.setFieldset4(BigDecimalUtil.getExchangeRate(basic04, 10000));
        vo.setFieldset5(BigDecimalUtil.getExchangeRate(basic05, 10000));
        vo.setFieldset6(basic06 + "%");
        vo.setFieldset7(basic07);
        return vo;
    }

    /**
     * 正常取数
     *
     * @param mapNew
     * @return
     */
    public BasicDataStatisticsVO getEntityToAll(List<LinkedHashMap> mapNew) {
        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
        Integer basic01 = AccessoryUtils.getActualValueByN(mapNew, "1");//数量
        BigDecimal basic02 = AccessoryUtils.getActualValueByP(mapNew, "1");//金额
        Integer basic03 = AccessoryUtils.getActualValueByN(mapNew, "2");//数量
        BigDecimal basic04 = AccessoryUtils.getActualValueByP(mapNew, "2");//金额
        BigDecimal basic05 = AccessoryUtils.getActualValueByP(mapNew, "3");//金额
        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
        String basic07 = QuartUtil.udByTable(basic06);//变化情况
        vo.setFieldset1(String.valueOf(basic01));
        vo.setFieldset2(BigDecimalUtil.getExchangeRate(basic02, 10000));
        vo.setFieldset3(String.valueOf(basic03));
        vo.setFieldset4(BigDecimalUtil.getExchangeRate(basic04, 10000));
        vo.setFieldset5(BigDecimalUtil.getExchangeRate(basic05, 10000));
        vo.setFieldset6(basic06 + "%");
        vo.setFieldset7(basic07);
        return vo;
    }

    /**
     * 只有数量
     * 1代表本月 2代表本年 3代表去年
     * getActualValueByN取数量
     *
     * @param mapNew 返回数据集
     * @return
     */
    public BasicDataStatisticsVO getEntityOnlyAmount(List<LinkedHashMap> mapNew) {
        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
        Integer basic01 = AccessoryUtils.getActualValueByN(mapNew, "1");//数量
        Integer basic03 = AccessoryUtils.getActualValueByN(mapNew, "2");//数量
        Integer basic05 = AccessoryUtils.getActualValueByN(mapNew, "3");//数量
        BigDecimal basic06 = BigDecimalUtil.getYoy(BigDecimal.valueOf(basic03), BigDecimal.valueOf(basic05));//同比
        String basic07 = QuartUtil.udByTable(basic06);//变化情况
        vo.setFieldset1(String.valueOf(basic01));
        vo.setFieldset2("-");
        vo.setFieldset3(String.valueOf(basic03));
        vo.setFieldset4("-");
        vo.setFieldset5(String.valueOf(basic05));
        vo.setFieldset6(basic06 + "%");
        vo.setFieldset7(basic07);
        return vo;
    }

    /**
     * 只有金额
     * 1代表本月 2代表本年 3代表去年
     * getActualValueByP取金额
     *
     * @param mapNew 返回数据集
     * @return
     */
    public BasicDataStatisticsVO getEntityOnlyPrice(List<LinkedHashMap> mapNew) {
        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
        BigDecimal basic02 = AccessoryUtils.getActualValueByP(mapNew, "1");//数量
        BigDecimal basic04 = AccessoryUtils.getActualValueByP(mapNew, "2");//数量
        BigDecimal basic05 = AccessoryUtils.getActualValueByP(mapNew, "3");//数量
        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
        String basic07 = QuartUtil.udByTable(basic06);//变化情况
        vo.setFieldset1("-");
        vo.setFieldset2(BigDecimalUtil.getExchangeRate(basic02, 10000));
        vo.setFieldset3("-");
        vo.setFieldset4(BigDecimalUtil.getExchangeRate(basic04, 10000));
        vo.setFieldset5(BigDecimalUtil.getExchangeRate(basic05, 10000));
        vo.setFieldset6(basic06 + "%");
        vo.setFieldset7(basic07);
        return vo;
    }


    /**
     * 获取监控指标数据统计表 的key和value 并添加到结果表
     *
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> getMonitorIndexDataMap(ReportRequest request) {
        SmartReportPO reportPO = new SmartReportPO();
        String startTime = request.getStartTime();//2022-03-01
        String endTime = request.getEndTime();//2022-03-31
        String time = request.getTime();//2022-03
        String year = request.getYear();//2022

        reportPO.setTime(time); //2022-03
        reportPO.setLastTime(TimeUtil.lastTime(time));//2021-03
        reportPO.setYear(year);//2022-03
        reportPO.setLastYear(TimeUtil.lastTime(year));//2021

        reportPO.setStartTime(startTime);
        reportPO.setEndTime(endTime);
        //本年累计时间-到当月 2023-01-01————2023-03-31
        reportPO.setAccStartTime(year + "-01-01");
        reportPO.setAccEndTime(endTime);
        //去年当月时间
        reportPO.setLastStartTime(TimeUtil.lastTime(request.getStartTime()));
        //去年累计时间-到去年当月 2022-01-01————2022-03-31
        reportPO.setAccLastStartTime(TimeUtil.lastTime(year) + "-01-01");
        reportPO.setAccLastEndTime(TimeUtil.lastTime(endTime));

        HashMap<String, MonitorIndexDataVO> purchaseInBasic = getPlanInMonitor(reportPO);//计划管理
        HashMap<String, MonitorIndexDataVO> tenderInBasic = getTenderInMonitor(reportPO);//招标采购
        HashMap<String, MonitorIndexDataVO> signedInBasic = getSignedInMonitor(reportPO);//合同签订
        HashMap<String, MonitorIndexDataVO> exerciseInBasic = getExerciseInMonitor(reportPO);//合同履约
        HashMap<String, MonitorIndexDataVO> storageInBasic = getStorageInMonitor(reportPO);//仓储管理
        HashMap<String, MonitorIndexDataVO> balanceInBasic = getBalanceInMonitor(reportPO);//平衡利库-暂无
        HashMap<String, MonitorIndexDataVO> wasteInBasic = getWasteInMonitor(reportPO);//废旧物资
        HashMap<String, MonitorIndexDataVO> supervisionInBasic = getSupervisionInMonitor(reportPO);//质量监督
        Map<String, MonitorIndexDataVO> allMap = new HashMap<>();
        allMap.putAll(purchaseInBasic);//采购计划
        allMap.putAll(tenderInBasic);//招标采购
        allMap.putAll(signedInBasic);//合同签订
        allMap.putAll(exerciseInBasic);//合同履约
        allMap.putAll(storageInBasic);//仓储管理
        allMap.putAll(balanceInBasic);//平衡利库
        allMap.putAll(wasteInBasic);//废旧物资
        allMap.putAll(supervisionInBasic);//质量监督
        Map<String, Object> map = new HashMap<>();
        int x = 1;
        for (int i = 1; i <= allMap.size(); i++) {
            MonitorIndexDataVO storage = allMap.get(MONITOR + i);
            List<String> valuesList = printValues(storage);
            System.out.println(valuesList);
            for (int y = 0; y < valuesList.size(); y++) {
                map.put(MONITOR + x++, valuesList.get(y));
            }
        }
        return map;
    }

    /**
     * 获取物资公司预警情况统计表 的key和value 并添加到结果表
     *
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> getMaterialsMap(ReportRequest request) {
        HashMap<String, Object> map = new LinkedHashMap<>();
        List<String> wzgsHander = wzgsMapper.getWzgsHander(request);
        System.out.println(wzgsHander);
        List<String> wzgsColumn = wzgsMapper.getWzgsColumn(request);
        System.out.println(wzgsColumn);
        map.put("materials1", "分发对象/指标名称");
        int i = 2;
        for (String str : wzgsHander) {
            map.put("materials" + i++, str);
        }
        map.put("materials" + i++, "总计");
        System.out.println(map);
        map.put("materials" + i++, "指标名称");
        for (String str : wzgsHander) {
            map.put("materials" + i++, "预警数量");
            map.put("materials" + i++, "办结比例");
        }
        map.put("materials" + i++, "预警数量");
        map.put("materials" + i++, "办结比例");
        System.out.println(map);

        for (String col : wzgsColumn) {
            map.put("materials" + i++, col);
            for (String hander : wzgsHander) {
                List<LinkedHashMap> wzgsBady = wzgsMapper.getWzgsBady(col, hander, "分发对象", request);
                if (wzgsBady.size() != 0) {
                    for (LinkedHashMap bady : wzgsBady) {
                        Object obj1 = bady.get("预警数量");
                        Object obj2 = bady.get("办结比例");
                        map.put("materials" + i++, obj1);
                        map.put("materials" + i++, obj2);
                    }
                } else {
                    map.put("materials" + i++, "");
                    map.put("materials" + i++, "");
                }
            }
            List<LinkedHashMap> wzgsBady = wzgsMapper.getWzgsBady(col, "", "", request);
            if (wzgsBady.size() != 0) {
                Object obj1 = wzgsBady.get(0).get("预警数量");
                Object obj2 = wzgsBady.get(0).get("办结比例");
                map.put("materials" + i++, obj1);
                map.put("materials" + i++, obj2);
            } else {
                map.put("materials" + i++, "");
                map.put("materials" + i++, "");
            }

        }


        map.put("materials" + i++, "总计");
        for (String str : wzgsHander) {
            List<LinkedHashMap> wzgsBady = wzgsMapper.getWzgsBady1(str, request);
            Object obj1 = wzgsBady.get(0).get("预警数量");
            Object obj2 = wzgsBady.get(0).get("办结比例");
            map.put("materials" + i++, obj1);
            map.put("materials" + i++, obj2);
        }

        //计算总计
        List<LinkedHashMap> wzgsBady = wzgsMapper.getWzgsBady1("", request);
        Object obj1 = wzgsBady.get(0).get("预警数量");
        Object obj2 = wzgsBady.get(0).get("办结比例");
        map.put("materials" + i++, obj1);
        map.put("materials" + i++, obj2);

        tst(map);
        return map;
    }

    private void tst(Map<String, Object> map) {
        for (String key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }
    }

    /**
     * 获取三级运营中心预警情况统计表 的key和value 并添加到结果表
     *
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> getThreeLevelMap(ReportRequest request) {
        Map<String, Object> map = new HashMap<>();
        List<String> gdwHander = gdwMapper.getGdwHander(request);
        System.out.println(gdwHander);
        List<String> gdwColumn = gdwMapper.getGdwColumn(request);
        System.out.println(gdwColumn);

        int i = 1;
        map.put("threeLevel" + i++, "运营中心指标名称");
        for (String str : gdwHander) {
            map.put("threeLevel" + i++, str);
        }
        map.put("threeLevel" + i++, "总计");
        int y = map.size() - 2;
        //遍历列头
        for (String str : gdwColumn) {
            map.put("threeLevel" + i++, str);
            map.put("threeLevel" + i++, "预警数量");
            for (String hander : gdwHander) {
                String gdwBady = gdwMapper.getGdwBady(str, hander, request);
                map.put("threeLevel" + i++, gdwBady);
            }
            String allBady = gdwMapper.getGdwBady(str, "", request);
            map.put("threeLevel" + i++, allBady);

//            map.put("threeLevel" + i++, "已办结");
//            for (String hander : gdwHander) {
//                String gdwBady = gdwMapper.getGdwBady2(str, hander);
//                map.put("threeLevel" + i++, gdwBady);
//            }
//            String allYbj = gdwMapper.getGdwBady2(str, "");
//            map.put("threeLevel" + i++, allYbj);

            map.put("threeLevel" + i++, "办结比例");
            for (String hander : gdwHander) {
                String gdwBady = gdwMapper.getGdwBady3(str, hander, request);
                map.put("threeLevel" + i++, gdwBady);
            }
            String allBjl = gdwMapper.getGdwBady3(str, "", request);
            map.put("threeLevel" + i++, allBjl);
        }

        map.put("threeLevel" + i++, "总计");
        map.put("threeLevel" + i++, "预警数量");
        for (String hander : gdwHander) {
            String gdwBady = gdwMapper.getGdwBady("", hander, request);
            map.put("threeLevel" + i++, gdwBady);
        }
        String allBady = gdwMapper.getGdwBady("", "", request);
        map.put("threeLevel" + i++, allBady);

        map.put("threeLevel" + i++, "办结比例");
        for (String hander : gdwHander) {
            String gdwBady = gdwMapper.getGdwBady3("", hander, request);
            map.put("threeLevel" + i++, gdwBady);
        }
        String allBjl = gdwMapper.getGdwBady3("", "", request);
        map.put("threeLevel" + i++, allBjl);


        return map;
    }

    /**
     * 正常取数只限于招标采购
     *
     * @param mapNew
     * @return
     */
    public MonitorIndexDataVO getEntityByMonitor1(List<LinkedHashMap> mapNew) {
        MonitorIndexDataVO vo = new MonitorIndexDataVO();
        String monitor1 = getActualValueByRate1(mapNew, "1");//数量
        String monitor2 = getActualValueByRate1(mapNew, "2");//金额
        String monitor3 = contrastByTable(monitor1, monitor2);//变化情况
        vo.setFieldset1(monitor1.equals("-") ? "-" : monitor1 + "%");
        vo.setFieldset2(monitor2.equals("-") ? "-" : monitor2 + "%");
        vo.setFieldset3(monitor3);
        return vo;
    }

    /**
     * 正常取数
     *
     * @param mapNew
     * @return
     */
    public MonitorIndexDataVO getEntityByMonitor(List<LinkedHashMap> mapNew) {
        MonitorIndexDataVO vo = new MonitorIndexDataVO();
        String monitor1 = getActualValueByRate(mapNew, "1");//数量
        String monitor2 = getActualValueByRate(mapNew, "2");//金额
        String monitor3 = contrastByTable(monitor1, monitor2);//变化情况
        vo.setFieldset1(monitor1.equals("-") ? "-" : monitor1 + "%");
        vo.setFieldset2(monitor2.equals("-") ? "-" : monitor2 + "%");
        vo.setFieldset3(monitor3);
        return vo;
    }

    /**
     * 正常取数
     *
     * @param mapNew
     * @return
     */
    public MonitorIndexDataVO getEntityByMonitor3(List<LinkedHashMap> mapNew) {
        MonitorIndexDataVO vo = new MonitorIndexDataVO();
        String monitor1 = getActualValueByRatePrice(mapNew, "1");//数量
        String monitor2 = getActualValueByRatePrice(mapNew, "2");//金额
        String monitor3 = contrastByTable(monitor1, monitor2);//变化情况
        vo.setFieldset1(monitor1);
        vo.setFieldset2(monitor2);
        vo.setFieldset3(monitor3);
        return vo;
    }

    /**
     * 正常取数
     *
     * @param mapNew
     * @return
     */
    public MonitorIndexDataVO getEntityByMonitor2(List<LinkedHashMap> mapNew) {
        MonitorIndexDataVO vo = new MonitorIndexDataVO();
        String monitor1 = AccessoryUtils.getActualValueByRate2(mapNew, "1");//数量
        String monitor2 = AccessoryUtils.getActualValueByRate2(mapNew, "2");//金额
        String monitor3 = contrastByTable(monitor1, monitor2);//变化情况
        vo.setFieldset1(monitor1.equals("-") ? "-" : monitor1 + "天");
        vo.setFieldset2(monitor2.equals("-") ? "-" : monitor2 + "天");
        vo.setFieldset3(monitor3);
        return vo;
    }

    /**
     * 计划管理
     *
     * @param request
     * @return
     */
    private HashMap<String, MonitorIndexDataVO> getPlanInMonitor(SmartReportPO request) {
        HashMap<String, MonitorIndexDataVO> map = new HashMap<>();
        List<LinkedHashMap> maps1 = monitorIndexDataMapper.selectPlan001(request);
        MonitorIndexDataVO vo1 = getEntityByMonitor(maps1);
        map.put("monitor1", vo1);

        List<LinkedHashMap> maps2 = monitorIndexDataMapper.selectPlan002(request);
        MonitorIndexDataVO vo2 = getEntityByMonitor(maps2);
        map.put("monitor2", vo2);

        List<LinkedHashMap> maps3 = monitorIndexDataMapper.selectPlan003(request);
        MonitorIndexDataVO vo = getEntityByMonitor(maps3);
        map.put("monitor3", vo);
        return map;
    }

    /**
     * 招标采购
     *
     * @param request
     * @return
     */
    private HashMap<String, MonitorIndexDataVO> getTenderInMonitor(SmartReportPO request) {
        SmartReportPO reportPO = new SmartReportPO();
        reportPO.setAccStartTime(request.getStartTime());//获取当前月
        reportPO.setAccEndTime(request.getEndTime());//获取当前月
        reportPO.setAccLastStartTime(TimeUtil.lastTime(request.getStartTime()));//获取去年本月
        reportPO.setAccLastEndTime(TimeUtil.lastTime(request.getEndTime()));//获取去年本月
        HashMap<String, MonitorIndexDataVO> map = new HashMap<>();
        List<LinkedHashMap> maps1 = monitorIndexDataMapper.selectTender001(reportPO);
        MonitorIndexDataVO vo1 = getEntityByMonitor1(maps1);
        map.put("monitor4", vo1);

        List<LinkedHashMap> maps2 = monitorIndexDataMapper.selectTender002(reportPO);
        MonitorIndexDataVO vo2 = getEntityByMonitor1(maps2);
        map.put("monitor5", vo2);

//        List<LinkedHashMap> maps3 = monitorIndexDataMapper.selectTender003(request);
//        MonitorIndexDataVO vo3 = getEntityByMonitor(maps3);
        MonitorIndexDataVO vo = new MonitorIndexDataVO();
        vo.setFieldset1("100%");
        vo.setFieldset2("100%");
        vo.setFieldset3("-");
        map.put("monitor6", vo);

        List<LinkedHashMap> maps4 = monitorIndexDataMapper.selectTender004(reportPO);
        MonitorIndexDataVO vo4 = getEntityByMonitor1(maps4);
        map.put("monitor7", vo4);

        List<LinkedHashMap> maps5 = monitorIndexDataMapper.selectTender005(reportPO);
        MonitorIndexDataVO vo5 = getEntityByMonitor1(maps5);
        map.put("monitor8", vo5);

        List<LinkedHashMap> maps6 = monitorIndexDataMapper.selectTender006(reportPO);
        MonitorIndexDataVO vo6 = getEntityByMonitor1(maps6);
        map.put("monitor9", vo6);

        List<LinkedHashMap> maps7 = monitorIndexDataMapper.selectTender007(reportPO);
        MonitorIndexDataVO vo7 = getEntityByMonitor1(maps7);
        map.put("monitor10", vo7);

//        List<LinkedHashMap> maps8 = monitorIndexDataMapper.selectTender008(request);
//        MonitorIndexDataVO vo8 = getEntityByMonitor(maps8);
        //---------
        MonitorIndexDataVO vo8 = new MonitorIndexDataVO();
        vo8.setFieldset1("-");
        vo8.setFieldset2("-");
        vo8.setFieldset3("-");
        map.put("monitor11", vo8);
        return map;
    }


    /**
     * 合同签订
     *
     * @param request
     * @return
     */
    private HashMap<String, MonitorIndexDataVO> getSignedInMonitor(SmartReportPO request) {
        HashMap<String, MonitorIndexDataVO> map = new HashMap<>();

        List<LinkedHashMap> maps1 = monitorIndexDataMapper.selectSigned001(request);
        MonitorIndexDataVO vo = getEntityByMonitor(maps1);
//        MonitorIndexDataVO vo1 = getEntityByMonitor(maps1);
//        MonitorIndexDataVO vo =new MonitorIndexDataVO();
//        vo.setFieldset1("100%");
//        vo.setFieldset2("100%");
//        vo.setFieldset3("-");
        map.put("monitor12", vo);
        return map;
    }

    /**
     * 合同履约
     *
     * @param reportPO
     * @return
     */
    private HashMap<String, MonitorIndexDataVO> getExerciseInMonitor(SmartReportPO reportPO) {
        SmartReportPO request = new SmartReportPO();
        request.setAccStartTime(reportPO.getStartTime());//获取当前月
        request.setAccEndTime(reportPO.getEndTime());//获取当前月
        request.setAccLastStartTime(TimeUtil.lastTime(reportPO.getStartTime()));//获取去年本月
        request.setAccLastEndTime(TimeUtil.lastTime(reportPO.getEndTime()));//获取去年本月

        HashMap<String, MonitorIndexDataVO> map = new HashMap<>();
        List<LinkedHashMap> maps1 = monitorIndexDataMapper.selectExercise001(request);
        List<LinkedHashMap> maps2 = monitorIndexDataMapper.selectExercise002(request);
        List<LinkedHashMap> maps3 = monitorIndexDataMapper.selectExercise003(reportPO);
//        List<LinkedHashMap> maps4 = monitorIndexDataMapper.selectExercise004(request);
        List<LinkedHashMap> maps6 = monitorIndexDataMapper.selectExercise006(reportPO);
        List<LinkedHashMap> maps7 = monitorIndexDataMapper.selectExercise007(reportPO);
        List<LinkedHashMap> maps8 = monitorIndexDataMapper.selectExercise008(request);

        MonitorIndexDataVO vo1 = getEntityByMonitor(maps1);
        map.put("monitor13", vo1);
        MonitorIndexDataVO vo2 = getEntityByMonitor(maps2);
        map.put("monitor14", vo2);
//        MonitorIndexDataVO vo3 = getEntityByMonitor(maps3);
        MonitorIndexDataVO vo3 = new MonitorIndexDataVO();
        vo3.setFieldset1("-");
        vo3.setFieldset2("-");
        vo3.setFieldset3("-");
        map.put("monitor15", vo3);
//        MonitorIndexDataVO vo4 = getEntityByMonitor(maps4);
        MonitorIndexDataVO vo = new MonitorIndexDataVO();
        vo.setFieldset1("-");
        vo.setFieldset2("-");
        vo.setFieldset3("-");
        map.put("monitor16", vo);
        MonitorIndexDataVO vo5 = new MonitorIndexDataVO();
        vo5.setFieldset1("100%");
        vo5.setFieldset2("100%");
        vo5.setFieldset3("-");
        map.put("monitor17", vo5);
        MonitorIndexDataVO vo6 = getEntityByMonitor2(maps6);
        map.put("monitor18", vo6);
        MonitorIndexDataVO vo7 = getEntityByMonitor(maps7);
        map.put("monitor19", vo7);
        MonitorIndexDataVO vo8 = getEntityByMonitor(maps8);
        map.put("monitor20", vo8);
        return map;
    }

    /**
     * 仓储管理
     *
     * @param request
     * @return
     */
    private HashMap<String, MonitorIndexDataVO> getStorageInMonitor(SmartReportPO request) {
        HashMap<String, MonitorIndexDataVO> map = new HashMap<>();
        List<LinkedHashMap> maps = monitorIndexDataMapper.selectStorage001(request);
        MonitorIndexDataVO vo1 = getEntityByMonitor(maps);
        BigDecimal storage002 = monitorIndexDataMapper.selectStorage002(request).setScale(2, BigDecimal.ROUND_HALF_UP);
        MonitorIndexDataVO vo2 = new MonitorIndexDataVO();
        vo2.setFieldset1(BigDecimalUtil.getExchangeRate(storage002, 10000));
        vo2.setFieldset2("-");
        vo2.setFieldset3("-");
        BigDecimal storage003 = monitorIndexDataMapper.selectStorage003(request).setScale(2, BigDecimal.ROUND_HALF_UP);
        MonitorIndexDataVO vo3 = new MonitorIndexDataVO();
        vo3.setFieldset1(storage003 + "%");
        vo3.setFieldset2("-");
        vo3.setFieldset3("-");
        List<LinkedHashMap> maps4 = monitorIndexDataMapper.selectStorage004(request);
        //
        MonitorIndexDataVO vo4 = getEntityByMonitor3(maps4);

        List<LinkedHashMap> maps5 = monitorIndexDataMapper.selectStorage005(request);
        MonitorIndexDataVO vo5 = getEntityByMonitor(maps5);
        map.put("monitor21", vo1);
        map.put("monitor22", vo2);
        map.put("monitor23", vo3);
        map.put("monitor24", vo4);
        map.put("monitor25", vo5);
        return map;
    }


    /**
     * 平衡利库
     *
     * @param request
     * @return
     */
    private HashMap<String, MonitorIndexDataVO> getBalanceInMonitor(SmartReportPO request) {
        HashMap<String, MonitorIndexDataVO> map = new HashMap<>();
        request.setLastStartTime(TimeUtil.lastTime(request.getStartTime()));
        List<LinkedHashMap> maps = monitorIndexDataMapper.selectBalance001(request);
        MonitorIndexDataVO vo = getEntityByMonitor(maps);
        map.put("monitor26", vo);
        return map;
    }

    /**
     * 废旧物资
     *
     * @param request
     * @return
     */
    private HashMap<String, MonitorIndexDataVO> getWasteInMonitor(SmartReportPO request) {
        HashMap<String, MonitorIndexDataVO> map = new HashMap<>();
        List<LinkedHashMap> maps = monitorIndexDataMapper.selectWaste001(request);
        MonitorIndexDataVO vo1 = getEntityByMonitor(maps);
        map.put("monitor27", vo1);
        List<LinkedHashMap> maps2 = monitorIndexDataMapper.selectWaste002(request);
        MonitorIndexDataVO vo2 = getEntityByMonitor(maps2);
        map.put("monitor28", vo2);
        return map;
    }

    /**
     * 质量监督
     *
     * @param request
     * @return
     */
    private HashMap<String, MonitorIndexDataVO> getSupervisionInMonitor(SmartReportPO request) {
        HashMap<String, MonitorIndexDataVO> map = new HashMap<>();
        List<LinkedHashMap> maps = monitorIndexDataMapper.selectSupervision001(request);
        MonitorIndexDataVO vo1 = getEntityByMonitor(maps);
        map.put("monitor29", vo1);
        List<LinkedHashMap> maps2 = monitorIndexDataMapper.selectSupervision002(request);
        MonitorIndexDataVO vo2 = getEntityByMonitor(maps2);
        map.put("monitor30", vo2);
        List<LinkedHashMap> maps3 = monitorIndexDataMapper.selectSupervision003(request);
        MonitorIndexDataVO vo3 = getEntityByMonitor2(maps3);
        map.put("monitor31", vo3);
        List<LinkedHashMap> maps4 = monitorIndexDataMapper.selectSupervision004(request);
        MonitorIndexDataVO vo4 = getEntityByMonitor2(maps4);
        map.put("monitor32", vo4);

        BigDecimal rate1 = getActualValueByRateBigDecimal(maps3, "1");
        BigDecimal rate2 = getActualValueByRateBigDecimal(maps4, "1");

        String scale1 = BigDecimalUtil.getExchangeRate(getSub(rate1, rate2), 100);

        BigDecimal rate3 = getActualValueByRateBigDecimal(maps3, "2");
        BigDecimal rate4 = getActualValueByRateBigDecimal(maps4, "2");

        String scale2 = BigDecimalUtil.getExchangeRate(getSub(rate3, rate4), 100);

        MonitorIndexDataVO vo5 = new MonitorIndexDataVO();

        vo5.setFieldset1(scale1 + "天");
        vo5.setFieldset2(scale2 + "天");
        vo5.setFieldset3(contrastByTable(scale1, scale2));

        map.put("monitor33", vo5);
        List<LinkedHashMap> maps6 = monitorIndexDataMapper.selectSupervision006(request);
        MonitorIndexDataVO vo6 = getEntityByMonitor2(maps6);
        map.put("monitor34", vo6);
        List<LinkedHashMap> maps7 = monitorIndexDataMapper.selectSupervision007(request);
        MonitorIndexDataVO vo7 = getEntityByMonitor2(maps7);
        map.put("monitor35", vo7);
        List<LinkedHashMap> maps8 = monitorIndexDataMapper.selectSupervision008(request);
        MonitorIndexDataVO vo8 = getEntityByMonitor2(maps8);
        map.put("monitor36", vo8);
        return map;
    }

}
