package com.proj.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.proj.core.utils.exception.ProjException;
import com.proj.web.entity.Export;
import com.proj.web.entity.SmartReportSecondPO;
import com.proj.web.mapper.ExportMapper;
import com.proj.web.service.ExportService;
import com.proj.web.util.ExcelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class ExportServiceImpl implements ExportService {
	@Resource
	ExportMapper exportMapper;

	/**
	 * 导出
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	@Override
	public void export1(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		String term = request.getTerm();
		Integer num;//数量
		Integer allNum;//总数
		Integer group;//组
		//编号basic_001
		int indexOf = term.indexOf("_");
		//判断属于那个模块
		if (term.substring(0, indexOf).equals("basic")) {
			//获取到带有模块名称的编码并转换为数字编码
			String substring = term.substring(indexOf + 1, term.length());
			Integer index = Integer.valueOf(substring);
			//获取编号所属行数
			if (index % 7 != 0) {
				allNum = index / 7 + 1;
			} else {
				allNum = index / 7;//32
			}
			//获取组别 和 第几位
			num = allNum % 8;
			if (num != 0) {
				group = allNum / 8 + 1;
			} else {
				group = allNum / 8 ;
				num = 8;
			}
			this.groupBybasic(request,response,group,num);
		}

	}

	/**
	 * 基础表分组
	 * 每组八个
	 * @param request
	 * @param response
	 * @param group
	 * @param num
	 * @throws ProjException
	 */
	private void groupBybasic(SmartReportSecondPO request, HttpServletResponse response,Integer group,Integer num) throws ProjException {
		request.setTerm("basic_0"+num);
		switch (group) {
			case 1:
				groupBybasic1(request, response);//基础表分组
				break;
			case 2:
				groupBybasic2(request, response);//基础表分组
				break;
			case 3:
				groupBybasic3(request, response);//基础表分组
				break;
			case 4:
				groupBybasic4(request, response);//基础表分组
				break;
			default:
				throw new ProjException();
		}
	}

	private void groupBybasic1(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		switch (request.getTerm()) {
			case "basic_01":
				exportJh01(request, response);//物资类批次计划条目及金额
				break;
			case "basic_02":
				exportJh02(request, response);//物资类协议库存计划条目及金额
				break;
			case "basic_03":
				exportJh03(request, response);//物资类框架计划条目及金额
				break;
			case "basic_04":
				exportJh06(request, response);//协议库存匹配计划条目及金额
				break;
			case "basic_05":
				exportJh04(request, response);//服务类批次计划条目及金额
				break;
			case "basic_06":
				exportJh05(request, response);//服务类框架计划条目及金额
				break;
			case "basic_07":
				exportZb01(request, response);//国网公司物资采购数量及金额
				break;
			case "basic_08":
				exportZb02(request, response);//北京公司物资类实施采购金额
				break;
			default:
				throw new ProjException();
		}
	}

	private void groupBybasic2(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		switch (request.getTerm()) {
			case "basic_01":
				exportZb03(request, response);//北京公司服务类实施采购金额
				break;
			case "basic_02":
				exportZb04(request, response);//零星物资电商化请购条目及金额
				break;
			case "basic_03":
				exportZb05(request, response);//服务类框架协议执行条目及金额
				break;
			case "basic_04":
				exportHt01(request, response);//物资合同条目及金额
				break;
			case "basic_05":
				exportHt02(request, response);//采购供货单条目及金额
				break;
			case "basic_06":
				exportHt03(request, response);//合同变更条目及金额
				break;
			case "basic_07":
				//物资到货
				break;
			case "basic_08":
				//ELP监控
				break;
			default:
				throw new ProjException();
		}
	}

	private void groupBybasic3(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		switch (request.getTerm()) {
			case "basic_01":
				exportHt05(request, response);//集中支付
				break;
			case "basic_02":
				exportHt06(request, response);//非电商采购支付逾期
				break;
			case "basic_03":
				//北京公司库存情况	库 存
				break;
			case "basic_04":
				//北京公司库存情况	入 库
				break;
			case "basic_05":
				//北京公司库存情况	出 库
				break;
			case "basic_06":
				//代保管物资	入 库
				break;
			case "basic_07":
				//代保管物资	出 库
				break;
			case "basic_08":
				//主动配送
				break;
			default:
				throw new ProjException();
		}
	}

	private void groupBybasic4(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		switch (request.getTerm()) {
			case "basic_01":
				//专业仓情况	库 存
				break;
			case "basic_02":
				//专业仓情况	入 仓
				break;
			case "basic_03":
				//专业仓情况	出 仓
				break;
			case "basic_04":
				exportJh07(request, response);//跨单位利库
				break;
			case "basic_05":
				exportJh08(request, response);//报废处置
				break;
			case "basic_06":
				exportZj09(request, response);//设备监造
				break;
			case "basic_07":
				exportZj01(request, response);//设备抽检
				break;
			case "basic_08":
				exportZj10(request, response);//不良行为
				break;
			default:
				throw new ProjException();
		}
	}


	/**
	 * 国网公司物资采购数量及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb01(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		List<Map<String, Object>> list = exportMapper.selectExportZb001ByMonth(request);
		tableHanderByZb01("国网公司物资采购数量及金额.xls", list, response);
	}

	/**
	 * 北京公司物资类实施采购金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb02(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//北京公司物资类实施采购金额
		List<Map<String, Object>> list = exportMapper.selectExportZb002ByMonth(request);
		tableHanderByZb01("北京公司物资类实施采购金额.xls", list, response);
	}

	/**
	 * 北京公司服务类实施采购金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb03(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//北京公司服务类实施采购金额
		List<Map<String, Object>> list3 = exportMapper.selectExportZb003ByMonth(request);
		tableHanderByZb01("北京公司服务类实施采购金额.xls", list3, response);
	}


	/**
	 * 一次采购成功率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb08(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//一次采购成功率-暂无逻辑
		List<Map<String, Object>> list = exportMapper.selectExportZb008ByMonth(request);
		tableHanderByZb01("一次采购成功率.xls", list, response);
	}


	/**
	 * 物资类采购资金节约率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb09(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//物资类采购资金节约率
		List<Map<String, Object>> list = exportMapper.selectExportZb009ByMonth(request);
		tableHanderByZb01("物资类采购资金节约率.xls", list, response);
	}

	/**
	 * 服务类采购资金节约率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb10(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//服务类采购资金节约率
		List<Map<String, Object>> list = exportMapper.selectExportZb010ByMonth(request);
		tableHanderByZb01("服务类采购资金节约率.xls", list, response);
	}


	/**
	 * 物资类公开采购率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb11(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//物资类公开采购率
		List<Map<String, Object>> list11 = exportMapper.selectExportZb011ByMonth(request);
		tableHanderByZb01("物资类公开采购率.xls", list11, response);
	}

	/**
	 * 服务类公开采购率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb12(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//服务类公开采购率
		List<Map<String, Object>> list = exportMapper.selectExportZb012ByMonth(request);
		tableHanderByZb01("服务类公开采购率.xls", list, response);
	}


	/**
	 * 基础固定表头 zb01
	 * 国网公司物资采购数量及金额
	 * 北京公司物资类实施采购金额
	 * 北京公司服务类实施采购金额
	 * 一次性采购成功率
	 * 物资类采购资金节约率
	 * 服务类采购资金节约率
	 * 物资类公开采购率
	 * 服务类公开采购率
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZb01(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "批次编码");
		headMap.put("amount2", "批次描述");
		headMap.put("amount3", "批次年度");
		headMap.put("amount4", "采购类别");
		headMap.put("amount5", "总部采购申请号");
		headMap.put("amount6", "总部采购申请行号");
		headMap.put("amount7", "网省采购申请号");
		headMap.put("amount8", "网省采购申请行号");
		headMap.put("amount9", "单位编码");
		headMap.put("amount10", "单位名称");
		headMap.put("amount11", "物料编码");
		headMap.put("amount12", "数量");
		headMap.put("amount13", "概算总价");
		headMap.put("amount14", "含税总价");
		headMap.put("amount15", "中标时间");
		headMap.put("amount16", "招标公告类型");
		headMap.put("amount17", "是否中标");
		headMap.put("amount18", "是否中标(新)");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 零星物资电商化请购条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb04(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//零星物资电商化请购条目及金额
		List<Map<String, Object>> list4 = exportMapper.selectExportZb004ByMonth(request);
		tableHanderByZb02("零星物资电商化请购条目及金额.xls", list4, response);
	}

	/**
	 * 基础固定表头 zb02 零星物资电商化请购条目及金额
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZb02(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "请购单号（专区）");
		headMap.put("amount2", "请购单行项目（专区）");
		headMap.put("amount3", "采购申请");
		headMap.put("amount4", "采购申请行项目");
		headMap.put("amount5", "物料");
		headMap.put("amount6", "工厂");
		headMap.put("amount7", "数量");
		headMap.put("amount8", "评价价格(含税)");
		headMap.put("amount9", "物料详细描述");
		headMap.put("amount10", "创建日期");
		headMap.put("amount11", "操作类型");
		headMap.put("amount12", "专区物料单位");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 服务类框架协议执行条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb05(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		List<Map<String, Object>> list5 = exportMapper.selectExportZb005ByMonth(request);
		tableHanderByZb03("服务类框架协议执行条目及金额.xls", list5, response);
	}

	/**
	 * 基础固定表头 zb03 服务类框架协议执行条目及金额——暂无表头
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZb03(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 物资类采购流标率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb06(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//物资类采购流标率
		List<Map<String, Object>> list = exportMapper.selectExportZb006ByMonth(request);
		tableHanderByZb04("物资类采购流标率.xls", list, response);
	}

	/**
	 * 服务类采购流标率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb07(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//物资类采购流标率
		List<Map<String, Object>> list = exportMapper.selectExportZb007ByMonth(request);
		tableHanderByZb04("服务类采购流标率.xls", list, response);
	}

	/**
	 * 基础固定表头 zb04
	 * <p>
	 * 物资类采购流标率
	 * 服务类采购流标率
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZb04(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "批次编码");
		headMap.put("amount2", "批次描述");
		headMap.put("amount3", "批次年度");
		headMap.put("amount4", "采购类别");
		headMap.put("amount5", "分标编号");
		headMap.put("amount6", "分标名称");
		headMap.put("amount7", "分包名称");
		headMap.put("amount8", "中标结果ID");
		headMap.put("amount9", "中标/流标");
		headMap.put("amount10", "报名供应商数量");
		headMap.put("amount11", "投标供应商数量");
		headMap.put("amount12", "否决供应商数量");
		headMap.put("amount13", "流标原因");
		headMap.put("amount14", "中标时间");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 招标采购——监控 服务类框架协议执行完成率——暂无 需要调整
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb13(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//招标采购——监控 服务类框架协议执行完成率——暂无 需要调整
		List<Map<String, Object>> list = exportMapper.selectExportZb013ByMonth(request);
		tableHanderByZb05("服务类框架协议执行完成率.xls", list, response);
	}

	/**
	 * 基础固定表头 zb05
	 * <p>
	 * 服务类框架协议执行完成率
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZb05(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "预警描述");
		headMap.put("amount2", "款项性质");
		headMap.put("amount3", "逾期类型");
		headMap.put("amount4", "采购凭证号");
		headMap.put("amount5", "单位描述");
		headMap.put("amount6", "采购组织");
		headMap.put("amount7", "采购组织描述");
		headMap.put("amount8", "合同类型");
		headMap.put("amount9", "供应商名称");
		headMap.put("amount10", "合同金额(万元)");
		headMap.put("amount11", "应付金额(万元)");
		headMap.put("amount12", "已付金额(万元)");
		headMap.put("amount13", "未付金额（万元）");
		headMap.put("amount14", "条件");
		headMap.put("amount15", "逾期日期");
		headMap.put("amount16", "未支付原因");
		headMap.put("amount17", "支付状态");
		headMap.put("amount18", "预警指标名称");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}


	/**
	 * 原材料价格波动情况
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZb14(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//原材料价格波动情况
		List<Map<String, Object>> list = exportMapper.selectExportZb014ByMonth(request);
		tableHanderByZb06("原材料价格波动情况.xls", list, response);
	}


	/**
	 * 基础固定表头 zb06
	 * <p>
	 * 原材料价格波动情况
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZb06(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "原材料编号");
		headMap.put("amount2", "发布日期");
		headMap.put("amount3", "原材料名称");
		headMap.put("amount4", "原材料单位");
		headMap.put("amount5", "原材料价格（含税）");
		headMap.put("amount6", "创建日期");
		headMap.put("amount7", "材质");
		headMap.put("amount8", "规格");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}


	/**
	 * 物资合同条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportHt01(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//物资合同条目及金额
		List<Map<String, Object>> list = exportMapper.selectExportHt001ByMonth(request);
		tableHanderByHt01("物资合同条目及金额.xls", list, response);
	}

	/**
	 * 基础固定表头 ht01
	 * 物资合同条目及金额
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByHt01(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "序号");
		headMap.put("amount2", "批次名称");
		headMap.put("amount3", "凭证号");
		headMap.put("amount4", "分包名称");
		headMap.put("amount5", "分标名称");
		headMap.put("amount6", "供应商名称");
		headMap.put("amount7", "金额");
		headMap.put("amount8", "凭证创建日期");
		headMap.put("amount9", "合同生效日期");
		headMap.put("amount10", "中标日期");
		headMap.put("amount11", "凭证类型");
		headMap.put("amount12", "凭证行项目");
		headMap.put("amount13", "单位名称");
		headMap.put("amount14", "招标批次编号");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 采购供货单条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportHt02(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//采购供货单条目及金额
		List<Map<String, Object>> list = exportMapper.selectExportHt002ByMonth(request);
		tableHanderByHt02("采购供货单条目及金额.xls", list, response);
	}

	/**
	 * 基础固定表头 ht02 、
	 * 采购供货单条目及金额
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByHt02(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "序号");
		headMap.put("amount2", "单位编码");
		headMap.put("amount3", "单位名称");
		headMap.put("amount4", "工程编码");
		headMap.put("amount5", "工程名称");
		headMap.put("amount6", "物料编码");
		headMap.put("amount7", "协议库存货物名称");
		headMap.put("amount8", "协议库存名称");
		headMap.put("amount9", "招标批次号");
		headMap.put("amount10", "ERP协议库存编号");
		headMap.put("amount11", "ERP协议库存行项目");
		headMap.put("amount12", "采购供货单金额");
		headMap.put("amount13", "采购供货单号");
		headMap.put("amount14", "采购供货单行项目");
		headMap.put("amount15", "采购供货单生效时间");
		headMap.put("amount16", "批次名称");
		headMap.put("amount17", "供货单创建日期");
		headMap.put("amount18", "中标日期");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 合同变更条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportHt03(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//合同变更条目及金额
		List<Map<String, Object>> list = exportMapper.selectExportHt003ByMonth(request);
		tableHanderByHt03("合同变更条目及金额.xls", list, response);
	}

	/**
	 * 基础固定表头 ht03
	 * <p>
	 * 合同变更条目及金额
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByHt03(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "序号");
		headMap.put("amount2", "单位编码");
		headMap.put("amount3", "单位描述");
		headMap.put("amount4", "供应计划编号");
		headMap.put("amount5", "合同编号");
		headMap.put("amount6", "项目编码");
		headMap.put("amount7", "项目名称");
		headMap.put("amount8", "采购订单号");
		headMap.put("amount9", "采购订单行项目");
		headMap.put("amount10", "物料编码");
		headMap.put("amount11", "物料描述");
		headMap.put("amount12", "合同金额");
		headMap.put("amount13", "单价");
		headMap.put("amount14", "订单金额");
		headMap.put("amount15", "订单数量");
		headMap.put("amount16", "确定交货日期");
		headMap.put("amount17", "合同交货期");
		headMap.put("amount18", "合同变更生效日期");
		headMap.put("amount19", "计量单位");
		headMap.put("amount20", "变更内容");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 合同签订及时率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportHt04(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//合同签订及时率
		List<Map<String, Object>> list = exportMapper.selectExportHt004ByMonth(request);
		tableHanderByHt04("合同签订及时率.xls", list, response);
	}

	/**
	 * 基础固定表头 ht04
	 * <p>
	 * 合同签订及时率
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByHt04(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "批次编码");
		headMap.put("amount2", "批次名称");
		headMap.put("amount3", "实施方式");
		headMap.put("amount4", "分标名称");
		headMap.put("amount5", "分包名称");
		headMap.put("amount6", "工程名称");
		headMap.put("amount7", "供应商名称");
		headMap.put("amount8", "采购申请");
		headMap.put("amount9", "采购申请行");
		headMap.put("amount10", "采购订单");
		headMap.put("amount11", "采购订单行");
		headMap.put("amount12", "合同");
		headMap.put("amount13", "合同行");
		headMap.put("amount14", "物料编码");
		headMap.put("amount15", "物料名称");
		headMap.put("amount16", "采购数量");
		headMap.put("amount17", "计量单位");
		headMap.put("amount18", "中标金额");
		headMap.put("amount19", "合同签订金额");
		headMap.put("amount20", "中标时间");
		headMap.put("amount21", "合同生效时间");
		headMap.put("amount22", "是否超期");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 集中支付条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportHt05(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//集中支付条目及金额
		List<Map<String, Object>> list = exportMapper.selectExportHt005ByMonth(request);
		tableHanderByHt04("集中支付条目及金额.xls", list, response);
	}


	/**
	 * 基础固定表头 ht05 集中支付条目及金额
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByHt05(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "计划批次");
		headMap.put("amount2", "计划编号");
		headMap.put("amount3", "款项性质");
		headMap.put("amount4", "采购订单号");
		headMap.put("amount5", "发票后续凭证号");
		headMap.put("amount6", "预算付款订单号");
		headMap.put("amount7", "付款申请号");
		headMap.put("amount8", "计划/预算金额");
		headMap.put("amount9", "申请/支付金额");
		headMap.put("amount10", "采购组织");
		headMap.put("amount11", "建设单位");
		headMap.put("amount12", "项目编码");
		headMap.put("amount13", "项目名称");
		headMap.put("amount14", "支付状态");
		headMap.put("amount15", "处理状态");
		headMap.put("amount16", "付款日期");
		headMap.put("amount17", "会记年度");
		headMap.put("amount18", "未支付原因");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 非电商采购支付逾期条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportHt06(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		//非电商采购支付逾期条目及金额
		List<Map<String, Object>> list = exportMapper.selectExportHt006ByMonth(request);
		tableHanderByHt06("非电商采购支付逾期条目及金额.xls", list, response);
	}

	/**
	 * 基础固定表头 ht06
	 * <p>
	 * 非电商采购支付逾期条目及金额
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByHt06(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "框架协议号");
		headMap.put("amount2", "框架协议行项目号");
		headMap.put("amount3", "经法合同号");
		headMap.put("amount4", "执行比例（%）");
		headMap.put("amount5", "招标计划批次");
		headMap.put("amount6", "招标计划年度");
		headMap.put("amount7", "中标日期");
		headMap.put("amount8", "中标框架金额（含税）");
		headMap.put("amount9", "合同金额（含税）");
		headMap.put("amount10", "已支付金额");

		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}


	/**
	 * 设备抽检
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj01(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj001ByMonth(request);
		tableHanderByZj01("设备抽检.xls", list, response);
	}

	/**
	 * 抽检合格率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj02(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj002ByMonth(request);
		tableHanderByZj01("抽检合格率.xls", list, response);
	}

	/**
	 * 基础固定表头 zj01
	 * 设备抽检
	 * 抽检合格率
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZj01(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "年份");
		headMap.put("amount2", "检测完成日期");
		headMap.put("amount3", "计划编号");
		headMap.put("amount4", "任务编号");
		headMap.put("amount5", "物资类别");
		headMap.put("amount6", "采购订单");
		headMap.put("amount7", "行项目");
		headMap.put("amount8", "物料描述");
		headMap.put("amount9", "基本计量单位");
		headMap.put("amount10", "合同数量");
		headMap.put("amount11", "抽检数量");
		headMap.put("amount12", "已检数量");
		headMap.put("amount13", "抽检规格");
		headMap.put("amount14", "检测结果");
		headMap.put("amount15", "不合格等级");
		headMap.put("amount16", "不合格数量");
		headMap.put("amount17", "检测状态");
		headMap.put("amount18", "复检状态");
		headMap.put("amount19", "工程名称");
		headMap.put("amount20", "供应商编码");
		headMap.put("amount21", "供应商名称");
		headMap.put("amount22", "A类数量");
		headMap.put("amount23", "B类数量");
		headMap.put("amount24", "C类数量");
		headMap.put("amount25", "采购订单数量");
		headMap.put("amount26", "实际完成时间");
		headMap.put("amount27", "建设单位");
		headMap.put("amount28", "是否重点工程");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 平均抽检总时长
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj03(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj003ByMonth(request);
		tableHanderByZj02("平均抽检总时长.xls", list, response);
	}

	/**
	 * 平均取样时长
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj04(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj004ByMonth(request);
		tableHanderByZj02("平均取样时长.xls", list, response);
	}

	/**
	 * 平均检测时长
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj05(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj005ByMonth(request);
		tableHanderByZj02("平均检测时长.xls", list, response);
	}

	/**
	 * A类任务平均抽检总时长
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj06(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj006ByMonth(request);
		tableHanderByZj02("A类任务平均抽检总时长.xls", list, response);
	}

	/**
	 * B类任务平均抽检总时长
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj07(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj007ByMonth(request);
		tableHanderByZj02("B类任务平均抽检总时长.xls", list, response);
	}

	/**
	 * C类任务平均抽检总时长
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj08(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj008ByMonth(request);
		tableHanderByZj02("C类任务平均抽检总时长.xls", list, response);
	}


	/**
	 * 基础固定表头 zj02
	 * 平均抽检总时长
	 * 平均取样时长
	 * 平均检测时长
	 * A类任务平均抽检总时长
	 * B类任务平均抽检总时长
	 * C类任务平均抽检总时长
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZj02(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "物资类别");
		headMap.put("amount2", "任务下达时间");
		headMap.put("amount3", "取样日期");
		headMap.put("amount4", "收样日期");
		headMap.put("amount5", "检测完成时间");
		headMap.put("amount6", "实际完成时间");
		headMap.put("amount7", "任务编号");
		headMap.put("amount8", "计划编号");
		headMap.put("amount9", "采购订单");
		headMap.put("amount10", "行项目");
		headMap.put("amount11", "物料描述");
		headMap.put("amount12", "基本计量单位");
		headMap.put("amount13", "合同数量");
		headMap.put("amount14", "抽检数量");
		headMap.put("amount15", "已检数量");
		headMap.put("amount16", "不合格数量");
		headMap.put("amount17", "C类抽检数量");
		headMap.put("amount18", "B类抽检数量");
		headMap.put("amount19", "A类抽检数量");
		headMap.put("amount20", "检测状态");
		headMap.put("amount21", "抽检规格");
		headMap.put("amount22", "检测结果");
		headMap.put("amount23", "工程名称");
		headMap.put("amount24", "供应商名称");

		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 设备监造
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj09(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj009ByMonth(request);
		tableHanderByZj03("设备监造.xls", list, response);
	}


	/**
	 * 基础固定表头 zj03
	 * 设备监造
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZj03(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "任务编号");
		headMap.put("amount2", "任务名称");
		headMap.put("amount3", "任务状态");
		headMap.put("amount4", "物料描述");
		headMap.put("amount5", "工程信息维护");
		headMap.put("amount6", "监造单位");
		headMap.put("amount7", "工程名称");
		headMap.put("amount8", "任务进度");
		headMap.put("amount9", "供应商名称");
		headMap.put("amount10", "开工日期");
		headMap.put("amount11", "电压等级");
		headMap.put("amount12", "是否国网任务物资");
		headMap.put("amount13", "供应商编码");
		headMap.put("amount14", "完工时间");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 不良行为
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj10(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj010ByMonth(request);
		tableHanderByZj04("不良行为.xls", list, response);
	}


	/**
	 * 基础固定表头 zj04
	 * 不良行为
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZj04(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "供应商名称");
		headMap.put("amount2", "不良行为描述");
		headMap.put("amount3", "处理措施");
		headMap.put("amount4", "处理范围");
		headMap.put("amount5", "供应商编码");
		headMap.put("amount6", "导入时间");
		headMap.put("amount7", "处理季度");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 非电网零星物资人工评价率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportZj11(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportZj011ByMonth(request);
		tableHanderByZj05("非电网零星物资人工评价率.xls", list, response);
	}


	/**
	 * 基础固定表头 zj05
	 * 非电网零星物资人工评价率
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByZj05(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "工厂名称");
		headMap.put("amount2", "订单数量");
		headMap.put("amount3", "已评价订单数量");
		headMap.put("amount4", "人工评价订单数量");
		headMap.put("amount5", "系统默认评价订单数量");
		headMap.put("amount6", "待评价订单数量");
		headMap.put("amount7", "人工评价率");
		headMap.put("amount8", "导入时间");
		headMap.put("amount9", "工厂编码");
		headMap.put("amount10", "工厂简称");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 物资类批次计划条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh01(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh001ByMonth(request);
		tableHanderByJh01("物资类批次计划条目及金额.xls", list, response);
	}

	/**
	 * 物资类协议库存计划条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh02(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh002ByMonth(request);
		tableHanderByJh01("物资类协议库存计划条目及金额.xls", list, response);
	}

	/**
	 * 物资类框架计划条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh03(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh003ByMonth(request);
		tableHanderByJh01("物资类框架计划条目及金额.xls", list, response);
	}

	/**
	 * 服务类批次计划条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh04(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh004ByMonth(request);
		tableHanderByJh01("服务类批次计划条目及金额.xls", list, response);
	}

	/**
	 * 服务类框架计划条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh05(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh005ByMonth(request);
		tableHanderByJh01("服务类框架计划条目及金额.xls", list, response);
	}

	/**
	 * 基础固定表头 jh01
	 * 物资类批次计划条目及金额
	 * 物资类协议库存计划条目及金额
	 * 物资类框架计划条目及金额
	 * 服务类批次计划条目及金额
	 * 服务类框架计划条目及金额
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByJh01(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "批次号");
		headMap.put("amount2", "采购申请号");
		headMap.put("amount3", "采购申请行项目");
		headMap.put("amount4", "申报截止日期");
		headMap.put("amount5", "发布公告时间");
		headMap.put("amount6", "操作日期");
		headMap.put("amount7", "采购申请金额");
		headMap.put("amount8", "计划类型");
		headMap.put("amount9", "计划上报类型");
		headMap.put("amount10", "需求单位编号");
		headMap.put("amount11", "需求单位名称");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 协议库存匹配计划条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh06(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh006ByMonth(request);
		tableHanderByJh02("协议库存匹配计划条目及金额.xls", list, response);
	}

	/**
	 * 基础固定表头 jh02
	 * 协议库存匹配计划条目及金额
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByJh02(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "招标批次");
		headMap.put("amount2", "协议库存编号");
		headMap.put("amount3", "协议库存行项目");
		headMap.put("amount4", "物料小类");
		headMap.put("amount5", "物资类别");
		headMap.put("amount6", "物料编码");
		headMap.put("amount7", "物料描述");
		headMap.put("amount8", "采购申请号");
		headMap.put("amount9", "申请行项目");
		headMap.put("amount10", "采购申请数量");
		headMap.put("amount11", "计量单位");
		headMap.put("amount12", "供应商编码");
		headMap.put("amount13", "供应商名称");
		headMap.put("amount14", "技术规范ID");
		headMap.put("amount15", "匹配日期");
		headMap.put("amount16", "匹配金额");
		headMap.put("amount17", "采购订单号");
		headMap.put("amount18", "采购订单行项目");
		headMap.put("amount19", "采购订单数量");
		headMap.put("amount20", "采购订单净价");
		headMap.put("amount21", "采购订单含税价");
		headMap.put("amount22", "采购订单收货金额");
		headMap.put("amount23", "采购订单收货日期");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 跨单位利库条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh07(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh007ByMonth(request);
		tableHanderByJh03("跨单位利库条目及金额.xls", list, response);
	}

	/**
	 * 基础固定表头 jh03
	 * 跨单位利库条目及金额——暂无
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByJh03(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();

		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 协议库存合同执行完成率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh08(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh008ByMonth(request);
		tableHanderByJh04("协议库存合同执行完成率.xls", list, response);
	}

	/**
	 * 基础固定表头 jh04
	 * 协议库存合同执行完成率
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByJh04(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "招标批次");
		headMap.put("amount2", "年份");
		headMap.put("amount3", "协议库存编号");
		headMap.put("amount4", "供应商编码");
		headMap.put("amount5", "供应商名称");
		headMap.put("amount6", "协议库存中标净总价");
		headMap.put("amount7", "协议库存中标含税总价");
		headMap.put("amount8", "中标日期");
		headMap.put("amount9", "生效日期");
		headMap.put("amount10", "匹配完成率");
		headMap.put("amount11", "有效开始日期");
		headMap.put("amount12", "有效截至日期");
		headMap.put("amount13", "匹配金额（申请）");
		headMap.put("amount14", "匹配金额（订单）");
		headMap.put("amount15", "匹配金额（收货）");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 物资采购计划报送准确率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh09(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh009ByMonth(request);
		tableHanderByJh05("物资采购计划报送准确率.xls", list, response);
	}

	/**
	 * 基础固定表头 jh05
	 * 物资采购计划报送准确率
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByJh05(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "批次号");
		headMap.put("amount2", "采购申请号");
		headMap.put("amount3", "采购申请行项目");
		headMap.put("amount4", "申报截止日期");
		headMap.put("amount5", "操作日期");
		headMap.put("amount6", "采购申请金额");
		headMap.put("amount7", "计划类型");
		headMap.put("amount8", "计划上报类型");
		headMap.put("amount9", "需求单位编号");
		headMap.put("amount10", "需求单位名称");
		headMap.put("amount11", "报送状态");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 物资采购及时率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh10(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh010ByMonth(request);
		tableHanderByJh06("物资采购及时率.xls", list, response);
	}

	/**
	 * 基础固定表头 jh06
	 * 物资采购及时率——暂无
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByJh06(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 平衡利库执行完成率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh11(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh011ByMonth(request);
		tableHanderByJh07("平衡利库执行完成率.xls", list, response);
	}

	/**
	 * 基础固定表头 jh07
	 * 平衡利库执行完成率——暂无
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByJh07(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}

	/**
	 * 废旧物资报废处置条目及金额
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh12(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh012ByMonth(request);
		tableHanderByJh08("废旧物资报废处置条目及金额.xls", list, response);
	}

	/**
	 * 报废处置流标率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh13(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh013ByMonth(request);
		tableHanderByJh08("报废处置流标率.xls", list, response);
	}

	/**
	 * 基础固定表头 jh08
	 * 废旧物资报废处置条目及金额
	 * 报废处置流标率
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByJh08(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "单位名称");
		headMap.put("amount2", "废旧处置计划编码");
		headMap.put("amount3", "处置计划状态");
		headMap.put("amount4", "处置类型");
		headMap.put("amount5", "报废类型");
		headMap.put("amount6", "物料编码");
		headMap.put("amount7", "物料描述");
		headMap.put("amount8", "物料小类");
		headMap.put("amount9", "物料小类描述");
		headMap.put("amount10", "评估价值");
		headMap.put("amount11", "中标金额");
		headMap.put("amount12", "合同金额");
		headMap.put("amount13", "数量");
		headMap.put("amount14", "计量单位");
		headMap.put("amount15", "竞拍批次");
		headMap.put("amount16", "竞价计划编码");
		headMap.put("amount17", "竞价计划名称");
		headMap.put("amount18", "竞价日期");
		headMap.put("amount19", "竞价状态");
		headMap.put("amount20", "包号");
		headMap.put("amount21", "包名称");
		headMap.put("amount22", "回收商编号");
		headMap.put("amount23", "回收商名称");
		headMap.put("amount24", "合同编码");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}


	/**
	 * 报废处置溢价率
	 *
	 * @param response
	 * @return
	 * @throws ProjException
	 */
	public void exportJh14(SmartReportSecondPO request, HttpServletResponse response) throws ProjException {
		ArrayList<Map<String, Object>> list = exportMapper.selectExportJh014ByMonth(request);
		tableHanderByJh09("报废处置溢价率.xls", list, response);
	}

	/**
	 * 基础固定表头 jh09
	 * 报废处置溢价率
	 *
	 * @param filename
	 * @param list
	 * @param response
	 */
	public void tableHanderByJh09(String filename, List<Map<String, Object>> list, HttpServletResponse response) {
		Map<String, String> headMap = new LinkedHashMap();
		Date d2 = new Date();
		headMap.put("amount1", "竞价批次");
		headMap.put("amount2", "竞价事件名称");
		headMap.put("amount3", "竞价计划名称");
		headMap.put("amount4", "包号");
		headMap.put("amount5", "包名称");
		headMap.put("amount6", "底价");
		headMap.put("amount7", "成交价格");
		headMap.put("amount8", "溢价率");
		headMap.put("amount9", "回收商编号");
		headMap.put("amount10", "回收商名称");
		headMap.put("amount11", "竞价日期");
		System.out.println("正在导出xlsx....");
		try {
			ExcelUtil.writeExcel(response, filename, headMap, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("共" + list.size() + "条数据,执行" + (new Date().getTime() - d2.getTime()) + "ms");
	}


}
