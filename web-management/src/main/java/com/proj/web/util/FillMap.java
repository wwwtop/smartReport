package com.proj.web.util;

import com.proj.model.vo.UndulationVO;
import com.proj.web.mapper.MainBadyMapper;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FillMap {

    public static void fillMissingDates(Map<String, Integer> map) throws ParseException {
        // 构造日期格式化对象
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        // 获取当前月份和年份
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        // 计算当前月份的第一天和最后一天
        String firstDayOfMonth = String.format("%04d-%02d-%02d", year, month, 1);
        Date firstDate = df.parse(firstDayOfMonth);
        cal.setTime(firstDate);
        int lastDayOfMonth = cal.getActualMaximum(Calendar.DATE);

        // 补齐缺失的日期
        long prevValue = -1;
        for (int i = 1; i <= lastDayOfMonth; i++) {
            String dateStr = String.format("%04d-%02d-%02d", year, month, i);
            if (!map.containsKey(dateStr)) {
                long currTime = df.parse(dateStr).getTime();
                if (prevValue >= 0) {
                    map.put(dateStr, (int) prevValue);
                } else {
                    map.put(dateStr, 0);
                }
            } else {
                prevValue = map.get(dateStr);
            }
        }
    }


    public static void main(String[] args) throws ParseException {
        MainBadyMapper mainBadyMapper;
        List<UndulationVO> vos1 = new LinkedList<>();
        UndulationVO undulationVO = new UndulationVO();
        undulationVO.setRawmaterialdate("2023-01-01");
        undulationVO.setRawmaterialprice("10");
        vos1.add(undulationVO);
        UndulationVO undulationVO1 = new UndulationVO();
        undulationVO1.setRawmaterialdate("2023-01-03");
        undulationVO1.setRawmaterialprice("20");
        vos1.add(undulationVO1);
        UndulationVO undulationVO2 = new UndulationVO();
        undulationVO2.setRawmaterialdate("2023-01-05");
        undulationVO2.setRawmaterialprice("30");
        vos1.add(undulationVO2);
        System.out.println(vos1);
        Map<String, Integer> map = new HashMap<>();
        map.put("2022-02-01", 10);
        map.put("2022-02-03", 20);
        map.put("2022-02-05", 30);
        System.out.println(map);
        fillMissingDates(map);
        System.out.println(map);
    }
}


