package com.proj.core.utils;

import java.time.LocalDateTime;

/**
 * 日期时间处理辅助工具类
 *
 * @author dong.ning
 */
public class DateTimeUtil {

    /**
     * 根据某时间参数为基准，进行时间某周期的测算。
     * <p>
     * 得出前后2个时间值（开始时间、结束时间）。如果测算失败，则返回null
     * <p>
     * <p></p>
     * <b>具体的处理类型号定义：</b>
     * <p>
     * <b>1xx---天</b>
     * <p>
     * 101-某天，开始时间和结束时间
     * <p>
     * 102-某天，前一天的开始时间和结束时间
     * <p>
     * <p></p>
     * <b>2xx---周</b>
     * <p>
     * 201-某天，这周的开始时间和结束时间
     * <p>
     * 202-某天，上周的开始时间和结束时间
     * <p>
     * 203-近一周
     * <p>
     * <p></p>
     * <b>3xx---月</b>
     * <p>
     * 301-某天，这月的开始时间和结束时间
     * <p>
     * 302-某天，上个月的开始时间和结束时间
     * <p>
     * 303-近一个月
     * <p>
     * <p></p>
     * <b>4xx---季度</b>
     * <p>
     * 401-某天，当前季度的开始时间和结束时间
     * <p>
     * 402-某天，上个季度的开始时间和结束时间
     * <p>
     * 411-某天的当前年，第一季度的开始时间和结束时间
     * <p>
     * 412-某天的当前年，第二季度的开始时间和结束时间
     * <p>
     * 413-某天的当前年，第三季度的开始时间和结束时间
     * <p>
     * 414-某天的当前年，第四季度的开始时间和结束时间
     * <p>
     * <p></p>
     * <b>5xx---年</b>
     * <p>
     * 501-某年，开始时间和结束时间
     *
     * @param queryType     处理类型号
     * @param localDateTime 当前传入参数的时间，null=当前时间（现在时间）
     * @return 计算之后的，前后2个时间值（开始时间、结束时间）。如果测算失败，则返回null
     */
    public static LocalDateTime[] getDatetimeByAnyTime(int queryType, LocalDateTime localDateTime) {
        LocalDateTime[] localDateTimeArray = new LocalDateTime[2];
        if (null == localDateTime) {
            localDateTime = LocalDateTime.now();
        }

        if (queryType <= 0) {
            return null;
        }

        //去处3位数的类型号的第一位，舍弃余数，直接取整。取出前缀
        int prefix = queryType / 100;
        DateTimeCalcTool calcTool = new DateTimeCalcTool(localDateTime);
        switch (prefix) {
            case 1:
                localDateTimeArray = calcTool.getCalcDay(queryType);
                break;
            case 2:
                localDateTimeArray = calcTool.getCalcWeek(queryType);
                break;
            case 3:
                localDateTimeArray = calcTool.getCalcMonth(queryType);
                break;
            case 4:
                localDateTimeArray = calcTool.getCalcQuarterly(queryType);
                break;
            case 5:
                localDateTimeArray = calcTool.getCalcYear(queryType);
                break;

            default:
                localDateTimeArray = null;
                break;
        }
        return localDateTimeArray;
    }

    /**
     * 获取季度序号，1=第一季度，2=第二季度，3=第三季度，4=第四季度
     *
     * @param localDateTime
     * @return
     */
    public static int getQuarterly(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            localDateTime = LocalDateTime.now();
        }
        int month = localDateTime.getMonthValue();
        return ((month - 1) / 3) + 1;
    }

    /**
     * 获取季度的起始月份
     *
     * @param quarterly
     * @return
     */
    public static int getQuarterlyStartMonth(int quarterly) {
        if (!(quarterly >= 1 && quarterly <= 4)) {
            System.out.println("季度参数错误");
        }
        return ((quarterly - 1) * 3) + 1;
    }


    /**
     * 日期时间内部处理辅助工具类（只允许在DateTimeUtil类中使用，内部调用）
     *
     * @author dong.ning
     */
    private static class DateTimeCalcTool {

        /**
         * 季度处理类型号的开始号，季度-当前
         */
        private final int CALC_TYPE_QUARTERLY_CURRENT = 410;

        /**
         * 构造初始化的时候，天
         */
        private int currentDay;

        /**
         * 构造初始化的时候，周
         */
        private int currentWeek;

        /**
         * 构造初始化的时候，月份
         */
        private int currentMonth;

        /**
         * 构造初始化的时候，季度
         */
        private int currentQuarterly;

        /**
         * 构造初始化的时候，年份
         */
        private int currentYear;

        /**
         * 初始化入参时间
         */
        private LocalDateTime localDateTimeTmpCurrent;


        /**
         * 构造初始化
         *
         * @param localDateTime
         */
        public DateTimeCalcTool(LocalDateTime localDateTime) {
            this.currentDay = localDateTime.getDayOfMonth();
            this.currentWeek = localDateTime.getDayOfWeek().getValue();
            this.currentMonth = localDateTime.getMonthValue();
            this.currentQuarterly = getQuarterly(localDateTime);
            this.currentYear = localDateTime.getYear();

            this.localDateTimeTmpCurrent = localDateTime;
        }

        /**
         * 获取仅初始化，年月日，日期的日期时间处理对象
         *
         * @param year
         * @param month
         * @param day
         * @return
         */
        private LocalDateTime getLocalDateTimeOfJustDate(int year, int month, int day) {
            return LocalDateTime.of(year, month, day
                    , 0, 0, 0);
        }

        /**
         * 按类型号，处理，天
         *
         * @param queryType
         * @return
         */
        public LocalDateTime[] getCalcDay(int queryType) {
            LocalDateTime localDateTimeTmp = LocalDateTime.now();
            LocalDateTime[] localDateTimeArray = new LocalDateTime[2];
            switch (queryType) {
                case 101:
                    localDateTimeTmp = this.getLocalDateTimeOfJustDate(this.currentYear, this.currentMonth, this.currentDay);
                    localDateTimeArray[0] = localDateTimeTmp;
                    localDateTimeArray[1] = localDateTimeTmp.plusDays(1);
                    break;
                case 102:
                    localDateTimeTmp = this.getLocalDateTimeOfJustDate(this.currentYear, this.currentMonth, this.currentDay)
                            .minusDays(1);
                    localDateTimeArray[0] = localDateTimeTmp;
                    localDateTimeArray[1] = localDateTimeTmp.plusDays(1);
                    break;
                default:
                    localDateTimeArray = null;
                    break;
            }
            return localDateTimeArray;
        }

        /**
         * 按类型号，处理，周
         *
         * @param queryType
         * @return
         */
        public LocalDateTime[] getCalcWeek(int queryType) {
            LocalDateTime localDateTimeTmp = LocalDateTime.now();
            LocalDateTime[] localDateTimeArray = new LocalDateTime[2];
            switch (queryType) {
                case 201:
                    localDateTimeTmp = this.getLocalDateTimeOfJustDate(this.currentYear, this.currentMonth, this.currentDay)
                            .minusDays(this.currentWeek - 1);
                    localDateTimeArray[0] = localDateTimeTmp;
                    localDateTimeArray[1] = localDateTimeTmp.plusDays(7);
                    break;
                case 202:
                    localDateTimeTmp = this.getLocalDateTimeOfJustDate(this.currentYear, this.currentMonth, this.currentDay)
                            .minusDays(this.currentWeek - 1)
                            .minusDays(7);
                    localDateTimeArray[0] = localDateTimeTmp;
                    localDateTimeArray[1] = localDateTimeTmp.plusDays(7);
                    break;
                case 203:
                    localDateTimeTmp = this.localDateTimeTmpCurrent;
                    localDateTimeArray[0] = localDateTimeTmp.minusDays(7);
                    localDateTimeArray[1] = localDateTimeTmp;
                    break;
                default:
                    localDateTimeArray = null;
                    break;
            }
            return localDateTimeArray;
        }

        /**
         * 按类型号，处理，月
         *
         * @param queryType
         * @return
         */
        public LocalDateTime[] getCalcMonth(int queryType) {
            LocalDateTime localDateTimeTmp = LocalDateTime.now();
            LocalDateTime[] localDateTimeArray = new LocalDateTime[2];
            switch (queryType) {
                case 301:
                    localDateTimeTmp = this.getLocalDateTimeOfJustDate(this.currentYear, this.currentMonth, 1);
                    localDateTimeArray[0] = localDateTimeTmp;
                    localDateTimeArray[1] = localDateTimeTmp.plusMonths(1).minusDays(1);
                    break;
                case 302:
                    localDateTimeTmp = this.getLocalDateTimeOfJustDate(this.currentYear, this.currentMonth, 1)
                            .minusMonths(1);
                    localDateTimeArray[0] = localDateTimeTmp;
                    localDateTimeArray[1] = localDateTimeTmp.plusMonths(1).minusDays(1);
                    break;
                case 303:
                    localDateTimeTmp = this.localDateTimeTmpCurrent;
                    localDateTimeArray[0] = localDateTimeTmp.minusMonths(1);
                    localDateTimeArray[1] = localDateTimeTmp;
                    break;
                default:
                    localDateTimeArray = null;
                    break;
            }
            return localDateTimeArray;
        }

        /**
         * 按类型号，处理，季度
         *
         * @param queryType
         * @return
         */
        public LocalDateTime[] getCalcQuarterly(int queryType) {
            int quarterlyNew = 0;

            LocalDateTime localDateTimeTmp = LocalDateTime.now();
            LocalDateTime[] localDateTimeArray = new LocalDateTime[2];

            //根据当前入参的处理类型号，减去已定义的当前时间处理季度类型号
            quarterlyNew = queryType - this.CALC_TYPE_QUARTERLY_CURRENT;
            if (quarterlyNew <= 10) {
                localDateTimeTmp = this.getLocalDateTimeOfJustDate(this.currentYear, getQuarterlyStartMonth(quarterlyNew), 1);
                localDateTimeArray[0] = localDateTimeTmp;
                localDateTimeArray[1] = localDateTimeTmp.plusMonths(3).minusDays(1);
                return localDateTimeArray;
            }

            int quarterly = getQuarterly(this.localDateTimeTmpCurrent);

            //如果不和已定义的处理季度特殊处理类型号匹配，则可能是其他情况
            switch (queryType) {
                case 401:
                    localDateTimeTmp = this.getLocalDateTimeOfJustDate(this.currentYear, getQuarterlyStartMonth(quarterly), 1);
                    localDateTimeArray[0] = localDateTimeTmp;
                    localDateTimeArray[1] = localDateTimeTmp.plusMonths(3).minusDays(1);
                    break;
                case 402:
                    localDateTimeTmp = this.getLocalDateTimeOfJustDate(this.currentYear, getQuarterlyStartMonth(quarterly), 1)
                            .minusMonths(3);
                    localDateTimeArray[0] = localDateTimeTmp;
                    localDateTimeArray[1] = localDateTimeTmp.plusMonths(3).minusDays(1);
                    break;
                default:
                    localDateTimeArray = null;
                    break;
            }
            return localDateTimeArray;
        }

        /**
         * 按类型号，处理，年
         *
         * @param queryType
         * @return
         */
        public LocalDateTime[] getCalcYear(int queryType) {
            LocalDateTime localDateTimeTmp = LocalDateTime.now();
            LocalDateTime[] localDateTimeArray = new LocalDateTime[2];
            switch (queryType) {
                case 501:
                    localDateTimeTmp = this.getLocalDateTimeOfJustDate(this.currentYear, 1, 1);
                    localDateTimeArray[0] = localDateTimeTmp;
                    localDateTimeArray[1] = localDateTimeTmp.plusYears(1);
                    break;
                default:
                    localDateTimeArray = null;
                    break;
            }
            return localDateTimeArray;
        }
    }

}
