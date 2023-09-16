package com.proj.web.service.impl;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.second.SecondMainBadyVO;
import com.proj.web.entity.MainBadySecond2PO;
import com.proj.web.entity.MainBadySecondPO;
import com.proj.web.mapper.MainBadySecondMapper;
import com.proj.web.service.MainBadySecondService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import static com.proj.web.util.ReflectionExample.*;

/**
 * 正文
 *
 * @author dong.ning
 */
@Service
public class MainBadySecondServiceImpl implements MainBadySecondService {

	@Resource
	MainBadySecondMapper mainBadySecondMapper;


	/**
	 * 获取正文
	 *
	 * @param request
	 * @return
	 */
	@Override
	public Map<String, Object> getsecondMainBadyList(ReportRequest request) {
		SecondMainBadyVO secondMainBady = new SecondMainBadyVO();
		SecondMainBadyVO badyVO = getSecondMainBadyEntity(request);//获取正文数据集
		List<String> fields = printFields(secondMainBady);
		List<String> values = printValues(badyVO);
		for (int i = 0; i < fields.size(); i++) {
			setFields(secondMainBady, fields.get(i), values.get(i));
		}
		List<String> list = printValues(secondMainBady);
		System.out.println(list);

		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			map.put("secondMainBady" + (i + 1), list.get(i));
		}
		return map;
	}

	/**
	 * 获取正文数据
	 *
	 * @param
	 * @return
	 */

	public SecondMainBadyVO getSecondMainBadyEntity(ReportRequest request) {
		SecondMainBadyVO secondMainBady = new SecondMainBadyVO();
		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos1 = getsecondMainBadyModuleList(0, "ESC预警问题整改率",request);
		List<MainBadySecond2PO> group1 = getGroup(pos1);
		String max1 = getThree(pos1.get("max"));//前三
		String min1 = getThree(pos1.get("min"));//后三
		secondMainBady.setA_a_pjzgl1(getSumRate(group1));//本月，各单位平均整改率为{a_a_pjzgl1}%。
		secondMainBady.setA_a_gs1(max1);//其中{a_a_gs1}等
		secondMainBady.setA_a_j("多");//{a_a_j}家公司
		secondMainBady.setA_a_pjzgl2(getMixThreeRate(group1, max1));//整改率均为{a_a_pjzgl2}%；
		secondMainBady.setA_a_gs2(min1);//{a_a_gs2}公司正确率较低，
		secondMainBady.setA_a_dy(getMixThreeRate(group1, min1));//均低于{a_a_dy}%。

		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos2 = getsecondMainBadyModuleList(1, "盘活利库任务完成率",request);
		List<MainBadySecond2PO> group2 = getGroup(pos2);
		String max2 = getThree(pos2.get("max"));//前三
		String min2 = getThree(pos2.get("min"));//后三
		secondMainBady.setA_b_rwwcl1(getSumRate(group2));//截止2月份，公司盘活利库任务完成率为{a_b_rwwcl1}%，
		secondMainBady.setA_b_gs1(max2);// 其中{a_b_gs1}公司推进较快，
		secondMainBady.setA_a_rwwcl2(getMixThreeRate(group2, max2));// 任务完成率在{a_a_rwwcl2}%以上；
		secondMainBady.setA_b_gs2(min2);// {a_b_gs2}公司进度较慢，需加大推进力度。

		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos3 = getsecondMainBadyModuleList(2, "计划申报正确率", request);
		List<MainBadySecond2PO> group3 = getGroup(pos3);
		String max3 = getThree(pos3.get("max"));//前三
		String min3 = getThree(pos3.get("min"));//后三
		secondMainBady.setA_c_pjzql(getSumRate(group3));//截止2月份，各单位平均正确率为{a_c_pjzql}%。
		secondMainBady.setA_c_gs1(max3);// 其中{a_c_gs1}公司正确率较高
		secondMainBady.setA_c_zql(getMixThreeRate(group3, max3));// 为{a_c_zql}%；
		secondMainBady.setA_c_gs2(min3);// {a_c_gs2}公司正确率较低，
		secondMainBady.setA_c_dy(getMixThreeRate(group3, min3));// 均低于{a_c_dy}%。

		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos4 = getsecondMainBadyModuleList(3, "报废物资处置计划申报规范率",request);
		List<MainBadySecond2PO> group4 = getGroup(pos4);
		String max4 = getThree(pos4.get("max"));//前三
		String min4 = getThree(pos4.get("min"));//后三
		secondMainBady.setA_d_yjgfl(getSumRate(group4));//截止2月份，各单位平均规范率为{a_d_yjgfl}%。
		secondMainBady.setA_d_gs1(max4);// 其中{a_d_gs1}公司
		secondMainBady.setA_d_w(getMixThreeRate(group4, max4));// 为{a_d_w}%；
		secondMainBady.setA_d_gs2(min4);// {a_d_gs2}公司
		secondMainBady.setA_d_dy(getMixThreeRate(group4, min4));// 低于{a_d_dy}%。

		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos5 = getsecondMainBadyModuleList(4, "一次采购成功率",request);
		List<MainBadySecond2PO> group5 = getGroup(pos5);
		String max5 = getThree(pos5.get("max"));//前三
		String min5 = getThree(pos5.get("min"));//后三
		secondMainBady.setA_e_yjcgl(getSumRate(group5));//截止2月份，各单位平均成功率{a_e_yjcgl}%。
		secondMainBady.setA_e_gs1(max5);// 其中{a_e_gs1}公司
		secondMainBady.setA_e_cgcgl(getMixThreeRate(group5, max5));// 一次采购成功率为{a_e_cgcgl}%；
		secondMainBady.setA_e_gs2(min5);// {a_e_gs2}公司
		secondMainBady.setA_e_dy(getMixThreeRate(group5, min5));// 均低于{a_e_dy}%。

//		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos5_1 = getsecondMainBadyModuleList(5, "服务类一次采购成功率",request);
//		List<MainBadySecond2PO> group5_1 = getGroup(pos5);
//		String max5_1 = getThree(pos5.get("max"));//前三
//		String min5_1 = getThree(pos5.get("min"));//后三
//		secondMainBady.setA_e_yjcgl(getSumRate(group5_1));//截止2月份，各单位平均成功率{a_e_yjcgl}%。
//		secondMainBady.setA_e_gs1(max5_1);// 其中{a_e_gs1}公司
//		secondMainBady.setA_e_cgcgl(getMixThreeRate(group5_1, max5_1));// 一次采购成功率为{a_e_cgcgl}%；
//		secondMainBady.setA_e_gs2(min5_1);// {a_e_gs2}公司
//		secondMainBady.setA_e_dy(getMixThreeRate(group5_1, min5_1));// 均低于{a_e_dy}%。


		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos6 = getsecondMainBadyModuleList(6, "服务类框架协议执行率",request);
		List<MainBadySecond2PO> group6 = getGroup(pos6);
		String max6 = getThree(pos6.get("max"));//前三
		String min6 = getThree(pos6.get("min"));//后三
		secondMainBady.setA_f6_pjzxl(getSumRate(group6));//截止2月份，各单位平均执行率{a_f6_pjzxl}%。
		secondMainBady.setA_f6_gs1(max6);// 其中，{a_f6_gs1}公司较好，
		secondMainBady.setA_f6_gy(getMixThreeRate(group6, max6));// 高于{a_f6_gy}%；
		secondMainBady.setA_f6_gs2(min6);// {a_f6_gs2}公司
		secondMainBady.setA_f6_dy(getMixThreeRate(group6, min6));// 均低于{a_f6_dy}%。


		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos7 = getsecondMainBadyModuleList(7, "物资类技术规范书退回率",request);
		List<MainBadySecond2PO> group7 = getGroup(pos7);
		String max7 = getThree(pos7.get("max"));//前三
		String min7 = getThree(pos7.get("min"));//后三
		secondMainBady.setA_f7_pjcgl1(getSumRate(group7));//截止2月份，物资批次中，各单位平均成功率{a_f7_pjcgl1}%，
		secondMainBady.setA_f7_gs1(max7);// 其中{a_f7_gs1}等
		secondMainBady.setA_f7_j(getMixThreeRate(group7, max7));// {a_f7_j}家公司未发生技术规范退回情况；
		secondMainBady.setA_f7_gs2(min7);// {a_f7_gs2}公司退回率较高，
		secondMainBady.setA_f7_cg1(getMixThreeRate(group7, min7));// 超过{a_f7_cg1}%。

		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos8 = getsecondMainBadyModuleList(8, "服务类技术规范书退回率",request);
		List<MainBadySecond2PO> group8 = getGroup(pos8);
		String max8 = getThree(pos8.get("max"));//前三
		String min8 = getThree(pos8.get("min"));//后三
		secondMainBady.setA_f7_pjcgl2(getSumRate(group8));//截止2月份，工程及服务批次中，各单位平均成功率{a_f7_pjcgl2}%，
		secondMainBady.setA_f7_gs3(max8);// 其中{a_f7_gs3}公司较好，
		secondMainBady.setA_f7_thl(getMixThreeRate(group8, max8));// 退回率均不到{a_f7_thl}%；
		secondMainBady.setA_f7_gs4(min8);// {a_f7_gs4}公司退回率较高，
		secondMainBady.setA_f7_cg2(getMixThreeRate(group8, min8));// 超过{a_f7_cg2}%。


		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos9 = getsecondMainBadyModuleList(9, "零星物资电商化请购总金额（万元）",request);
		List<MainBadySecond2PO> group9 = getGroup(pos9);
		String max9 = getThree(pos9.get("max"));//前三
		String min9 = getThree(pos9.get("min"));//后三
		secondMainBady.setA_g_zje(getSumRate(group9));//截止2月份，各单位平均请购总金额{a_g_zje}万元，
		secondMainBady.setA_g_gs1(max9);// 其中{a_g_gs1}公司
		secondMainBady.setA_g_cg(getMixThreeRate(group9, max9));// 超过{a_g_cg}万元；
		secondMainBady.setA_g_gs2(min9);// {a_g_gs2}公司
		secondMainBady.setA_g_dy(getMixThreeRate(group9, min9));// 低于{a_g_dy}万元。

		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos10 = getsecondMainBadyModuleList(10, "逾期货款清理完成指数（万元）",request);
		List<MainBadySecond2PO> group10 = getGroup(pos10);
		String max10 = getThree(pos10.get("max"));//前三
		String min10 = getThree(pos10.get("min"));//后三
		secondMainBady.setA_h_gs("*");//截止2月份，{a_h_gs}公司存在逾期款项，
		secondMainBady.setA_h_kx("*");//涉及款项{a_h_kx}笔，
		secondMainBady.setA_h_je("*");//金额{a_h_je}万元，
		secondMainBady.setA_h_yy("*");//原因为{a_h_yy}。


		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos11 = getsecondMainBadyModuleList(11, "供应计划完成率",request);
		List<MainBadySecond2PO> group11 = getGroup(pos11);
		String max11 = getThree(pos11.get("max"));//前三
		String min11 = getThree(pos11.get("min"));//后三
		secondMainBady.setA_i_pjwcl("*");//本月，各单位平均完成率为{a_i_pjwcl}%。
		secondMainBady.setA_i_gs1("*");// 其中{a_i_gs1}等
		secondMainBady.setA_i_j("*");// {a_i_j}家公司
		secondMainBady.setA_i_wcl("*");// 完成率均为{a_i_wcl}%；
		secondMainBady.setA_i_gs2("*");// {a_i_gs2}公司完成率
		secondMainBady.setA_i_dy("*");// 均低于{a_i_dy}%。


		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos12 = getsecondMainBadyModuleList(12, "物资供应计划调整率",request);
		List<MainBadySecond2PO> group12 = getGroup(pos12);
		String max12 = getThree(pos12.get("max"));//前三
		String min12 = getThree(pos12.get("min"));//后三
		secondMainBady.setA_j_pjtzl(getSumRate(group12));//本月，各单位平均调整率为{a_j_pjtzl}%。
		secondMainBady.setA_j_gs1(max12);// 其中{a_j_gs1}公司调整率较低，
		secondMainBady.setA_j_dy(getMixThreeRate(group12,max12)); // 均低于{a_j_dy}%；
		secondMainBady.setA_j_gs2(min12);// {a_j_gs2}公司调整率较高，
		secondMainBady.setA_j_cg(getMixThreeRate(group12,min12));// 超过{a_j_cg}%。


		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos13 = getsecondMainBadyModuleList(13, "到货交接单签署正确率",request);
		List<MainBadySecond2PO> group13 = getGroup(pos13);
		String max13 = getThree(pos13.get("max"));//前三
		String min13 = getThree(pos13.get("min"));//后三
		secondMainBady.setA_k_pjzql(getSumRate(group13));//本月，各单位平均正确率为{a_k_pjzql}%。
		secondMainBady.setA_k_gs1("*");// 其中{a_k_gs1}等
		secondMainBady.setA_k_j("*");// {a_k_j}家公司正确率较高，
		secondMainBady.setA_k_jw("*"); // 均为{a_k_jw}%
		secondMainBady.setA_k_gs2("*");// {a_k_gs2}公司正确率
		secondMainBady.setA_k_dy("*");// 低于{a_k_dy}%。


		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos14 = getsecondMainBadyModuleList(14, "库存积压监控",request);
		List<MainBadySecond2PO> group14 = getGroup(pos14);
		String max14 = getThree(pos14.get("max"));//前三
		String min14 = getThree(pos14.get("min"));//后三
		secondMainBady.setA_z_zyb1("*");//截止2月份，北京公司整体库存积压占比{a_z_zyb1}%，
		secondMainBady.setA_z_zyb2("*");// 各二级单位平均库存积压占比{a_z_zyb2}%。
		secondMainBady.setA_z_gs1("*");// 其中{a_z_gs1}公司库存积压占比相对较低，
		secondMainBady.setA_z_dy("*"); // 低于{a_z_dy}%；
		secondMainBady.setA_z_gs2("*");// {a_z_gs2}公司库存积压
		secondMainBady.setA_z_cg("*");// 占比超过{a_z_cg}%。


		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos15 = getsecondMainBadyModuleList(15, "非电网零星物资人工评价率",request);
		List<MainBadySecond2PO> group15 = getGroup(pos15);
		String max15 = getThree(pos15.get("max"));//前三
		String min15 = getThree(pos15.get("min"));//后三
		secondMainBady.setA_l_rgpjl(getSumRate(group15));//本月，各单位平均人工评价率为{a_l_rgpjl}%。
		secondMainBady.setA_l_gs1(max15);// {a_l_gs1}公司评价率较好，
		secondMainBady.setA_l_cg(getMixThreeRate(group15, max15));// 超过{a_l_cg}%；
		secondMainBady.setA_l_gs2(min15);// {a_l_gs2}公司评价率
		secondMainBady.setA_l_dy(getMixThreeRate(group15, min15));// 低于{a_l_dy}%。


		Map<String, Map<Integer, List<MainBadySecond2PO>>> pos16 = getsecondMainBadyModuleList(16, "出厂验收参与率",request);
		List<MainBadySecond2PO> group16 = getGroup(pos16);
		String max16 = getThree(pos16.get("max"));//前三
		String min16 = getThree(pos16.get("min"));//后三
		secondMainBady.setA_m_pjcyl(getSumRate(group16));//本月，各单位平均参与率为{a_m_pjcyl}%。
		secondMainBady.setA_m_gs1(max16);// 其中{a_m_gs1}
		secondMainBady.setA_m_j("3");// 等{a_m_j}家公司参与率较高，
		secondMainBady.setA_m_cg(getMixThreeRate(group16, max16));// 超过{a_m_cg}%；
		secondMainBady.setA_m_gs2(min16);// {a_m_gs2}公司
		secondMainBady.setA_m_dy(getMixThreeRate(group16, min16)); // 参与率低于{a_m_dy}%。
		return secondMainBady;
	}


	/**
	 * 获取总平均
	 *
	 * @param list
	 * @return
	 */
	private String getSumRate(List<MainBadySecond2PO> list) {
		Double dou = 0.0;
		int size = list.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				dou += list.get(i).getNum();
			}
		} else {
			return "0";
		}
		String format = String.format("%.2f", (dou / list.size()));
		double parseDouble = Double.parseDouble(format);
		if (Math.round(parseDouble)-parseDouble==0){
			BigDecimal bigDecimal = NumberUtils.createBigDecimal(String.valueOf((long)parseDouble));
			format = format(bigDecimal);
			return format;
		}

		return format;
	}

	/**
	 * 获取前三
	 *
	 * @param
	 * @return
	 */
	private String getThree(Map<Integer, List<MainBadySecond2PO>> map) {
		String str = "";
		//判断不是只有两个的
		if (map.size() != 0) {
			//获取前三数据集
			if (2 < map.get(1).size()) {
				List<MainBadySecond2PO> mainBadySecond2POS = map.get(1);
				for (MainBadySecond2PO po : mainBadySecond2POS) {
					str = str + "、" + po.getCity();
				}
			} else {
				if (3 < map.size()) {
					for (int i = 1; i <= 3; i++) {
						List<MainBadySecond2PO> mainBadySecond2POS = map.get(i);
						for (MainBadySecond2PO po : mainBadySecond2POS) {
							str = str + "、" + po.getCity();
						}
					}
				} else {
					for (int i = 1; i <= map.size(); i++) {
						List<MainBadySecond2PO> mainBadySecond2POS = map.get(i);
						for (MainBadySecond2PO po : mainBadySecond2POS) {
							str = str + "、" + po.getCity();
						}
					}
				}
			}
		} else {
			str = "、*";
		}
		return str.substring(1);
	}


	/**
	 * 获取第三的率
	 *
	 * @param list
	 * @return
	 */
	private String getMixThreeRate(List<MainBadySecond2PO> list, Object str) {
		int index = String.valueOf(str).lastIndexOf("、");
		if (index > 0) {
			String substring = String.valueOf(str).substring(index + 1);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getCity().equals(substring)) {
					BigDecimal bigDecimal = NumberUtils.createBigDecimal(String.valueOf(list.get(i).getNum()));
					String format = format(bigDecimal);
					return format;
				}
			}
		}
		return "0";
	}

	/**
	 * 获取正文数据某一模块
	 *
	 * @param col      列
	 * @param rateName 指标名称
	 * @return
	 */
	public Map<String, Map<Integer, List<MainBadySecond2PO>>> getsecondMainBadyModuleList(Integer col, String rateName,ReportRequest request) {
		List<Map<String, List<MainBadySecond2PO>>> list = getRateMap(request);//获得所有集合率
		Map<String, List<MainBadySecond2PO>> stringListMap = list.get(col);//获取正文数据某一模块
		System.out.println("获取正文数据某一模块:"+stringListMap);
		List<MainBadySecond2PO> mainBadySecond2POS1 = stringListMap.get(rateName);//获得实体
		System.out.println("排序前:" + mainBadySecond2POS1);
		Collections.sort(mainBadySecond2POS1);
		// 将指标数值（num）相同的实体放在一起并通过key（num）进行从小到大排序
		Map<Double, List<MainBadySecond2PO>> minMap = new TreeMap<>();
		for (MainBadySecond2PO entity : mainBadySecond2POS1) {
			List<MainBadySecond2PO> lists = minMap.computeIfAbsent(entity.getNum(), k -> new ArrayList<>());
			lists.add(entity);
		}
		//删除具有特殊标识的key
		minMap.remove(9999.99);
		/**
		 * 数据集重排序 从小到大排序
		 * 将指标数值（num）相同的实体放在一起并通过key（num）进行从小到大排序
		 */
		Map<Integer, List<MainBadySecond2PO>> min = new HashMap<>();
		Integer ints = 1;
		for (Double key : minMap.keySet()) {
			min.put(ints++, minMap.get(key));
		}

		//排序取反
		Comparator<Double> reverseComparator=Collections.reverseOrder();
		Map<Double, List<MainBadySecond2PO>> maxMap = new TreeMap<>(reverseComparator);
		maxMap.putAll(minMap);

		/**
		 * 数据集重排序 从大到小排序
		 * 将指标数值（num）相同的实体放在一起并通过key（num）进行从大到小排序
		 */
		Map<Integer, List<MainBadySecond2PO>> max = new HashMap<>();
		ints = 1;
		for (Double key : maxMap.keySet()) {
			max.put(ints++, maxMap.get(key));
		}

		Map<String, Map<Integer, List<MainBadySecond2PO>>> map = new HashMap<>();
		map.put("min",min);
		map.put("max",max);

		return map;
	}



	/**
	 * 获得对num指标数值排序后的集合
	 *
	 * @param map
	 * @return
	 */
	private List<MainBadySecond2PO> getGroup(Map<String, Map<Integer, List<MainBadySecond2PO>>> map) {
		Collection<List<MainBadySecond2PO>> values = map.get("min").values();
		// 对每个List进行排序
		List<List<MainBadySecond2PO>> groups = new ArrayList<>(values);
		List<MainBadySecond2PO> list = new ArrayList<>();
		int rank = 1;
		for (List<MainBadySecond2PO> group : groups) {
			List<MainBadySecond2PO> groupList = group;
			for (MainBadySecond2PO po : groupList) {
				po.setRank(rank);
				list.add(po);
			}
			rank++;
		}
		// 输出结果
		System.out.println("排序后集合" + list);
		return list;
	}


	/**
	 * 获得集合率
	 */
	private List<Map<String, List<MainBadySecond2PO>>> getRateMap(ReportRequest request) {
		List<Map<String, List<MainBadySecond2PO>>> list = new ArrayList<>();
		List<String> getkey = getRateKeys();
		for (int i = 0; i < getkey.size(); i++) {
			list.add(getUnit(getkey.get(i), i,request));
		}
		return list;
	}


	/**
	 * 获得公司
	 */
	private Map<String, List<MainBadySecond2PO>> getUnit(String key, Integer ints,ReportRequest request) {
		Map<String, List<MainBadySecond2PO>> map = new HashMap<>();
		List<String> getkey = getUnitkey();
		List<MainBadySecond2PO> list = new ArrayList<>();
		for (int i = 0; i < getkey.size(); i++) {
			int num = 17;
			MainBadySecond2PO second2PO = new MainBadySecond2PO();
			num = (i * num) + ints;
			List<MainBadySecondPO> pos = mainBadySecondMapper.selectyb001ByTable(num,request);
			second2PO.setCity(getkey.get(i));
			second2PO.setNum(this.intercept(pos.get(0).getZzbsz()));
			second2PO.setRank(0);
			list.add(second2PO);
		}
		map.put(key, list);
		return map;
	}


	/**
	 * 截取“%”之外的数据
	 * 三种情况性：
	 * 1.正常
	 * 2.为* ——代表暂无逻辑
	 * 3.为 - ——代表没有逻辑
	 * @param str1 输入类型
	 * @return
	 */
	public static Double intercept(String str1) {
		if (str1.equals("-")) {
			return Double.valueOf(9999.99);
		}

		if (!str1.equals("*")) {
			try {
				int i = str1.indexOf("%");
				if (i == -1) {
					return Double.valueOf(str1);
				} else {
					String substring = str1.substring(0, i);
					return Double.valueOf(substring);
				}
			} catch (Exception e) {
				return Double.valueOf(0);
			}
		} else {
			return Double.valueOf(0);
		}
	}



	/**
	 * 23个单位公司
	 */
	private List<String> getUnitkey() {
		List<String> list = new ArrayList<>();
		list.add("城区");
		list.add("通州");
		list.add("朝阳");
		list.add("海淀");
		list.add("丰台");
		list.add("石景山");
		list.add("亦庄");
		list.add("昌平");
		list.add("门头沟");
		list.add("房山");
		list.add("大兴");
		list.add("平谷");
		list.add("怀柔");
		list.add("密云");
		list.add("顺义");
		list.add("延庆");
		list.add("经研院");
		list.add("电科院");
		list.add("工程");
		list.add("检修");
		list.add("电缆");
		list.add("信通");
		list.add("建咨");
		return list;
	}

	/**
	 * 16个率
	 */
	private List<String> getRateKeys() {
		List<String> list = new ArrayList<>();
		list.add("ESC预警问题整改率");
		list.add("盘活利库任务完成率");
		list.add("计划申报正确率");
		list.add("报废物资处置计划申报规范率");
		list.add("一次采购成功率");
		list.add("服务类一次采购成功率");
		list.add("服务类框架协议执行率");
		list.add("物资类技术规范书退回率");
		list.add("服务类技术规范书退回率");
		list.add("零星物资电商化请购总金额（万元）");
		list.add("逾期货款清理完成指数（万元）");
		list.add("供应计划完成率");
		list.add("物资供应计划调整率");
		list.add("到货交接单签署正确率");
		list.add("库存积压监控");
		list.add("非电网零星物资人工评价率");
		list.add("出厂验收参与率");
		return list;
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
