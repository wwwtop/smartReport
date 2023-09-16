package com.proj.web.util;

import java.math.BigDecimal;

/**
 * 为避免冗余 使用此方法
 */
public class QuartUtil {
    public static void main(String[] args) {
        String is = "26.12511";
        System.out.println(is.substring(1));
    }

    private final static String TB = "同比";

    public static Object off(Object o) {
        return o == null ? 0 : o;
    }

    /**
     * 判断同比增长/下降 正文
     *
     * @param bigDecimal
     * @return
     */
    public static String udByMain(BigDecimal bigDecimal, String tb) {
        if (TB.equals(tb)) {
            if (bigDecimal != null && bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
                return "上涨" + bigDecimal+"%";
            }
            if (bigDecimal != null && bigDecimal.compareTo(BigDecimal.ZERO) == -1) {
                return "下跌" + bigDecimal.abs()+"%";
            }
        } else {
            if (bigDecimal != null && bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
                return "上涨" + bigDecimal+"%";
            }
            if (bigDecimal != null && bigDecimal.compareTo(BigDecimal.ZERO) == -1) {
                return "下跌" + bigDecimal.abs()+"%";
            }
        }
        return "无变化";
    }


    /**
     * 判断同比增长/下降 表格
     *
     * @param bigDecimal
     * @return
     */
    public static String udByTable(BigDecimal bigDecimal) {
        if (bigDecimal != null && bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
            return "↑";
        }
        if (bigDecimal != null && bigDecimal.compareTo(BigDecimal.ZERO) == -1) {
            return "↓";
        }
        return "-";
    }

    /**
     * 判断同比增长/下降 表格
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String contrastByTable(String str1, String str2) {
        try {
            BigDecimal bigDecimal1 = new BigDecimal(str1);
            BigDecimal bigDecimal2 = new BigDecimal(str2);
            if (bigDecimal1 != null && bigDecimal2 != null && bigDecimal1.compareTo(bigDecimal2) == 1) {
                return "↑";
            }
            if (bigDecimal1 != null && bigDecimal2 != null && bigDecimal1.compareTo(bigDecimal2) == -1) {
                return "↓";
            }
        }catch (Exception e){
            return "-";
        }

        return "-";
    }
}
