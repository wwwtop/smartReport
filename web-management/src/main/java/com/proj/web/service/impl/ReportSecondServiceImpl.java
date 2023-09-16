package com.proj.web.service.impl;

import com.proj.core.utils.exception.ProjException;
import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.SmartReportIndexVO;
import com.proj.model.vo.second.SecondCircularVO;
import com.proj.model.vo.second.SecondMainBadyVO;
import com.proj.model.vo.second.SmartReportSecondIndexVO;
import com.proj.web.entity.SmartReportAddPO;
import com.proj.web.mapper.ReportMapper;
import com.proj.web.service.AccessorySecondService;
import com.proj.web.service.MainBadySecondService;
import com.proj.web.service.ReportSecondService;
import com.proj.web.util.QuartUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.proj.web.util.ReflectionExample.printFields;
import static com.proj.web.util.ReflectionExample.setFields;

/**
 * 二级月报汇总
 */
@Service
@Transactional(rollbackFor = ProjException.class)
public class ReportSecondServiceImpl implements ReportSecondService {

	private final static String SECONDMAINBADY = "secondMainBady";//正文
	private final static String CORP = "corp";//二级供应链运营指标通报明细表标识

	/*
		月报汇总
		 */
	@Resource
	ReportMapper reportMapper;
	/**
	 * 二级供应链运营指标通报明细
	 */
	@Resource
	private AccessorySecondService accessorySecondService;
	/**
	 * 正文
	 */
	@Resource
	MainBadySecondService mainBadySecondService;


	/**
	 * 添加数据集
	 *
	 * @return
	 */
	@Override
	public Boolean smartReportAdd(ReportRequest request) {
		try {
			reportMapper.deleteByTime(request.getTime(), CORP);//删除所有数据集通过月份
			Map<String, Object> secondCircularMap = accessorySecondService.getSecondCircularMap(request);//二级供应链运营指标通报明细
			smartReportAccessoryByAdd(secondCircularMap, request, CORP);

			reportMapper.deleteByTime(request.getTime(), SECONDMAINBADY);//删除所有数据集通过月份
			Map<String, Object> basicDataStatisticsMap = mainBadySecondService.getsecondMainBadyList(request);//正文
			smartReportAccessoryByAdd(basicDataStatisticsMap, request, SECONDMAINBADY);
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
	public SmartReportSecondIndexVO smartReportQueryByMain(ReportRequest request) {
		request.setTargetName("secondMainBady");
		request.setZtype("yb");
		SmartReportIndexVO vo = new SmartReportIndexVO();
		List<LinkedHashMap> maps = reportMapper.selectAll(request);
		SecondMainBadyVO mainBadyVO = new SecondMainBadyVO();
		List<String> strings = printFields(mainBadyVO);
		int size = maps.size();
		for (int i = 0; i < size; i++) {
			String amount = (String) maps.get(i).get("ZZBSZ");
			setFields(mainBadyVO, strings.get(i), amount);

		}
		SmartReportSecondIndexVO vos = new SmartReportSecondIndexVO();
		vos.setSecondMainBadyList(mainBadyVO);
//        vo.setPageSize(list.size());
//        vo.setBasicDataStatisticsList(list);
		return vos;
	}

	/**
	 * 查询数据集-针对基础数据统计表
	 *
	 * @return
	 */
	@Override
	public SmartReportSecondIndexVO smartReportQueryByCircular(ReportRequest request) {
		request.setTargetName("corp");
		request.setZtype("yb");
		SmartReportSecondIndexVO vo = new SmartReportSecondIndexVO();
		List<LinkedHashMap> maps = reportMapper.selectAll(request);

		LinkedList<SecondCircularVO> list = new LinkedList<>();
		int size = maps.size();
		int increment = 17;//一共17列
		//通过反射进行写入
		for (int i = 0; i < size; i += increment) {
			SecondCircularVO vo1 = new SecondCircularVO();
			for (int j = 0; j < increment; j++) {
				int index = i + j;
				String amount = (String) maps.get(index < size ? index : i).get("ZZBSZ");
				try {
					Field field = SecondCircularVO.class.getDeclaredField("fieldset" + (j + 1));
					field.setAccessible(true);
					field.set(vo1, amount);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			list.addLast(vo1);
		}
		vo.setPageSize(list.size());
		vo.setSecondCircularList(list);
		return vo;
	}


}
