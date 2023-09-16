package com.proj.web.service.impl;

import com.proj.core.utils.exception.ProjException;
import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.*;
import com.proj.web.entity.SmartReportAddPO;
import com.proj.web.entity.SmartReportSecondPO;
import com.proj.web.mapper.ReportMapper;
import com.proj.web.service.AccessoryService;
import com.proj.web.service.MainBadyService;
import com.proj.web.service.ReportService;
import com.proj.web.util.QuartUtil;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.*;

import static com.proj.web.util.ReflectionExample.*;

@Service
@Transactional(rollbackFor = ProjException.class)
public class ReportServiceImpl implements ReportService {

    private final static String MAINBADY = "mainBady";//正文标识
    private final static String BASIC = "basic";//基础数据统计表标识
    private final static String MONITOR = "monitor";//监控指标数据统计表标识
    private final static String MATERIALS = "materials";//物资公司预警情况统计表标识
    private final static String THREELEVEL = "threeLevel";//三级运营中心预警情况统计表标识
    private final static String SECONDARYWARN = "secondaryWarn";//二级运营中心预警情况正文


    /*
    月报汇总
     */
    @Resource
    ReportMapper reportMapper;

    /*
    正文
   */
    @Resource
    MainBadyService mainBadyService;

    /*
    附件/表格
   */
    @Resource
    private AccessoryService accessoryService;


    /**
     * 查看详情
     * 2023.05.24
     * @return
     */
    @Override
    public SmartReportIndexVO getDetail(SmartReportSecondPO smartReportSecond) {
        SmartReportIndexVO smartReportIndexVO=new SmartReportIndexVO();
        if (smartReportSecond.getTerm() == null) {
            return smartReportIndexVO;
        }
        DetailVO datails = reportMapper.selectDatsils(smartReportSecond);
        if (datails == null) {
            DetailVO datail = new DetailVO();
            datail.setZzbbh("查询暂无数据");
            datail.setZzbljsm("查询暂无数据");
            datail.setZzbmc("查询暂无数据");
            datail.setZzbywsm("查询暂无数据");
            smartReportIndexVO.setDetail(datail);
            return smartReportIndexVO;
        }
        smartReportIndexVO.setDetail(datails);
        return smartReportIndexVO;
    }

    /**
     * 添加数据集
     *
     * @return
     */
    @Override
    public Boolean smartReportAdd(ReportRequest request) {
        try {
            reportMapper.deleteByTime(request.getTime(), MAINBADY);//删除所有数据集通过月份
            Map<String, Object> mainBadyMap = mainBadyService.getMainBadyList(request);//正文数据
            smartReportAccessoryByAdd(mainBadyMap, request, MAINBADY);

            reportMapper.deleteByTime(request.getTime(), BASIC);//删除所有数据集通过月份
            Map<String, Object> basicDataStatisticsMap = accessoryService.getBasicDataStatisticsMap(request);//基础数据统计表数据
            smartReportAccessoryByAdd(basicDataStatisticsMap, request, BASIC);

            reportMapper.deleteByTime(request.getTime(), MONITOR);//删除所有数据集通过月份
            Map<String, Object> monitorIndexDataMap = accessoryService.getMonitorIndexDataMap(request);//监控指标数据统计表数据
            smartReportAccessoryByAdd(monitorIndexDataMap, request, MONITOR);

            reportMapper.deleteByTime(request.getTime(), SECONDARYWARN);//删除所有数据集通过月份
            Map<String, Object> secondaryWarning = mainBadyService.getSecondaryWarning(request);
            smartReportAccessoryByAdd(secondaryWarning, request, SECONDARYWARN);


            reportMapper.deleteByTime(request.getTime(), MATERIALS);//删除所有数据集通过月份
            Map<String, Object> materialsMap = accessoryService.getMaterialsMap(request);//物资公司预警情况统计表数据
            smartReportAccessoryByAdd(materialsMap, request, MATERIALS);

            reportMapper.deleteByTime(request.getTime(), THREELEVEL);//删除所有数据集通过月份
            Map<String, Object> threeLevelMap = accessoryService.getThreeLevelMap(request);//各单位情况统计表数据
            smartReportAccessoryByAdd(threeLevelMap, request, THREELEVEL);
        } catch (ProjException e) {
            e.getStackTrace();
        }
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
    private void smartReportAccessoryByAdd(Map<String, Object> map, ReportRequest request, String label) throws ProjException {
        for (int i = 1; i <= map.size(); i++) {
            SmartReportAddPO datail = new SmartReportAddPO();
            datail.setIndexNumber(label + "_" + String.format("%03d", i));//指标编号
            Object obj = map.get(label + i);
            datail.setValueIndex(String.valueOf(QuartUtil.off(obj)));//指标数值

            datail.setCreationTime(request.getTime());
            datail.setSource(label);
            System.out.println(i + "||:" + datail);
            Integer integer = reportMapper.basicDataStatisticsAdd(datail);
            if (integer != 1) {
                throw new ProjException();
            }
        }
    }


    /**
     * 查询数据集-针对正文
     *
     * @return
     */
    @Override
    public MainBadyVO smartReportQueryByMain(ReportRequest request) {
        request.setTargetName("mainBady");
        request.setZtype("yb");
        SmartReportIndexVO vo = new SmartReportIndexVO();
        List<LinkedHashMap> maps = reportMapper.selectAll(request);
        MainBadyVO mainBadyVO = new MainBadyVO();
        List<String> strings = printFields(mainBadyVO);
        int size = maps.size();
        for (int i = 0; i < size; i++) {
            String amount = (String) maps.get(i).get("ZZBSZ");
            setFields(mainBadyVO, strings.get(i), amount);

        }
        return mainBadyVO;
    }

    /**
     * 查询数据集-针对预警正文
     *
     * @param request
     * @return
     */
    @Override
    public SecondaryWarnVO smartReportQueryBySecondaryWarn(ReportRequest request) {
        request.setTargetName("SECONDARYWARN");
        request.setZtype("yb");
        List<LinkedHashMap> maps = reportMapper.selectAll(request);
        SecondaryWarnVO secondaryWarnVO = new SecondaryWarnVO();
        List<String> strings = printFields(secondaryWarnVO);
        int size = maps.size();
        for (int i = 0; i < size; i++) {
            String amount = (String) maps.get(i).get("ZZBSZ");
            setFields(secondaryWarnVO, strings.get(i), amount);

        }
        return secondaryWarnVO;
    }

    /**
     * 查询数据集-针对基础数据统计表
     *
     * @return
     */
    @Override
    public SmartReportIndexVO smartReportQueryByBasicData(ReportRequest request) {
        request.setTargetName("basic");
        request.setZtype("yb");
        SmartReportIndexVO vo = new SmartReportIndexVO();
        List<LinkedHashMap> maps = reportMapper.selectAll(request);
        LinkedList<BasicDataStatisticsVO> list = new LinkedList<>();
        int size = maps.size();
        for (int i = 0; i < size; i += 7) {
            BasicDataStatisticsVO vo1 = new BasicDataStatisticsVO();
            String amount1 = (String) maps.get(i).get("ZZBSZ");
            String amount2 = (String) maps.get(i + 1 < size ? i + 1 : i).get("ZZBSZ");
            String amount3 = (String) maps.get(i + 2 < size ? i + 2 : i).get("ZZBSZ");
            String amount4 = (String) maps.get(i + 3 < size ? i + 3 : i).get("ZZBSZ");
            String amount5 = (String) maps.get(i + 4 < size ? i + 4 : i).get("ZZBSZ");
            String amount6 = (String) maps.get(i + 5 < size ? i + 5 : i).get("ZZBSZ");
            String amount7 = (String) maps.get(i + 6 < size ? i + 6 : i).get("ZZBSZ");
            vo1.setFieldset1(amount1);
            vo1.setFieldset2(amount2);
            vo1.setFieldset3(amount3);
            vo1.setFieldset4(amount4);
            vo1.setFieldset5(amount5);
            vo1.setFieldset6(amount6);
            vo1.setFieldset7(amount7);
            list.addLast(vo1);
        }
        vo.setPageSize(list.size());
        vo.setBasicDataStatisticsList(list);
        return vo;
    }

    /**
     * 查询数据集-针对监控数据统计表
     *
     * @return
     */
    @Override
    public SmartReportIndexVO smartReportQueryByMonitorData(ReportRequest request) {
        request.setTargetName("monitor");
        request.setZtype("yb");
        SmartReportIndexVO vo = new SmartReportIndexVO();
        List<LinkedHashMap> maps = reportMapper.selectAll(request);
        LinkedList<MonitorIndexDataVO> list = new LinkedList<>();
        int size = maps.size();
        for (int i = 0; i < size; i += 3) {
            MonitorIndexDataVO vo1 = new MonitorIndexDataVO();
            String amount1 = (String) maps.get(i).get("ZZBSZ");
            String amount2 = (String) maps.get(i + 1 < size ? i + 1 : i).get("ZZBSZ");
            String amount3 = (String) maps.get(i + 2 < size ? i + 2 : i).get("ZZBSZ");
            vo1.setFieldset1(amount1);
            vo1.setFieldset2(amount2);
            vo1.setFieldset3(amount3);
            list.addLast(vo1);
        }
        vo.setPageSize(list.size());
        vo.setMonitorIndexDataList(list);
        return vo;
    }

    /**
     * 查询数据集-物资公司预警情况统计表
     *
     * @param request
     * @return
     */
    @Override
    public HashMap<String, Object> smartReportQueryBymaterials(ReportRequest request) {
        request.setTargetName("materials");
        request.setZtype("yb");
        HashMap<String, Object> map = new HashMap<>();
        List<LinkedHashMap> allList = reportMapper.selectAll(request);
        List<LinkedHashMap> handerFirst = new ArrayList<>();
        List<LinkedHashMap> handerNext = new ArrayList<>();
        int i = 0;
        int y = 0;
        //遍历表头
        for (; i < allList.size(); i++) {
            if ("总计".equals(allList.get(i).get("ZZBSZ"))) { //获取到某个字段进行截取
                handerFirst = allList.subList(0, ++i);
            }
            if ("指标名称".equals(allList.get(i).get("ZZBSZ"))) { //获取到某个字段进行截取
                handerNext = allList.subList(i, (i * 2 - 1) + i);
                y = i * 2 - 1;
                i = (i * 2 - 1) + i;
                break;
            }
        }
        //遍历内容
        List<List<LinkedHashMap>> contentList = new ArrayList<>();
        for (int x = i; x < allList.size(); ) {
            contentList.add(allList.subList(x, x + y));
            x = x + y;
        }
        map.put("handerFirst", handerFirst);
        map.put("handerNext", handerNext);
        map.put("content", contentList);
        return map;
    }

    /**
     * 查询数据集-各单位预警情况统计表
     *
     * @param request
     * @return
     */
    @Override
    public HashMap<String, Object> smartReportQueryByThreeLevel(ReportRequest request) {
        request.setTargetName("threeLevel");
        request.setZtype("yb");
        HashMap<String, Object> map = new HashMap<>();
        List<LinkedHashMap> allList = reportMapper.selectAll(request);
        List<LinkedHashMap> handerFirst = null;
        int i = 0;
        int y = 0;
        int size = 0;
        //遍历表头
        for (; i < allList.size(); i++) {
            if ("总计".equals(allList.get(i).get("ZZBSZ"))) { //获取到某个字段进行截取
                handerFirst = allList.subList(0, ++i);
                size = i;
                y = i * 2 + 1;
                break;
            }
        }
        //遍历城区所有内容
        List<List<LinkedHashMap>> contentList = new ArrayList<>();
        for (; i < allList.size(); ) {
            List<LinkedHashMap> contentMapList = allList.subList(i, y + i);
            contentList.add(contentMapList);
            i = y + i;
        }

        //分离 预警数量/办结比例
        String city = "";
        Map<String, Object> contentMap = new LinkedHashMap<>();
        for (List<LinkedHashMap> contents : contentList) {
            Object zzbsz = contents.get(0).get("ZZBSZ");
            city = String.valueOf(zzbsz);
            List<LinkedHashMap> mapList = contents.subList(1, contents.size());
            ;
            ArrayList<Object> list = new ArrayList<>();
            for (int x = 0; x < mapList.size(); ) {
                List<LinkedHashMap> maps = mapList.subList(x, x + size);
                x = x + size;
                list.add(maps);
            }
            contentMap.put(city, list);
        }
        map.put("hander", handerFirst);
        map.put("bady", contentMap);
        System.out.println();
        return map;
    }


    /**
     * 物资公司
     *
     * @throws IOException
     */
    @Override
    public void getWzgsByDc(HttpServletResponse response,ReportRequest request) throws IOException {
//        ReportRequest request = new ReportRequest();
        request.setTargetName("materials");
        request.setZtype("yb");
        request.setTime("2023-04");
        List<LinkedHashMap> allList = reportMapper.selectAll(request);
        List<Map<String, Object>> mapList = new ArrayList();
        List<LinkedHashMap> handerFirst = new ArrayList<>();
        List<LinkedHashMap> handerNext = new ArrayList<>();
        int i = 0;
        int y = 0;
        //遍历表头
        for (; i < allList.size(); i++) {
            if ("总计".equals(allList.get(i).get("ZZBSZ"))) { //获取到某个字段进行截取
                handerFirst = allList.subList(0, ++i);
            }
            if ("指标名称".equals(allList.get(i).get("ZZBSZ"))) { //获取到某个字段进行截取
                handerNext = allList.subList(i, (i * 2 - 1) + i);
                y = i * 2 - 1;
                i = (i * 2 - 1) + i;
                break;
            }
        }

        int c = 1;
        LinkedHashMap handerFirstMap = new LinkedHashMap<String, Object>();
        handerFirstMap.put("1", "分发对象");
        for (int x = 1; x < handerFirst.size(); x++) {
            handerFirstMap.put(String.valueOf(++c), handerFirst.get(x).get("ZZBSZ"));
            handerFirstMap.put(String.valueOf(++c), "");
        }
        mapList.add(handerFirstMap);

        LinkedHashMap handerNextMap = new LinkedHashMap<String, Object>();
        handerNextMap.put("1", "指标名称");
        for (int x = 1; x < handerFirstMap.size(); ) {
            Object zzbsz = handerNext.get(x).get("ZZBSZ");
            handerNextMap.put(String.valueOf(++x), zzbsz);
        }
        mapList.add(handerNextMap);

        //遍历内容
        for (int x = i; x < allList.size(); ) {
            LinkedHashMap contentMap = new LinkedHashMap<String, Object>();
            List<LinkedHashMap> maps = allList.subList(x, x + y);
            c = 1;
            for (LinkedHashMap lis : maps) {
                contentMap.put(String.valueOf(++c), lis.get("ZZBSZ"));
            }
            mapList.add(contentMap);
            x = x + y;
        }

        //创建word对象
        XWPFDocument docxDocument = new XWPFDocument();

        // 动态表格 // 默认有一个1行1列的表头
        XWPFTable table = docxDocument.createTable();
        //table.setWidth("95%");// 设置宽度
        table.setWidthType(TableWidthType.PCT);//设置表格相对宽度
        table.setTableAlignment(TableRowAlign.CENTER);
        // 遍历数据
        for (i = 0; i < mapList.size(); i++) {
            XWPFTableRow row = null;
            if (i == 0) {
                row = table.getRow(i);
            } else {
                // 不能使用createRow() 用于简单表，这是复杂表
                row = table.insertNewTableRow(i);
            }
            Iterator<Map.Entry<String, Object>> iterator = mapList.get(i).entrySet().iterator();
            XWPFTableCell cell = null;
            int j = 0;
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();

                // 第一行第一列直接获取
                if (i == 0 && j == 0) {
                    cell = row.getCell(j);
                } else {
                    cell = row.createCell();
                }
                CTTcPr tcpr = cell.getCTTc().addNewTcPr();
                CTTblWidth cellw = tcpr.addNewTcW();
                cellw.setType(STTblWidth.DXA);

                // 第一列与其他列宽度
                if (j == 0) {
                    cellw.setW(BigInteger.valueOf(360 * 17));
                } else {
                    cellw.setW(BigInteger.valueOf(360 * 5));
                }
                cell.setText(next.getValue().toString());
                j++;

            }
        }

        //获取第二行应该占比几个单元格（减1因为第一列不算 从第二列开始合并,每两个合并成一个单元格最终与表头对应）
        int size = (handerFirst.size() - 1) * 2;
        //合并列
        for (i = 1; i < size; i++) {
            mergeCellsHorizontal(table, 0, i, ++i);

        }


        //分页，作用为再同一个word下面生成另一个表格
        docxDocument.createParagraph().createRun().addBreak(BreakClear.RIGHT);

        request.setTargetName("threeLevel");
        allList = reportMapper.selectAll(request);
        mapList = new ArrayList();
        handerFirst = null;
        y = 0;
        //遍历表头
        for (i = 0; i < allList.size(); i++) {
            if ("总计".equals(allList.get(i).get("ZZBSZ"))) { //获取到某个字段进行截取
                handerFirst = allList.subList(0, ++i);
                y = i * 2 + 1;
                break;
            }
        }
        //遍历城区所有内容
        List<List<LinkedHashMap>> contentList = new ArrayList<>();
        for (; i < allList.size(); ) {
            List<LinkedHashMap> contentMapList = allList.subList(i, y + i);
            contentList.add(contentMapList);
            i = y + i;
        }

        c = 2;
        LinkedHashMap<String, Object> handerMap = new LinkedHashMap();
        handerMap.put("1", "运营中心指标名称");
        handerMap.put("2", "");
        for (int x = 1; x < handerFirst.size(); x++) {
            handerMap.put(String.valueOf(++c), handerFirst.get(x).get("ZZBSZ"));
        }
        mapList.add(handerMap);
        //分离 预警数量/办结比例
        String city = "";
        for (List<LinkedHashMap> contents : contentList) {

            Object zzbsz = contents.get(0).get("ZZBSZ");
            city = String.valueOf(zzbsz);

            List<LinkedHashMap> badyFirstList = contents.subList(1, contents.size() / 2 + 1);
            LinkedHashMap<String, Object> badyFirstMap = new LinkedHashMap();
            badyFirstMap.put("1", city);
            c = 2;
            for (LinkedHashMap map : badyFirstList) {
                badyFirstMap.put(String.valueOf(c++), map.get("ZZBSZ"));
            }
            mapList.add(badyFirstMap);
            List<LinkedHashMap> badyNextList = contents.subList(contents.size() / 2 + 1, contents.size());
            LinkedHashMap<String, Object> badyNextMap = new LinkedHashMap();
            badyNextMap.put("1", city);
            c = 2;
            for (LinkedHashMap map : badyNextList) {
                badyNextMap.put(String.valueOf(c++), map.get("ZZBSZ"));
            }
            mapList.add(badyNextMap);
        }

        // 动态表格 // 默认有一个1行1列的表头
        XWPFTable table2 = docxDocument.createTable();
        table2.setWidthType(TableWidthType.PCT);//设置表格相对宽度
        table2.setTableAlignment(TableRowAlign.CENTER);

        // 遍历数据
        for (i = 0; i < mapList.size(); i++) {
            XWPFTableRow row = null;
            if (i == 0) {
                row = table2.getRow(i);
            } else {
                // 不能使用createRow() 用于简单表，这是复杂表
                row = table2.insertNewTableRow(i);
            }
            Iterator<Map.Entry<String, Object>> iterator = mapList.get(i).entrySet().iterator();
            XWPFTableCell cell = null;
            int j = 0;
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                // 第一行第一列直接获取
                if (i == 0 && j == 0) {
                    cell = row.getCell(j);
                } else {
                    cell = row.createCell();
                }

                CTTcPr tcpr = cell.getCTTc().addNewTcPr();
                CTTblWidth cellw = tcpr.addNewTcW();
                cellw.setType(STTblWidth.DXA);

                // 第一列与其他列宽度
                if (i == 0 && j == 0 || j == 0) {
                    cellw.setW(BigInteger.valueOf(360 * 2));
                } else {
                    cellw.setW(BigInteger.valueOf(360 * 5));
                }
                cell.setText(next.getValue().toString());
                j++;
            }
        }
        // 因为获得的内容列为预警数量和办结比例合并的大小 所以*2
        for (i = 1; i < contentList.size() * 2; ) {
            // 合并行
            mergeCellsVertically(table2, 0, i++, i++);
        }
        //合并列 表头固定合并
        mergeCellsHorizontal(table2, 0, 0, 1);


        ServletOutputStream out = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("demo4.docx", "UTF-8"));
        response.setContentType("application/force-download");
        docxDocument.write(out);
    }

    /**
     * 各单位
     *
     * @throws IOException
     */
    @Override
    public void getGdwByDc(HttpServletResponse response) throws IOException {
        ReportRequest request = new ReportRequest();
        request.setTargetName("threeLevel");
        request.setZtype("yb");
        request.setTime("2023-04");
        List<LinkedHashMap> allList = reportMapper.selectAll(request);
        List<Map<String, Object>> mapList = new ArrayList();
        List<LinkedHashMap> handerFirst = null;
        int i = 0;
        int y = 0;
        //遍历表头
        for (; i < allList.size(); i++) {
            if ("总计".equals(allList.get(i).get("ZZBSZ"))) { //获取到某个字段进行截取
                handerFirst = allList.subList(0, ++i);
                y = i * 2 + 1;
                break;
            }
        }
        //遍历城区所有内容
        List<List<LinkedHashMap>> contentList = new ArrayList<>();
        for (; i < allList.size(); ) {
            List<LinkedHashMap> contentMapList = allList.subList(i, y + i);
            contentList.add(contentMapList);
            i = y + i;
        }

        int c = 2;
        LinkedHashMap<String, Object> handerMap = new LinkedHashMap();
        handerMap.put("1", "运营中心指标名称");
        handerMap.put("2", "");
        for (int x = 1; x < handerFirst.size(); x++) {
            handerMap.put(String.valueOf(++c), handerFirst.get(x).get("ZZBSZ"));
        }
        mapList.add(handerMap);

        //分离 预警数量/办结比例
        String city = "";
        for (List<LinkedHashMap> contents : contentList) {

            Object zzbsz = contents.get(0).get("ZZBSZ");
            city = String.valueOf(zzbsz);

            List<LinkedHashMap> badyFirstList = contents.subList(1, contents.size() / 2 + 1);
            LinkedHashMap<String, Object> badyFirstMap = new LinkedHashMap();
            badyFirstMap.put("1", city);
            c = 2;
            for (LinkedHashMap map : badyFirstList) {
                badyFirstMap.put(String.valueOf(c++), map.get("ZZBSZ"));
            }
            mapList.add(badyFirstMap);
            List<LinkedHashMap> badyNextList = contents.subList(contents.size() / 2 + 1, contents.size());
            LinkedHashMap<String, Object> badyNextMap = new LinkedHashMap();
            badyNextMap.put("1", city);
            c = 2;
            for (LinkedHashMap map : badyNextList) {
                badyNextMap.put(String.valueOf(c++), map.get("ZZBSZ"));
            }
            mapList.add(badyNextMap);
        }


        //创建word对象
        XWPFDocument docxDocument = new XWPFDocument();
        // 动态表格 // 默认有一个1行1列的表头
        XWPFTable table = docxDocument.createTable();
        //table.setWidth("95%");// 设置宽度
        table.setWidthType(TableWidthType.PCT);//设置表格相对宽度
        table.setTableAlignment(TableRowAlign.CENTER);
        // 遍历数据
        for (i = 0; i < mapList.size(); i++) {
            XWPFTableRow row = null;
            if (i == 0) {
                row = table.getRow(i);
            } else {
                // 不能使用createRow() 用于简单表，这是复杂表
                row = table.insertNewTableRow(i);
            }
            Iterator<Map.Entry<String, Object>> iterator = mapList.get(i).entrySet().iterator();
            XWPFTableCell cell = null;
            int j = 0;
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                // 第一行第一列直接获取
                if (i == 0 && j == 0) {
                    cell = row.getCell(j);
                } else {
                    cell = row.createCell();
                }
                CTTcPr tcpr = cell.getCTTc().addNewTcPr();
                CTTblWidth cellw = tcpr.addNewTcW();
                cellw.setType(STTblWidth.DXA);

                // 第一列与其他列宽度
                if (j == 0) {
                    cellw.setW(BigInteger.valueOf(360 * 17));
                } else {
                    cellw.setW(BigInteger.valueOf(360 * 5));
                }
                cell.setText(next.getValue().toString());
                j++;
            }
        }

        // 因为获得的内容列为预警数量和办结比例合并的大小 所以*2
        for (i = 1; i < contentList.size() * 2; ) {
            // 合并行
            mergeCellsVertically(table, 0, i++, i++);
        }

        //合并列 表头固定合并
        mergeCellsHorizontal(table, 0, 0, 1);

        ServletOutputStream out = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("demo5.docx", "UTF-8"));
        response.setContentType("application/force-download");
        docxDocument.write(out);
    }



    // word跨列合并单元格
    public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    // word跨行并单元格
    public void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }


}
