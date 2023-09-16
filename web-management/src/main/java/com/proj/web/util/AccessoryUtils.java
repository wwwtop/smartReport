package com.proj.web.util;

import com.proj.model.vo.BasicDataStatisticsVO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;

import static com.proj.web.util.BigDecimalUtil.getMul;

/**
 * 表格数据处理工具
 */
public class AccessoryUtils {
    /**
     * 返回金额
     * @param mapNew
     * @param typeFind
     * @return
     */
    public static BigDecimal getActualValueByP(List<LinkedHashMap> mapNew , String typeFind){
        if (CollectionUtils.isEmpty(mapNew)){
            return BigDecimal.ZERO;
        }
        for (int i=0;i<mapNew.size();i++){
            String type = mapNew.get(i).get("type").toString();
            if (typeFind.equals(type)){
                return new BigDecimal(mapNew.get(i).get("p").toString());
            }
        }
        return BigDecimal.ZERO;
    }
    /**
     * 返回数量
     * @param mapNew
     * @param typeFind
     * @return
     */
    public static Integer getActualValueByN(List<LinkedHashMap> mapNew , String typeFind){
        if (CollectionUtils.isEmpty(mapNew)){
            return 0;
        }
        for (int i=0;i<mapNew.size();i++){
            String type = mapNew.get(i).get("type").toString();
            if (typeFind.equals(type)){
                Double dou = new Double(mapNew.get(i).get("n").toString());
                return (int) Math.round(dou);
            }
        }
        return 0;
    }

    public static String getActualValueByRatePrice(List<LinkedHashMap> mapNew , String typeFind){
        if (CollectionUtils.isEmpty(mapNew)){
            return "0";
        }
        for (int i=0;i<mapNew.size();i++){
            String type = mapNew.get(i).get("type").toString();
            if (typeFind.equals(type)){
                BigDecimal rate = new BigDecimal(mapNew.get(i).get("rate").toString());
                //判断分母是否为0 （约定分母为0返回数值为-2）
                if (rate.compareTo(BigDecimal.valueOf(-2))==0){
                    return "-";
                }
                return BigDecimalUtil.getExchangeRate(rate,10000);
            }
        }
        return "0";
    }


    /**
     * 只限于招标
     * @param mapNew
     * @param typeFind
     * @return
     */
    public static String getActualValueByRate1(List<LinkedHashMap> mapNew , String typeFind){
        if (CollectionUtils.isEmpty(mapNew)){
            return "0";
        }
        for (int i=0;i<mapNew.size();i++){
            String type = mapNew.get(i).get("type").toString();
            if (typeFind.equals(type)){
                BigDecimal rate = new BigDecimal(mapNew.get(i).get("rate").toString());
                //判断分母是否为0 （约定分母为0返回数值为-2）
                if (rate.compareTo(BigDecimal.valueOf(-2))==0){
                    return "-";
                }
                BigDecimal mul = getMul(rate, new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
                String format = format(mul);
                return format;
            }
        }
        return "0";
    }


    public static String getActualValueByRate(List<LinkedHashMap> mapNew , String typeFind){
        if (CollectionUtils.isEmpty(mapNew)){
            return "0";
        }
        for (int i=0;i<mapNew.size();i++){
            String type = mapNew.get(i).get("type").toString();
            if (typeFind.equals(type)){
                BigDecimal rate = new BigDecimal(mapNew.get(i).get("rate").toString());
                //判断分母是否为0 （约定分母为0返回数值为-2）
                if (rate.compareTo(BigDecimal.valueOf(-2))==0){
                    return "-";
                }
                BigDecimal mul = getMul(rate, new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
                String format = format(mul);
                return format;
            }
        }
        return "0";
    }

    public static BigDecimal getActualValueByRateBigDecimal(List<LinkedHashMap> mapNew , String typeFind){
        if (CollectionUtils.isEmpty(mapNew)){
            return BigDecimal.ZERO;
        }
        for (int i=0;i<mapNew.size();i++){
            String type = mapNew.get(i).get("type").toString();
            if (typeFind.equals(type)){
                BigDecimal rate = new BigDecimal(mapNew.get(i).get("rate").toString());
                BigDecimal mul = getMul(rate, new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
                String format = format(mul);
                mul=NumberUtils.createBigDecimal(format);
                return mul;
            }
        }
        return BigDecimal.ZERO;
    }


    public static String getActualValueByRate2(List<LinkedHashMap> mapNew , String typeFind){
        if (CollectionUtils.isEmpty(mapNew)){
            return "0";
        }
        for (int i=0;i<mapNew.size();i++){
            String type = mapNew.get(i).get("type").toString();
            if (typeFind.equals(type)){
                BigDecimal rate = new BigDecimal(mapNew.get(i).get("rate").toString());
                //判断分母是否为0 （约定分母为0返回数值为-2）
                if (rate.compareTo(BigDecimal.valueOf(-2))==0){
                    return "-";
                }
//                BigDecimal mul = getMul(rate, new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal mul = new BigDecimal(mapNew.get(i).get("rate").toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                String format = format(mul);
                return format;
            }
        }
        return "0";
    }



    public static String getYoy(BigDecimal bigDecimal){
        BigDecimal mul = bigDecimal.multiply(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        String yoy = format(mul)+"%";
        if (yoy.equals("100.00%")){
            yoy="100%";
        }
        if (yoy.equals("0.00%")){
            yoy="0%";
        }
        return yoy;
    }


    /**
     * 保留两位小数或者直接显示整数
     *
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    private static final DecimalFormat format=new DecimalFormat("#.##");

    public static String format(BigDecimal bigDecimal1) {
        if (bigDecimal1.scale() == 0) {
            return String.valueOf(bigDecimal1.setScale(0));
        }else {
            return format.format(bigDecimal1);
        }
    }



}
