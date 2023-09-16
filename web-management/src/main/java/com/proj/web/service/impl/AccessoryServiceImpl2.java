package com.proj.web.service.impl;

import com.proj.web.mapper.BasicDataStatisticsMapper;

/**
 * 附件
 */
public class AccessoryServiceImpl2 {

    private BasicDataStatisticsMapper accessoryMapper;

    /**
     * 获取基础数据统计表
     *
     * @param request
     * @return
     */
//    @Override
//    public Map<String, BasicDataStatisticsVO> getBasicDataStatisticsMap(ReportRequest request) {
//        SmartReportPO reportPO = new SmartReportPO();
//        String startTime = request.getStartTime();//2022-03-01
//        String endTime = request.getEndTime();//2022-03-31
//        String time = request.getTime();//2022-03
//        String year = request.getYear();//2022
//        //当月时间
//        reportPO.setStartTime(startTime);
//        reportPO.setEndTime(endTime);
//        //本年累计时间-到当月
//        reportPO.setAccStartTime(year+"-01-01");
//        reportPO.setAccEndTime(endTime);
//        //去年累计时间-到去年当月
//        reportPO.setAccLastStartTime(TimeUtil.lastTime(year)+"-01-01");
//        reportPO.setAccLastEndTime(TimeUtil.lastTime(endTime));
//        HashMap<String, BasicDataStatisticsVO> purchaseInBasic = getPurchaseInBasic(reportPO);//采购计划
//        HashMap<String, BasicDataStatisticsVO> tenderInBasic = getTenderInBasic(reportPO);//招标采购-有
////        HashMap<String, BasicDataStatisticsVO> signedInBasic = getSignedInBasic(reportPO);//合同签订-有
////        HashMap<String, BasicDataStatisticsVO> exerciseInBasic = getExerciseInBasic(reportPO);//合同履约-暂无
////        HashMap<String, BasicDataStatisticsVO> clearingInBasic = getClearingInBasic(reportPO);//合同结算-有
////        HashMap<String, BasicDataStatisticsVO> storageInBasic = getStorageInBasic(reportPO);//仓储管理-有
////        HashMap<String, BasicDataStatisticsVO> balanceInBasic = getBalanceInBasic(reportPO);//平衡利库-暂无
////        HashMap<String, BasicDataStatisticsVO> wasteInBasic = getWasteInBasic(reportPO);//废旧物资
////        HashMap<String, BasicDataStatisticsVO> supervisionInBasic = getSupervisionInBasic(reportPO);//质量监督
////        HashMap<String, BasicDataStatisticsVO> supplierInBasic = getSupplierInBasic(reportPO);//供应商管理-暂无
//        Map<String, BasicDataStatisticsVO> allMap = new HashMap<>();
////        allMap.putAll(purchaseInBasic);//采购计划
////        allMap.putAll(tenderInBasic);//招标采购
////        allMap.putAll(signedInBasic);//合同签订
////        allMap.putAll(exerciseInBasic);//合同履约
////        allMap.putAll(clearingInBasic);//合同结算
////        allMap.putAll(storageInBasic);//仓储管理
////        allMap.putAll(balanceInBasic);//平衡利库
////        allMap.putAll(wasteInBasic);//废旧物资
////        allMap.putAll(supervisionInBasic);//质量监督
////        allMap.putAll(supplierInBasic);//供应商管理
//        return allMap;
//    }
//
//    public void accessoryByBasic(Map<String, BasicDataStatisticsVO> allMap, ReportRequest request) {
//        Map<String, Object> map = new HashMap<>();
//        int x=1;
//        for (int i = 1; i <= allMap.size(); i++) {
//            BasicDataStatisticsVO storage = allMap.get("basic"+i);
//            List<String> valuesList = printValues(storage);
//            System.out.println(valuesList);
//            for(int y = 0; y < valuesList.size();y++) {
//                map.put("basic"+x++,valuesList.get(y));
//            }
//        }
//        for (int i = 1; i <= map.size(); i++) {
//            SmartReportAddPO datail = new SmartReportAddPO();
//            if (i > 99) {
//                datail.setIndexNumber("basic_" + i);//指标编号
//                Object obj = map.get("basic" + i);
//                datail.setValueIndex(String.valueOf(QuartUtil.off(obj)));//指标数值
//            } else if (i < 10) {
//                datail.setIndexNumber("basic_00" + i);//指标编号
//                Object obj = map.get("basic" + i);
//                datail.setValueIndex(String.valueOf(QuartUtil.off(obj)));//指标数值
//            } else {
//                datail.setIndexNumber("basic_0" + i);//指标编号
//                Object obj = map.get("basic" + i);
//                datail.setValueIndex(String.valueOf(QuartUtil.off(obj)));//指标数值
//            }
//            datail.setCreationTime(request.getTime());
//            datail.setSource("basic");
//            accessoryMapper.basicDataStatisticsAdd(datail);
//        }
//    }
//
//    /**
//     * 供应商管理
//     *
//     * @param request
//     * @return
//     */
//    private HashMap<String, BasicDataStatisticsVO> getSupplierInBasic(SmartReportPO request) {
//        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        vo.setFieldset1(String.valueOf(""));
//        vo.setFieldset2(String.valueOf(""));
//        vo.setFieldset3(String.valueOf(""));
//        vo.setFieldset4(String.valueOf(""));
//        vo.setFieldset5(String.valueOf(""));
//        vo.setFieldset6(String.valueOf(""));
//        vo.setFieldset7(String.valueOf(""));
//        map.put("basic32",vo);
//        return map;
//    }
//
//    /**
//     * 质量监督
//     *
//     * @param request
//     * @return
//     */
//    private HashMap<String, BasicDataStatisticsVO> getSupervisionInBasic(SmartReportPO request) {
//        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        vo.setFieldset1(String.valueOf(""));
//        vo.setFieldset2(String.valueOf(""));
//        vo.setFieldset3(String.valueOf(""));
//        vo.setFieldset4(String.valueOf(""));
//        vo.setFieldset5(String.valueOf(""));
//        vo.setFieldset6(String.valueOf(""));
//        vo.setFieldset7(String.valueOf(""));
//        map.put("basic30",vo);
//        List<LinkedHashMap> maps2 = accessoryMapper.selectSupervision002(request);
//        BigDecimal basic08 = ActualValueUtils.getActualValueByP(maps2, "1");//数量
////        BigDecimal basic09 = ActualValueUtils.getActualValueByP(maps2, "1");//金额(当月)
//        BigDecimal basic10 = ActualValueUtils.getActualValueByP(maps2, "2");//数量
////        BigDecimal basic11 = ActualValueUtils.getActualValueByP(maps2, "1");//金额
//        BigDecimal basic12 = ActualValueUtils.getActualValueByP(maps2, "3");//金额
////        BigDecimal basic13 = BigDecimalUtil.getYoy(basic11, basic12);//同比
////        String basic14 = QuartUtil.udByTable(basic13);//变化情况
//        BasicDataStatisticsVO vo2 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(basic08));
//        vo2.setFieldset2(String.valueOf(""));
//        vo2.setFieldset3(String.valueOf(basic10));
//        vo2.setFieldset4(String.valueOf(""));
//        vo2.setFieldset5(String.valueOf(""));
//        vo2.setFieldset6(String.valueOf(""));
//        vo2.setFieldset7("");
//        map.put("basic31",vo2);
//        return map;
//    }
//
//    /**
//     * 废旧物资
//     *
//     * @param request
//     * @return
//     */
//    private HashMap<String, BasicDataStatisticsVO> getWasteInBasic(SmartReportPO request) {
//        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
//        List<LinkedHashMap> maps = accessoryMapper.selectWaste001(request);
//        Integer basic01 = ActualValueUtils.getActualValueByN(maps, "1");//数量
//        BigDecimal basic02 = ActualValueUtils.getActualValueByP(maps, "1");//金额(当月)
//        Integer basic03 = ActualValueUtils.getActualValueByN(maps, "2");//数量
//        BigDecimal basic04 = ActualValueUtils.getActualValueByP(maps, "1");//金额
//        BigDecimal basic05 = ActualValueUtils.getActualValueByP(maps, "2");//金额
//        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
//        String basic07 = QuartUtil.udByTable(basic06);//变化情况
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        vo.setFieldset1(String.valueOf(basic01));
//        vo.setFieldset2(String.valueOf(basic02));
//        vo.setFieldset3(String.valueOf(basic03));
//        vo.setFieldset4(String.valueOf(basic04));
//        vo.setFieldset5(String.valueOf(basic05));
//        vo.setFieldset6(String.valueOf(basic06));
//        vo.setFieldset7(basic07);
//        map.put("basic29",vo);
//        return map;
//    }
//
//
//    /**
//     * 平衡利库
//     *
//     * @param request
//     * @return
//     */
//    private HashMap<String, BasicDataStatisticsVO> getBalanceInBasic(SmartReportPO request) {
//        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
//        List<LinkedHashMap> maps1 = accessoryMapper.selectBalance001(request);
//        Integer basic01 = ActualValueUtils.getActualValueByN(maps1, "1");//数量
//        BigDecimal basic02 = ActualValueUtils.getActualValueByP(maps1, "1");//金额(当月)
//        Integer basic03 = ActualValueUtils.getActualValueByN(maps1, "2");//数量
//        BigDecimal basic04 = ActualValueUtils.getActualValueByP(maps1, "1");//金额
//        BigDecimal basic05 = ActualValueUtils.getActualValueByP(maps1, "2");//金额
//        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
//        String basic07 = QuartUtil.udByTable(basic06);//变化情况
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        vo.setFieldset1(String.valueOf(basic01));
//        vo.setFieldset2(String.valueOf(basic02));
//        vo.setFieldset3(String.valueOf(basic03));
//        vo.setFieldset4(String.valueOf(basic04));
//        vo.setFieldset5(String.valueOf(basic05));
//        vo.setFieldset6(String.valueOf(basic06));
//        vo.setFieldset7(basic07);
//        map.put("basic28",vo);
//        return map;
//    }
//
//    /**
//     * 合同结算
//     *
//     * @param request
//     * @return
//     */
//    private HashMap<String, BasicDataStatisticsVO> getClearingInBasic(SmartReportPO request) {
//        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
//        List<LinkedHashMap> maps1 = accessoryMapper.selectClearing001(request);
//        Integer basic01 = ActualValueUtils.getActualValueByN(maps1, "1");//数量
//        BigDecimal basic02 = ActualValueUtils.getActualValueByP(maps1, "1");//金额(当月)
//        Integer basic03 = ActualValueUtils.getActualValueByN(maps1, "2");//数量
//        BigDecimal basic04 = ActualValueUtils.getActualValueByP(maps1, "1");//金额
//        BigDecimal basic05 = ActualValueUtils.getActualValueByP(maps1, "2");//金额
//        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
//        String basic07 = QuartUtil.udByTable(basic06);//变化情况
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        vo.setFieldset1(String.valueOf(basic01));
//        vo.setFieldset2(String.valueOf(basic02));
//        vo.setFieldset3(String.valueOf(basic03));
//        vo.setFieldset4(String.valueOf(basic04));
//        vo.setFieldset5(String.valueOf(basic05));
//        vo.setFieldset6(String.valueOf(basic06));
//        vo.setFieldset7(basic07);
//        map.put("basic17",vo);
//        BasicDataStatisticsVO vo2 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(""));
//        vo2.setFieldset2(String.valueOf(""));
//        vo2.setFieldset3(String.valueOf(""));
//        vo2.setFieldset4(String.valueOf(""));
//        vo2.setFieldset5(String.valueOf(""));
//        vo2.setFieldset6(String.valueOf(""));
//        vo2.setFieldset7(String.valueOf(""));
//        map.put("basic18",vo2);
//        return map;
//    }
//
//    /**
//     * 合同签订
//     *
//     * @param request
//     * @return
//     */
//    private HashMap<String, BasicDataStatisticsVO> getSignedInBasic(SmartReportPO request) {
//        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
//        List<LinkedHashMap> n1 = accessoryMapper.selectSigned001n1(request);
//        List<LinkedHashMap> p1 = accessoryMapper.selectSigned001p1(request);
//        Integer basic01 = ActualValueUtils.getActualValueByN(n1, "1");//数量
//        BigDecimal basic02 = ActualValueUtils.getActualValueByP(p1, "1");//金额(当月)
//        Integer basic03 = ActualValueUtils.getActualValueByN(n1, "2");//数量
//        BigDecimal basic04 = ActualValueUtils.getActualValueByP(p1, "1");//金额
//        BigDecimal basic05 = ActualValueUtils.getActualValueByP(p1, "2");//金额
//        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
//        String basic07 = QuartUtil.udByTable(basic06);//变化情况
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        vo.setFieldset1(String.valueOf(basic01));
//        vo.setFieldset2(String.valueOf(basic02));
//        vo.setFieldset3(String.valueOf(basic03));
//        vo.setFieldset4(String.valueOf(basic04));
//        vo.setFieldset5(String.valueOf(basic05));
//        vo.setFieldset6(String.valueOf(basic06));
//        vo.setFieldset7(basic07);
//        map.put("basic12",vo);
//        List<LinkedHashMap> maps = accessoryMapper.selectSigned002(request);
//        Integer basic08 = ActualValueUtils.getActualValueByN(maps, "1");//数量
//        BigDecimal basic09 = ActualValueUtils.getActualValueByP(maps, "1");//金额(当月)
//        Integer basic10 = ActualValueUtils.getActualValueByN(maps, "2");//数量
//        BigDecimal basic11 = ActualValueUtils.getActualValueByP(maps, "1");//金额
//        BigDecimal basic12 = ActualValueUtils.getActualValueByP(maps, "2");//金额
//        BigDecimal basic13 = BigDecimalUtil.getYoy(basic11, basic12);//同比
//        String basic14 = QuartUtil.udByTable(basic13);//变化情况
//        BasicDataStatisticsVO vo2 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(basic08));
//        vo2.setFieldset2(String.valueOf(basic09));
//        vo2.setFieldset3(String.valueOf(basic10));
//        vo2.setFieldset4(String.valueOf(basic11));
//        vo2.setFieldset5(String.valueOf(basic12));
//        vo2.setFieldset6(String.valueOf(basic13));
//        vo2.setFieldset7(basic14);
//        map.put("basic13",vo2);
//        List<LinkedHashMap> maps3 = accessoryMapper.selectSigned003(request);
//        Integer basic15 = ActualValueUtils.getActualValueByN(maps3, "1");//数量
//        BigDecimal basic16 = ActualValueUtils.getActualValueByP(maps3, "1");//金额(当月)
//        Integer basic17 = ActualValueUtils.getActualValueByN(maps3, "2");//数量
//        BigDecimal basic18 = ActualValueUtils.getActualValueByP(maps3, "1");//金额
//        BigDecimal basic19 = ActualValueUtils.getActualValueByP(maps3, "2");//金额
//        BigDecimal basic20 = BigDecimalUtil.getYoy(basic18, basic19);//同比
//        String basic21 = QuartUtil.udByTable(basic20);//变化情况
//        BasicDataStatisticsVO vo3 = new BasicDataStatisticsVO();
//        vo3.setFieldset1(String.valueOf(basic15));
//        vo3.setFieldset2(String.valueOf(basic16));
//        vo3.setFieldset3(String.valueOf(basic17));
//        vo3.setFieldset4(String.valueOf(basic18));
//        vo3.setFieldset5(String.valueOf(basic19));
//        vo3.setFieldset6(String.valueOf(basic20));
//        vo3.setFieldset7(basic21);
//        map.put("basic14",vo3);
//        return map;
//    }
//
//    /**
//     * 合同履约
//     *
//     * @param request
//     * @return
//     */
//    private HashMap<String, BasicDataStatisticsVO> getExerciseInBasic(SmartReportPO request) {
//        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        vo.setFieldset1(String.valueOf(""));
//        vo.setFieldset2(String.valueOf(""));
//        vo.setFieldset3(String.valueOf(""));
//        vo.setFieldset4(String.valueOf(""));
//        vo.setFieldset5(String.valueOf(""));
//        vo.setFieldset6(String.valueOf(""));
//        vo.setFieldset7(String.valueOf(""));
//        map.put("basic15",vo);
//        map.put("basic16",vo);
//        return map;
//    }
//
//
//
//    /**
//     * 仓储管理
//     *
//     * @param request
//     * @return
//     */
//    private HashMap<String, BasicDataStatisticsVO> getStorageInBasic(SmartReportPO request) {
//        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
//        //因为这个特殊
//        List<LinkedHashMap> mapNew = accessoryMapper.selectStorage001(request);//仓储管理-北京公司库存情况库存数量
//        List<LinkedHashMap> p2 = accessoryMapper.selectStorage001p2(request);//仓储管理-物资类批次计划数金额（今年/去年）
//        Integer basic01 = ActualValueUtils.getActualValueByN(mapNew, "1");//数量
//        BigDecimal basic02 = accessoryMapper.selectStorage001p1(request);//金额(当月)
//        Integer basic03 = ActualValueUtils.getActualValueByN(mapNew, "2");//数量
//        BigDecimal basic04 = ActualValueUtils.getActualValueByP(p2, "1");//金额
//        BigDecimal basic05 = ActualValueUtils.getActualValueByP(p2, "2");//金额
//        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
//        String basic07 = QuartUtil.udByTable(basic06);//变化情况
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        vo.setFieldset1(String.valueOf(basic01));
//        vo.setFieldset2(String.valueOf(basic02));
//        vo.setFieldset3(String.valueOf(basic03));
//        vo.setFieldset4(String.valueOf(basic04));
//        vo.setFieldset5(String.valueOf(basic05));
//        vo.setFieldset6(String.valueOf(basic06));
//        vo.setFieldset7(basic07);
//        map.put("basic19", vo);
//        List<LinkedHashMap> mapNew2 = accessoryMapper.selectStorage002(request);//仓储管理-北京公司库存情况入库
//        Integer basic08 = ActualValueUtils.getActualValueByN(mapNew2, "1");//数量
//        BigDecimal basic09 = ActualValueUtils.getActualValueByP(mapNew2, "1");//金额
//        Integer basic10 = ActualValueUtils.getActualValueByN(mapNew2, "2");//数量
//        BigDecimal basic11 = ActualValueUtils.getActualValueByP(mapNew2, "2");//金额
//        BigDecimal basic12 = ActualValueUtils.getActualValueByP(mapNew2, "3");//金额
//        BigDecimal basic13 = BigDecimalUtil.getYoy(basic11, basic12);//同比
//        String basic14 = QuartUtil.udByTable(basic13);//变化情况
//        BasicDataStatisticsVO vo2 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(basic08));
//        vo2.setFieldset2(String.valueOf(basic09));
//        vo2.setFieldset3(String.valueOf(basic10));
//        vo2.setFieldset4(String.valueOf(basic11));
//        vo2.setFieldset5(String.valueOf(basic12));
//        vo2.setFieldset6(String.valueOf(basic13));
//        vo2.setFieldset7(basic14);
//        map.put("basic20", vo2);
//        List<LinkedHashMap> mapNew3 = accessoryMapper.selectStorage003(request);//仓储管理-北京公司库存情况出库
//        Integer basic15 = ActualValueUtils.getActualValueByN(mapNew3, "1");//数量
//        BigDecimal basic16 = ActualValueUtils.getActualValueByP(mapNew3, "1");//金额
//        Integer basic17 = ActualValueUtils.getActualValueByN(mapNew3, "2");//数量
//        BigDecimal basic18 = ActualValueUtils.getActualValueByP(mapNew3, "2");//金额
//        BigDecimal basic19 = ActualValueUtils.getActualValueByP(mapNew3, "3");//金额
//        BigDecimal basic20 = BigDecimalUtil.getYoy(basic18, basic19);//同比
//        String basic21 = QuartUtil.udByTable(basic20);//变化情况
//        BasicDataStatisticsVO vo3 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(basic15));
//        vo2.setFieldset2(String.valueOf(basic16));
//        vo2.setFieldset3(String.valueOf(basic17));
//        vo2.setFieldset4(String.valueOf(basic18));
//        vo2.setFieldset5(String.valueOf(basic19));
//        vo2.setFieldset6(String.valueOf(basic20));
//        vo2.setFieldset7(basic21);
//        map.put("basic21", vo3);
//        List<LinkedHashMap> mapNew4 = accessoryMapper.selectStorage004(request);//仓储管理-代保管物资入库
//        Integer basic22 = ActualValueUtils.getActualValueByN(mapNew4, "1");//数量
////        BigDecimal basic23 = ActualValueUtils.getActualValueByP(mapNew4, "1");//金额
//        Integer basic24 = ActualValueUtils.getActualValueByN(mapNew4, "2");//数量
////        BigDecimal basic25 = ActualValueUtils.getActualValueByP(mapNew4, "2");//金额
////        BigDecimal basic26 = ActualValueUtils.getActualValueByP(mapNew4, "3");//金额
////        BigDecimal basic27 = BigDecimalUtil.getYoy(basic25, basic26);//同比
////        String basic28 = QuartUtil.udByTable(basic27);//变化情况
//        BasicDataStatisticsVO vo4 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(basic22));
//        vo2.setFieldset2(String.valueOf(""));
//        vo2.setFieldset3(String.valueOf(basic24));
//        vo2.setFieldset4(String.valueOf(""));
//        vo2.setFieldset5(String.valueOf(""));
//        vo2.setFieldset6(String.valueOf(""));
//        vo2.setFieldset7("");
//        map.put("basic22", vo4);
//        List<LinkedHashMap> mapNew5 = accessoryMapper.selectStorage005(request);//仓储管理-代保管物资出库
//        Integer basic29 = ActualValueUtils.getActualValueByN(mapNew5, "1");//数量
////        BigDecimal basic30 = ActualValueUtils.getActualValueByP(mapNew5, "1");//金额
//        Integer basic31 = ActualValueUtils.getActualValueByN(mapNew5, "2");//数量
////        BigDecimal basic32 = ActualValueUtils.getActualValueByP(mapNew5, "2");//金额
////        BigDecimal basic33 = ActualValueUtils.getActualValueByP(mapNew5, "3");//金额
////        BigDecimal basic34 = BigDecimalUtil.getYoy(basic32, basic33);//同比
////        String basic35 = QuartUtil.udByTable(basic34);//变化情况
//        BasicDataStatisticsVO vo5 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(basic29));
//        vo2.setFieldset2(String.valueOf(""));
//        vo2.setFieldset3(String.valueOf(basic31));
//        vo2.setFieldset4(String.valueOf(""));
//        vo2.setFieldset5(String.valueOf(""));
//        vo2.setFieldset6(String.valueOf(""));
//        vo2.setFieldset7("");
//        map.put("basic23", vo5);
//        //主动配送
//        BasicDataStatisticsVO vo6 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(""));//36
//        vo2.setFieldset2(String.valueOf(""));//37
//        vo2.setFieldset3(String.valueOf(""));//38
//        vo2.setFieldset4(String.valueOf(""));//39
//        vo2.setFieldset5(String.valueOf(""));//40
//        vo2.setFieldset6(String.valueOf(""));//41
//        vo2.setFieldset7("");//42
//        map.put("basic24", vo6);
//        Integer n1 = accessoryMapper.selectStorage006n1();//专业仓情况库存数量
//        BigDecimal p1 = accessoryMapper.selectStorage006p1();//专业仓情况库存金额
//        BigDecimal basic48 = BigDecimalUtil.getYoy(p1, p1);//同比
//        String basic49 = QuartUtil.udByTable(basic13);//变化情况
//        BasicDataStatisticsVO vo7 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(n1));//43
//        vo2.setFieldset2(String.valueOf(p1));//44
//        vo2.setFieldset3(String.valueOf(n1));//45
//        vo2.setFieldset4(String.valueOf(p1));//46
//        vo2.setFieldset5(String.valueOf(p1));//47
//        vo2.setFieldset6(String.valueOf(basic48));//48
//        vo2.setFieldset7(basic49);//49
//        map.put("basic25", vo7);
//        List<LinkedHashMap> mapNew8 = accessoryMapper.selectStorage007(request);//仓储管理-专业仓情况入仓
//        Integer basic50 = ActualValueUtils.getActualValueByN(mapNew8, "1");//数量
//        BigDecimal basic51 = ActualValueUtils.getActualValueByP(mapNew8, "1");//金额
//        Integer basic52 = ActualValueUtils.getActualValueByN(mapNew8, "2");//数量
//        BigDecimal basic53 = ActualValueUtils.getActualValueByP(mapNew8, "2");//金额
//        BigDecimal basic54 = ActualValueUtils.getActualValueByP(mapNew8, "3");//金额
//        BigDecimal basic55 = BigDecimalUtil.getYoy(basic53, basic54);//同比
//        String basic56 = QuartUtil.udByTable(basic55);//变化情况
//        BasicDataStatisticsVO vo8 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(basic50));
//        vo2.setFieldset2(String.valueOf(basic51));
//        vo2.setFieldset3(String.valueOf(basic52));
//        vo2.setFieldset4(String.valueOf(basic53));
//        vo2.setFieldset5(String.valueOf(basic54));
//        vo2.setFieldset6(String.valueOf(basic55));
//        vo2.setFieldset7(basic56);
//        map.put("basic26", vo8);
//        List<LinkedHashMap> mapNew9 = accessoryMapper.selectStorage008(request);//仓储管理-专业仓情况出仓
//        Integer basic57 = ActualValueUtils.getActualValueByN(mapNew9, "1");//数量
//        BigDecimal basic58 = ActualValueUtils.getActualValueByP(mapNew9, "1");//金额
//        Integer basic59 = ActualValueUtils.getActualValueByN(mapNew9, "2");//数量
//        BigDecimal basic60 = ActualValueUtils.getActualValueByP(mapNew9, "2");//金额
//        BigDecimal basic61 = ActualValueUtils.getActualValueByP(mapNew9, "3");//金额
//        BigDecimal basic62 = BigDecimalUtil.getYoy(basic60, basic61);//同比
//        String basic63 = QuartUtil.udByTable(basic62);//变化情况
//        BasicDataStatisticsVO vo9 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(basic57));
//        vo2.setFieldset2(String.valueOf(basic58));
//        vo2.setFieldset3(String.valueOf(basic59));
//        vo2.setFieldset4(String.valueOf(basic60));
//        vo2.setFieldset5(String.valueOf(basic61));
//        vo2.setFieldset6(String.valueOf(basic62));
//        vo2.setFieldset7(basic63);
//        map.put("basic27", vo9);
//        return map;
//    }
//
//    public BasicDataStatisticsVO getEntityToAll(List<LinkedHashMap> mapNew){
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        Integer basic01 = ActualValueUtils.getActualValueByN(mapNew, "1");//数量
//        BigDecimal basic02 = ActualValueUtils.getActualValueByP(mapNew, "1");//金额
//        Integer basic03 = ActualValueUtils.getActualValueByN(mapNew, "2");//数量
//        BigDecimal basic04 = ActualValueUtils.getActualValueByP(mapNew, "2");//金额
//        BigDecimal basic05 = ActualValueUtils.getActualValueByP(mapNew, "3");//金额
//        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
//        String basic07 = QuartUtil.udByTable(basic06);//变化情况
//        vo.setFieldset1(String.valueOf(basic01));
//        vo.setFieldset2(String.valueOf(basic02));
//        vo.setFieldset3(String.valueOf(basic03));
//        vo.setFieldset4(String.valueOf(basic04));
//        vo.setFieldset5(String.valueOf(basic05));
//        vo.setFieldset6(String.valueOf(basic06));
//        vo.setFieldset7(basic07);
//        return vo;
//    }
//
//    /**
//     * 只有数量
//     * 1代表本月 2代表本年 3代表去年
//     * getActualValueByN取数量
//     * @param mapNew 返回数据集
//     * @return
//     */
//    public BasicDataStatisticsVO getEntityOnlyAmount(List<LinkedHashMap> mapNew){
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        Integer basic01 = ActualValueUtils.getActualValueByN(mapNew, "1");//数量
//        Integer basic03 = ActualValueUtils.getActualValueByN(mapNew, "2");//数量
//        Integer basic05 = ActualValueUtils.getActualValueByN(mapNew, "3");//数量
//        BigDecimal basic06 = BigDecimalUtil.getYoy(BigDecimal.valueOf(basic03), BigDecimal.valueOf(basic05));//同比
//        String basic07 = QuartUtil.udByTable(basic06);//变化情况
//        vo.setFieldset1(String.valueOf(basic01));
//        vo.setFieldset2(String.valueOf("-"));
//        vo.setFieldset3(String.valueOf(basic03));
//        vo.setFieldset4(String.valueOf("-"));
//        vo.setFieldset5(String.valueOf(basic05));
//        vo.setFieldset6(String.valueOf(basic06));
//        vo.setFieldset7(basic07);
//        return vo;
//    }
//
//    /**
//     * 只有金额
//     * 1代表本月 2代表本年 3代表去年
//     * getActualValueByP取金额
//     * @param mapNew 返回数据集
//     * @return
//     */
//    public BasicDataStatisticsVO getEntityOnlyPrice(List<LinkedHashMap> mapNew){
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        BigDecimal basic02 = ActualValueUtils.getActualValueByP(mapNew, "1");//数量
//        BigDecimal basic04 = ActualValueUtils.getActualValueByP(mapNew, "2");//数量
//        BigDecimal basic05 = ActualValueUtils.getActualValueByP(mapNew, "3");//数量
//        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
//        String basic07 = QuartUtil.udByTable(basic06);//变化情况
//        vo.setFieldset1(String.valueOf("-"));
//        vo.setFieldset2(String.valueOf(basic02));
//        vo.setFieldset3(String.valueOf("-"));
//        vo.setFieldset4(String.valueOf(basic04));
//        vo.setFieldset5(String.valueOf(basic05));
//        vo.setFieldset6(String.valueOf(basic06));
//        vo.setFieldset7(basic07);
//        return vo;
//    }
//
//
//    /**
//     * 招标采购
//     *
//     * @param request
//     * @return
//     */
//    private HashMap<String, BasicDataStatisticsVO> getTenderInBasic(SmartReportPO request) {
//        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
//        List<LinkedHashMap> mapNew = accessoryMapper.selectTender001(request);//招标采购-国网公司实施采购
//        map.put("basic7", getEntityToAll(mapNew));
//        List<LinkedHashMap> mapNew2 = accessoryMapper.selectTender002(request);//招标采购-物资类 没有数量
////        Integer basic08 = ActualValueUtils.getActualValueByN(mapNew2, "1");//数量
//        BigDecimal basic09 = ActualValueUtils.getActualValueByP(mapNew2, "1");//金额
////        Integer  basic10= ActualValueUtils.getActualValueByN(mapNew2, "2");//数量
//        BigDecimal basic11 = ActualValueUtils.getActualValueByP(mapNew2, "2");//金额
//        BigDecimal basic12 = ActualValueUtils.getActualValueByP(mapNew2, "3");//金额
//        BigDecimal basic13 = BigDecimalUtil.getYoy(basic11, basic12);//同比
//        String basic14 = QuartUtil.udByTable(basic13);//变化情况
//        BasicDataStatisticsVO vo2 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(""));
//        vo2.setFieldset2(String.valueOf(basic09));
//        vo2.setFieldset3(String.valueOf(""));
//        vo2.setFieldset4(String.valueOf(basic11));
//        vo2.setFieldset5(String.valueOf(basic12));
//        vo2.setFieldset6(String.valueOf(basic13));
//        vo2.setFieldset7(basic14);
//        map.put("basic8", vo2);
//        List<LinkedHashMap> mapNew3 = accessoryMapper.selectTender001(request);//招标采购-服务类 没有数量
////        Integer basic15 = ActualValueUtils.getActualValueByN(mapNew3, "1");//数量
//        BigDecimal basic16 = ActualValueUtils.getActualValueByP(mapNew3, "1");//金额
////        Integer  basic17= ActualValueUtils.getActualValueByN(mapNew3, "2");//数量
//        BigDecimal basic18 = ActualValueUtils.getActualValueByP(mapNew3, "2");//金额
//        BigDecimal basic19 = ActualValueUtils.getActualValueByP(mapNew3, "3");//金额
//        BigDecimal basic20 = BigDecimalUtil.getYoy(basic18, basic19);//同比
//        String basic21 = QuartUtil.udByTable(basic20);//变化情况
//        BasicDataStatisticsVO vo3 = new BasicDataStatisticsVO();
//        vo3.setFieldset1(String.valueOf(""));
//        vo3.setFieldset2(String.valueOf(basic16));
//        vo3.setFieldset3(String.valueOf(""));
//        vo3.setFieldset4(String.valueOf(basic18));
//        vo3.setFieldset5(String.valueOf(basic19));
//        vo3.setFieldset6(String.valueOf(basic20));
//        vo3.setFieldset7(basic21);
//        map.put("basic9", vo3);
//        //零星物资电商化请购金额
//        BasicDataStatisticsVO vo4 = new BasicDataStatisticsVO();
//        vo4.setFieldset1(String.valueOf(""));
//        vo4.setFieldset2(String.valueOf(""));
//        vo4.setFieldset3(String.valueOf(""));
//        vo4.setFieldset4(String.valueOf(""));
//        vo4.setFieldset5(String.valueOf(""));
//        vo4.setFieldset6(String.valueOf(""));
//        vo4.setFieldset7(String.valueOf(""));
//        map.put("basic10", vo4);
//        //服务类框架协议执行金额
//        BasicDataStatisticsVO vo5 = new BasicDataStatisticsVO();
//        vo5.setFieldset1(String.valueOf(""));
//        vo5.setFieldset2(String.valueOf(""));
//        vo5.setFieldset3(String.valueOf(""));
//        vo5.setFieldset4(String.valueOf(""));
//        vo5.setFieldset5(String.valueOf(""));
//        vo5.setFieldset6(String.valueOf(""));
//        vo5.setFieldset7(String.valueOf(""));
//        map.put("basic11", vo5);
//        System.out.println(map);
//        return map;
//    }
//
//    /**
//     * 采购计划
//     *
//     * @param request
//     * @return
//     */
//    private HashMap<String, BasicDataStatisticsVO> getPurchaseInBasic(SmartReportPO request) {
//        HashMap<String, BasicDataStatisticsVO> map = new HashMap<>();
//
//        List<LinkedHashMap> mapNew2 = accessoryMapper.selectPurchase002(request);//采购计划-物资类批次计划
//        Integer basic08 = ActualValueUtils.getActualValueByN(mapNew2, "1");//数量
//        BigDecimal basic09 = ActualValueUtils.getActualValueByP(mapNew2, "1");//金额
//        Integer basic10 = ActualValueUtils.getActualValueByN(mapNew2, "2");//数量
//        BigDecimal basic11 = ActualValueUtils.getActualValueByP(mapNew2, "2");//金额
//        BigDecimal basic12 = ActualValueUtils.getActualValueByP(mapNew2, "3");//金额
//        BigDecimal basic13 = BigDecimalUtil.getYoy(basic11, basic12);//同比
//        String basic14 = QuartUtil.udByTable(basic13);//变化情况
//        BasicDataStatisticsVO vo2 = new BasicDataStatisticsVO();
//        vo2.setFieldset1(String.valueOf(basic08));
//        vo2.setFieldset2(String.valueOf(basic09));
//        vo2.setFieldset3(String.valueOf(basic10));
//        vo2.setFieldset4(String.valueOf(basic11));
//        vo2.setFieldset5(String.valueOf(basic12));
//        vo2.setFieldset6(String.valueOf(basic13));
//        vo2.setFieldset7(basic14);
//        map.put("basic2", vo2);
//        List<LinkedHashMap> mapNew3 = accessoryMapper.selectPurchase003(request);//采购计划-物资类批次计划
//        Integer basic15 = ActualValueUtils.getActualValueByN(mapNew3, "1");//数量
//        BigDecimal basic16 = ActualValueUtils.getActualValueByP(mapNew3, "1");//金额
//        Integer basic17 = ActualValueUtils.getActualValueByN(mapNew3, "2");//数量
//        BigDecimal basic18 = ActualValueUtils.getActualValueByP(mapNew3, "2");//金额
//        BigDecimal basic19 = ActualValueUtils.getActualValueByP(mapNew3, "3");//金额
//        BigDecimal basic20 = BigDecimalUtil.getYoy(basic18, basic19);//同比
//        String basic21 = QuartUtil.udByTable(basic20);//变化情况
//        BasicDataStatisticsVO vo3 = new BasicDataStatisticsVO();
//        vo3.setFieldset1(String.valueOf(basic15));
//        vo3.setFieldset2(String.valueOf(basic16));
//        vo3.setFieldset3(String.valueOf(basic17));
//        vo3.setFieldset4(String.valueOf(basic18));
//        vo3.setFieldset5(String.valueOf(basic19));
//        vo3.setFieldset6(String.valueOf(basic20));
//        vo3.setFieldset7(basic21);
//        map.put("basic3", vo3);
//
//        List<LinkedHashMap> n1 = accessoryMapper.selectPurchase001n1(request);//采购计划-物资类批次计划数量
//        List<LinkedHashMap> p1 = accessoryMapper.selectPurchase001p1(request);//采购计划-物资类批次计划金额
//        Integer basic01 = ActualValueUtils.getActualValueByN(n1, "1")-basic08-basic15;//数量
//        BigDecimal basic02 = ActualValueUtils.getActualValueByP(p1, "1").subtract(basic09).subtract(basic16);//金额
//        Integer basic03 = ActualValueUtils.getActualValueByN(n1, "2")-basic10-basic17;//数量
//        BigDecimal basic04 = ActualValueUtils.getActualValueByP(p1, "2").subtract(basic11).subtract(basic18);//金额
//        BigDecimal basic05 = ActualValueUtils.getActualValueByP(p1, "3").subtract(basic12).subtract(basic19);//金额
//        BigDecimal basic06 = BigDecimalUtil.getYoy(basic04, basic05);//同比
//        String basic07 = QuartUtil.udByTable(basic06);//变化情况
//        BasicDataStatisticsVO vo = new BasicDataStatisticsVO();
//        vo.setFieldset1(String.valueOf(basic01));
//        vo.setFieldset2(String.valueOf(basic02));
//        vo.setFieldset3(String.valueOf(basic03));
//        vo.setFieldset4(String.valueOf(basic04));
//        vo.setFieldset5(String.valueOf(basic05));
//        vo.setFieldset6(String.valueOf(basic06));
//        vo.setFieldset7(basic07);
//        map.put("basic1", vo);
//
//
//        List<LinkedHashMap> mapNew4 = accessoryMapper.selectPurchase004(request);//采购计划-物资类批次计划
//        Integer basic22 = ActualValueUtils.getActualValueByN(mapNew4, "1");//数量
//        BigDecimal basic23 = ActualValueUtils.getActualValueByP(mapNew4, "1");//金额
//        Integer basic24 = ActualValueUtils.getActualValueByN(mapNew4, "2");//数量
//        BigDecimal basic25 = ActualValueUtils.getActualValueByP(mapNew4, "2");//金额
//        BigDecimal basic26 = ActualValueUtils.getActualValueByP(mapNew4, "3");//金额
//        BigDecimal basic27 = BigDecimalUtil.getYoy(basic25, basic26);//同比
//        String basic28 = QuartUtil.udByTable(basic27);//变化情况
//        BasicDataStatisticsVO vo4 = new BasicDataStatisticsVO();
//        vo4.setFieldset1(String.valueOf(basic22));
//        vo4.setFieldset2(String.valueOf(basic23));
//        vo4.setFieldset3(String.valueOf(basic24));
//        vo4.setFieldset4(String.valueOf(basic25));
//        vo4.setFieldset5(String.valueOf(basic26));
//        vo4.setFieldset6(String.valueOf(basic27));
//        vo4.setFieldset7(basic28);
//        map.put("basic4", vo4);
//        List<LinkedHashMap> mapNew5 = accessoryMapper.selectPurchase005(request);//采购计划-物资类批次计划
//        Integer basic29 = ActualValueUtils.getActualValueByN(mapNew5, "1");//数量
//        BigDecimal basic30 = ActualValueUtils.getActualValueByP(mapNew5, "1");//金额
//        Integer basic31 = ActualValueUtils.getActualValueByN(mapNew5, "2");//数量
//        BigDecimal basic32 = ActualValueUtils.getActualValueByP(mapNew5, "2");//金额
//        BigDecimal basic33 = ActualValueUtils.getActualValueByP(mapNew5, "3");//金额
//        BigDecimal basic34 = BigDecimalUtil.getYoy(basic32, basic33);//同比
//        String basic35 = QuartUtil.udByTable(basic34);//变化情况
//        BasicDataStatisticsVO vo5 = new BasicDataStatisticsVO();
//        vo5.setFieldset1(String.valueOf(basic29));
//        vo5.setFieldset2(String.valueOf(basic30));
//        vo5.setFieldset3(String.valueOf(basic31));
//        vo5.setFieldset4(String.valueOf(basic32));
//        vo5.setFieldset5(String.valueOf(basic33));
//        vo5.setFieldset6(String.valueOf(basic34));
//        vo5.setFieldset7(basic35);
//        map.put("basic5", vo5);
//        List<LinkedHashMap> mapNew6 = accessoryMapper.selectPurchase006(request);//采购计划-物资类批次计划
//        Integer basic36 = ActualValueUtils.getActualValueByN(mapNew6, "1");//数量
//        BigDecimal basic37 = ActualValueUtils.getActualValueByP(mapNew6, "1");//金额
//        Integer basic38 = ActualValueUtils.getActualValueByN(mapNew6, "2");//数量
//        BigDecimal basic39 = ActualValueUtils.getActualValueByP(mapNew6, "2");//金额
//        BigDecimal basic40 = ActualValueUtils.getActualValueByP(mapNew6, "3");//金额
//        BigDecimal basic41 = BigDecimalUtil.getYoy(basic39, basic40);//同比
//        String basic42 = QuartUtil.udByTable(basic41);//变化情况
//        BasicDataStatisticsVO vo6 = new BasicDataStatisticsVO();
//        vo6.setFieldset1(String.valueOf(basic36));
//        vo6.setFieldset2(String.valueOf(basic37));
//        vo6.setFieldset3(String.valueOf(basic38));
//        vo6.setFieldset4(String.valueOf(basic39));
//        vo6.setFieldset5(String.valueOf(basic40));
//        vo6.setFieldset6(String.valueOf(basic41));
//        vo6.setFieldset7(basic42);
//        map.put("basic6", vo6);
//        return map;
//    }
//
//
//    /**
//     * 获取监控指标数据统计表 的key和value 并添加到结果表
//     *
//     * @param request
//     * @return
//     */
//    @Override
//    public HashMap<String, Object> getMonitorIndexDataMap(ReportRequest request) {
//        return null;
//    }
//
//    /**
//     * 获取物资公司预警情况统计表 的key和value 并添加到结果表
//     *
//     * @param request
//     * @return
//     */
//    @Override
//    public HashMap<String, Object> getMaterialsFirmEarlyMap(ReportRequest request) {
//        return null;
//    }
//
//    /**
//     * 获取三级运营中心预警情况统计表 的key和value 并添加到结果表
//     *
//     * @param request
//     * @return
//     */
//    @Override
//    public HashMap<String, Object> getThreeLevelEarlyMap(ReportRequest request) {
//        return null;
//    }

}
