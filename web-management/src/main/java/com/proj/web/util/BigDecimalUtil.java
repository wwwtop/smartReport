package com.proj.web.util;


import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigDecimalUtil {
	public static void main(String[] args) {
		Integer integer;
		Integer num;//数量
		Integer allNum;//总数
		Integer group;//组
		String str = "basic_098";
		System.out.println("" + str.indexOf("_"));
		String substring = str.substring(str.indexOf("_") + 1, str.length());
		System.out.println(substring);
		Integer index = Integer.valueOf(substring);
		if (index % 7 != 0) {
			allNum = index / 7 + 1;
		} else {
			allNum = index / 7;//32
		}

		System.out.println(allNum);
		num = allNum % 8;

		if (num != 0) {
			group = allNum / 8 + 1;
		} else {
			group = allNum / 8 ;
			num = 8;
		}

//        integer2=integer2/8;
		System.out.println(group + "||" + num);
	}

    /*
    bigDecimal2.compareTo(BigDecimal.ZERO)==0 等于0
    bigDecimal2.compareTo(BigDecimal.ZERO)==-1 小于0
    bigDecimal2.compareTo(BigDecimal.ZERO)==1 大于0
    bigDecimal2.setScale(2, RoundingMode.HALF_UP); 四舍五入 保留两位小数
     */

	/**
	 * 汇率转换 如 转换 12000元 1.2万元
	 *
	 * @param decimal      金额 12000
	 * @param exchangeRate 汇率 10000
	 * @return 1.20
	 */
	public static String getExchangeRate(BigDecimal decimal, Integer exchangeRate) {
		if (decimal != null) {
			BigDecimal divide = decimal.divide(new BigDecimal(exchangeRate));
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
			String num = decimalFormat.format(divide);
			divide = NumberUtils.createBigDecimal(num);
			String format = format(divide);
			return format;
		}
		return "0.00";
	}

	/**
	 * （1-2）/2*100%
	 * 同比计算(减 除 乘)
	 *
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return
	 */
	public static BigDecimal getYoy(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
		BigDecimal resultNumber = BigDecimal.valueOf(0);

		if (bigDecimal1 != null && bigDecimal2 != null) {
			if (bigDecimal2.compareTo(BigDecimal.ZERO) == 0) {
				//若为0 抛异常
//                try {
//                    throw new ProjException(Constants.API_TIP_ERR_BY_ZERO);
//                } catch (ProjException e) {
//                    e.printStackTrace();
//                }
			} else {
				resultNumber = bigDecimal1.subtract(bigDecimal2).divide(bigDecimal2, 6, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
				String format = format(resultNumber);
				resultNumber = NumberUtils.createBigDecimal(format);
			}
		}
		return resultNumber;
	}

	/**
	 * 除法运算
	 *
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return
	 */
	public static BigDecimal getDiv(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
		BigDecimal resultNumber = BigDecimal.valueOf(0);
		if (bigDecimal1 == null || bigDecimal1.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.valueOf(0);
		}
		if (bigDecimal2 == null || bigDecimal2.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.valueOf(0);
		}
		resultNumber = bigDecimal1.divide(bigDecimal2, 2, RoundingMode.HALF_UP);
		String format = format(resultNumber);
		resultNumber = NumberUtils.createBigDecimal(format);
		return resultNumber;
	}


	/**
	 * 除法运算
	 *
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return
	 */
	public static String getDivs(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
		String resultNumber = "0";
		if (bigDecimal1 == null || bigDecimal1.compareTo(BigDecimal.ZERO) == 0) {
			return resultNumber;
		}
		if (bigDecimal2 == null || bigDecimal2.compareTo(BigDecimal.ZERO) == 0) {
			return resultNumber;
		}
		BigDecimal multiply = bigDecimal1.divide(bigDecimal2, 6, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
		resultNumber = format(multiply) + "%";
		return resultNumber;
	}

	/**
	 * 乘法运算
	 *
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return
	 */
	public static BigDecimal getMul(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
		BigDecimal resultNumber = BigDecimal.valueOf(0);
		if (bigDecimal1 == null || bigDecimal1.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.valueOf(0);
		}
		if (bigDecimal2 == null || bigDecimal2.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.valueOf(0);
		}
		resultNumber = bigDecimal1.multiply(bigDecimal2);
		String format = format(resultNumber);
		resultNumber = NumberUtils.createBigDecimal(format);
		return resultNumber;
	}

	/**
	 * 减法运算
	 *
	 * @param bigDecimal1 第一个数
	 * @param bigDecimal2 第二个数
	 * @return 差(可为负数)
	 */
	public static BigDecimal getSub(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
		if (bigDecimal1 == null) {
			return bigDecimal2.negate();//取反
		}
		if (bigDecimal2 == null) {
			return bigDecimal1;
		}
		if (bigDecimal1 == null && bigDecimal2 == null) {
			return BigDecimal.valueOf(0);
		}
		return bigDecimal1.subtract(bigDecimal2);
	}

	/**
	 * 加法运算
	 *
	 * @param bigDecimal1 第一个数
	 * @param bigDecimal2 第二个数
	 * @return
	 */
	public static BigDecimal getAdd(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
		if (bigDecimal1 == null && bigDecimal2 == null) {
			return BigDecimal.valueOf(0);
		}
		if (bigDecimal1 == null) {
			return bigDecimal2;
		}
		if (bigDecimal2 == null) {
			return bigDecimal1;
		}
		return bigDecimal1.add(bigDecimal2);
	}


	/**
	 * 特例 加法运算
	 *
	 * @param bigDecimal1 第一个数
	 * @param bigDecimal2 第二个数
	 * @return
	 */
	public static BigDecimal getAdd1(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
		if (bigDecimal1 == null && bigDecimal2 == null) {
			return BigDecimal.valueOf(0);
		}
		if (bigDecimal1 == null) {
			return bigDecimal2;
		}
		if (bigDecimal2 == null) {
			return bigDecimal1;
		}
		BigDecimal resultNumber = bigDecimal1.add(bigDecimal2).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
		String format = format(resultNumber);
		resultNumber = NumberUtils.createBigDecimal(format);
		return resultNumber;
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
