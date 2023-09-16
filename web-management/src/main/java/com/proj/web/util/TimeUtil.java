package com.proj.web.util;

import com.proj.model.bo.request.ReportRequest;
import lombok.SneakyThrows;
import org.assertj.core.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 */
public class TimeUtil {
    @SneakyThrows
    public static void main(String[] args) {
        YearMonth now = YearMonth.parse("2023-02");
        System.out.println(lastMonth());
    }


    /**
     * 返回上月
     * @return
     * @throws ParseException
     */
    public static ReportRequest lastMonth() throws ParseException {
        ReportRequest request = new ReportRequest();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(new Date());
        calendar.setTime(format.parse(format1));
        int thisMonth = calendar.get(Calendar.DAY_OF_MONTH);//当前天数
        calendar.add(Calendar.MONTH, -1);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//上一月天数
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;//上月
        if (month < 10) {
            request.setYear(String.valueOf(year));
            request.setMonth("0" + month);
            request.setTime(year + "-0" + month); //2023-01
            request.setStartTime(year + "-0" + month + "-01"); //2023-01-01
            request.setEndTime(year + "-0" + month + "-" + day); //2023-01-31
        } else {
            request.setYear(String.valueOf(year));
            request.setMonth(String.valueOf(month));
            request.setTime(year + "-" + month); //2023-10
            request.setStartTime(year + "-" + month + "-01"); //2023-10-01
            request.setEndTime(year + "-" + month + "-" + day); //2023-10-31
        }
        return request;
    }

    public static Integer setNeedDate(String str){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d=null;
        try {
            d=format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(d);
        int w=calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (w==0) w=7;
        return w;
    }

    /**
     * 返回上一年的时间
     *
     * @param year 2022-01-01
     * @return 2021-01-01
     */
    public static String lastTime(String year) {
        if (!year.isEmpty()) {
            Integer integer = Integer.valueOf(year.substring(0, 4));
            String s = String.valueOf(integer - 1);
            return s + year.substring(4);
        }
        return null;
    }

    /**
     * 返回年
     *
     * @param year 2022-01-01
     * @return 2022
     */
    public static String getyear(String year) {
        if (!year.isEmpty()) {
            Integer integer = Integer.valueOf(year.substring(0, 4));
            String s = String.valueOf(integer);
            return s;
        }
        return null;
    }

    /**
     * 返回当前月份
     *
     * @param year 2022-01-01
     * @return 1
     */
    public static Integer getMonth(String year) {
        if (!year.isEmpty()) {
            String month = year.substring(5, 7);
            return Integer.valueOf(month);
        }
        return null;
    }

    /**
     *
     * 获取当前月天数
     * @param year 2022-02
     * @return 28
     */
    public static Integer getDaysByYear(String year) {
        YearMonth yearMonth = YearMonth.parse(year);
        int day = yearMonth.lengthOfMonth();
        return day;
    }

    /**
     * 获取当前月天数
     *
     * @param year 2022-02-28
     * @return 28
     */
    public static Integer getDaysByTime(String year) {
        if (!year.isEmpty()) {
            String month = year.substring(8, 10);
            return Integer.valueOf(month);
        }
        return null;
    }


}
