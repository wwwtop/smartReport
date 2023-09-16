package com.proj.web.service.impl;

import com.proj.model.bo.request.ReportRequest;
import com.proj.model.vo.*;
import com.proj.web.mapper.MainBadyMapper;
import com.proj.web.service.MainBadyService;
import com.proj.web.util.AccessoryUtils;
import com.proj.web.util.BigDecimalUtil;
import com.proj.web.util.QuartUtil;
import com.proj.web.util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

import static com.proj.web.util.ReflectionExample.printValues;

/**
 * 正文
 *
 * @author dong.ning
 */
@Service
public class MainBadyServiceImpl implements MainBadyService {
    /*
    一级月报正文
     */
    @Resource
    MainBadyMapper mainBadyMapper;




    /**
     * 获取正文
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> getMainBadyList(ReportRequest request) {
        SmartReportIndexVO vo = new SmartReportIndexVO();
        MainBadyVO badyVO = new MainBadyVO();
        BigDecimal mainBady001 = mainBadyMapper.selectMainBady001(request);
        BigDecimal mainBady002 = mainBadyMapper.selectMainBady002(request);
        BigDecimal mainBady003 = mainBadyMapper.selectMainBady003(request);//空
        BigDecimal mainBady004 = mainBadyMapper.selectMainBady004(request);
        Integer mainBady005 = mainBadyMapper.selectMainBady005(request);//空

        Integer mainBady006 = mainBadyMapper.selectMainBady006(request);//空
        BigDecimal mainBady007 = mainBadyMapper.selectMainBady007(request);//空

        BigDecimal mainBady008 = mainBadyMapper.selectMainBady008(request);
        BigDecimal mainBady009 = mainBadyMapper.selectMainBady009(request);//空
        BigDecimal mainBady010 = mainBadyMapper.selectMainBady010(request);
        badyVO.setB_a_jzcg(BigDecimalUtil.getExchangeRate(mainBady001, 10000));//本月，北京公司集中采购{b_a_jzcg}万元
        badyVO.setB_a_wzht(String.valueOf(mainBady002));//签订物资合同{b_a_wzht}万元
        badyVO.setB_a_dh(BigDecimalUtil.getExchangeRate(mainBady003, 10000));//到货{b_a_dh}万元
        badyVO.setB_a_zjzf(BigDecimalUtil.getExchangeRate(mainBady004, 10000));//资金支付{b_a_zjzf}万元
        badyVO.setB_a_jzrw(String.valueOf(mainBady005));//开展物资监造任务{b_a_jzrw}项
        badyVO.setB_a_jc(String.valueOf(mainBady006));//检测{b_a_jc}件
        BigDecimal decimal = mainBady007.multiply(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        badyVO.setB_a_cjhgl(String.valueOf(decimal));//抽检合格率{b_a_cjhgl}%
        badyVO.setB_a_wzje(BigDecimalUtil.getExchangeRate(mainBady008, 10000));//库存物资金额{b_a_wzje}万元
        badyVO.setB_a_pswz(BigDecimalUtil.getExchangeRate(mainBady009, 10000));//开展主动配送物资{b_a_pswz}万元
        badyVO.setB_a_wzcz(BigDecimalUtil.getExchangeRate(mainBady010, 10000));//报废物资处置{b_a_wzcz}万元。

        badyVO.setB_b_fx("*");//本月基础数据共发现{b_b_fx}项数据波动较大
        badyVO.setB_b_zs("*");//占基础数据总数的{b_b_zs}%
        badyVO.setB_b_ts("*");//本月监控指标中，{b_b_ts}项有所提升
        badyVO.setB_b_xj("*");//{b_b_xj}项有所下降,总体变化趋势**。

        badyVO.setB_b_ts("*");//本月监控指标中，{b_b_ts}项有所提升，
        badyVO.setB_b_xj("*");//{b_b_xj}项有所下降,总体变化趋势**。

        badyVO.setB_c_bjgs("*");//本月，对北京公司{b_c_bjgs}项指标进行监控
        badyVO.setB_c_ff("*");//共分发{b_c_ff}项指标
        badyVO.setB_c_yj("*");//{b_c_yj}条预警
        badyVO.setB_c_ywbm("*");//从分发对象看，物资公司各业务部门{b_c_ywbm}条
        badyVO.setB_c_yyzx("*");//各三级运营中心{b_c_yyzx}条
        badyVO.setB_c_tsjd("*");//从风险等级看，“提示”阶段{b_c_tsjd}条，
        badyVO.setB_c_yqjd("*");//“逾期”阶段{b_c_yqjd}条
        badyVO.setB_c_yjbj("*");//从办结情况看，完成预警办结{b_c_yjbj}条
        badyVO.setB_c_bhbj("*");//其中完成业务闭环办结{b_c_bhbj}条

        badyVO.setB_c_by1("*");//本月“{b_c_by1}”预警最多
        badyVO.setB_c_yj1("*");//占预警总数的{b_c_yj1}%
        badyVO.setB_c_by2("*");//{b_c_by2}”
        badyVO.setB_c_yj2("*");//占预警总数的{b_c_yj2}%
        badyVO.setB_c_by3("*");//{b_c_by3}
        badyVO.setB_c_yj3("*");//占预警总数的{b_c_yj3}%

        badyVO.setB_c_tzg("*");//铜价最高{b_c_tzg}元/吨
        badyVO.setB_c_tzd("*");//最低{b_c_tzd}元/吨
        badyVO.setB_c_ty("*");//截至{b_c_ty}月
        badyVO.setB_c_tr("*");//{b_c_tr}日
        badyVO.setB_c_tjg("*");//价格为{b_c_tjg}元/吨
        badyVO.setB_c_tszxd("*");//较去年同期上涨/下跌{b_c_tszxd}%。
        badyVO.setB_c_lzg("*");//铝价最高{b_c_lzg}元/吨
        badyVO.setB_c_lzd("*");//最低{b_c_lzd}元/吨
        badyVO.setB_c_ly("*");//截至{b_c_ly}月
        badyVO.setB_c_lr("*");//{b_c_lr}日
        badyVO.setB_c_ljg("*");//价格为{b_c_ljg}元/吨
        badyVO.setB_c_lszxd("*");//较去年同期上涨/下跌{b_c_lszxd}%。
        List<String> list = printValues(badyVO);
        System.out.println(list);

        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put("mainBady" + (i + 1), list.get(i));
        }
        System.out.println(map);
        return map;
    }

    /**
     * 折线图
     *
     * @param request
     */
    @Override
    public UndulationIndexDataVO getUndulation(ReportRequest request) throws ParseException {
//        Integer days = TimeUtil.getDaysByTime(request.getStartTime());//当前天数
        String year = TimeUtil.getyear(request.getStartTime());//当前年
        Integer month = TimeUtil.getMonth(request.getStartTime());//当前月
        UndulationIndexDataVO vo = new UndulationIndexDataVO();
        String lastTime = TimeUtil.lastTime(year);
        List<UndulationVO> vos1 = mainBadyMapper.selectUndulation003(year,lastTime);
        LinkedList<UndulationVO> linkedList1 = getNewUndulationList(vos1,request);
        vo.setCopperUndulationList(linkedList1);//铜
        List<UndulationVO> vos2 = mainBadyMapper.selectUndulation004(year,lastTime);
        LinkedList<UndulationVO> linkedList2 = getNewUndulationList(vos2,request);
        vo.setAluminiumUndulationList(linkedList2);//铝

        List<UndulationVO> vos3 = mainBadyMapper.selectUndulation005(year,lastTime);
        LinkedList<UndulationVO> linkedList3 = getNewUndulationList(vos3,request);
        vo.setSteelUndulationList(linkedList3);//钢
        return vo;
    }

    //实时查询折线图正文
    @Override
    public UndulationIndexDataVO getUndulationMain(ReportRequest request) throws ParseException {
        String times=request.getTime();//本年
        String lastTime = TimeUtil.lastTime(times);//去年
        UndulationIndexDataVO vo = new UndulationIndexDataVO();
        UndulationMainBadyVO badyVO = new UndulationMainBadyVO();

        //铜价格
        List<LinkedHashMap> maps2 = mainBadyMapper.selectUndulation002(request);//正文铜价最大最小
        BigDecimal maxCopper = AccessoryUtils.getActualValueByP(maps2, "1");//最大
        BigDecimal minCopper = AccessoryUtils.getActualValueByP(maps2, "2");//最小
        BigDecimal copperPrice = mainBadyMapper.selectUndulation002p1(times);//当前本月
        if (copperPrice==null){//如果查询当前铜价为null则证明当前月没有录入数据取上月铜价
             copperPrice = mainBadyMapper.selectUndulation002p2(times);//当前上月
            if (copperPrice==null){
                copperPrice=BigDecimal.ZERO;
            }
        }
        BigDecimal copperPriceLast = mainBadyMapper.selectUndulation002p1(lastTime);//去年
        if (copperPriceLast==null){
            copperPriceLast = mainBadyMapper.selectUndulation002p2(lastTime);//当前
            if (copperPriceLast==null){
                copperPriceLast=BigDecimal.ZERO;
            }
        }
        BigDecimal yoyCopper = BigDecimalUtil.getYoy(copperPrice, copperPriceLast);
        String copper = QuartUtil.udByMain(yoyCopper, "");



        //铝价格
        List<LinkedHashMap> maps1 = mainBadyMapper.selectUndulation001(request);
        BigDecimal maxAluminium = AccessoryUtils.getActualValueByP(maps1, "1");//最大
        BigDecimal minAluminium = AccessoryUtils.getActualValueByP(maps1, "2");//最小
        BigDecimal aluminiumPrice = mainBadyMapper.selectUndulation001p1(times);//当前铝价格
        if (aluminiumPrice==null){//如果查询当前铝价为null则证明当前月没有录入数据取上月铝价
            aluminiumPrice = mainBadyMapper.selectUndulation001p2(times);//当前上月铝价格
            if (aluminiumPrice==null){
                aluminiumPrice=BigDecimal.ZERO;
            }
        }
        BigDecimal aluminiumPriceLast = mainBadyMapper.selectUndulation001p1(lastTime);//去年
        if (aluminiumPriceLast==null){
            aluminiumPriceLast = mainBadyMapper.selectUndulation001p2(lastTime);//当前
            if (aluminiumPriceLast==null){
                aluminiumPriceLast=BigDecimal.ZERO;
            }
        }
        BigDecimal yoyAluminium = BigDecimalUtil.getYoy(aluminiumPrice, aluminiumPriceLast);
        String aluminium = QuartUtil.udByMain(yoyAluminium, "");


        //角钢
        List<LinkedHashMap> maps3 = mainBadyMapper.selectUndulation006(request);
        BigDecimal maxSteel = AccessoryUtils.getActualValueByP(maps3, "1");//最大
        BigDecimal minSteel = AccessoryUtils.getActualValueByP(maps3, "2");//最小
        BigDecimal SteelPrice = mainBadyMapper.selectUndulation006p1(times);//当前
        if (SteelPrice==null){
            SteelPrice = mainBadyMapper.selectUndulation006p2(times);//当前
            if (SteelPrice==null){
                SteelPrice=BigDecimal.ZERO;
            }
        }
        BigDecimal SteelPriceLast = mainBadyMapper.selectUndulation006p1(lastTime);//去年
        if (SteelPriceLast==null){
            SteelPriceLast = mainBadyMapper.selectUndulation006p2(lastTime);//当前
            if (SteelPriceLast==null){
                SteelPriceLast=BigDecimal.ZERO;
            }
        }
        BigDecimal yoySteel= BigDecimalUtil.getYoy(SteelPrice, SteelPriceLast);
        String teel = QuartUtil.udByMain(yoySteel, "");

        badyVO.setB_c_tzg(String.valueOf(maxCopper.setScale(2,BigDecimal.ROUND_HALF_UP)));
        badyVO.setB_c_tzd(String.valueOf(minCopper.setScale(2,BigDecimal.ROUND_HALF_UP)));
        badyVO.setB_c_tjg(String.valueOf(copperPrice.setScale(2,BigDecimal.ROUND_HALF_UP)));
        badyVO.setB_c_tszxd(copper);

        badyVO.setB_c_lzg(String.valueOf(maxAluminium.setScale(2,BigDecimal.ROUND_HALF_UP)));
        badyVO.setB_c_lzd(String.valueOf(minAluminium.setScale(2,BigDecimal.ROUND_HALF_UP)));
        badyVO.setB_c_ljg(String.valueOf(aluminiumPrice.setScale(2,BigDecimal.ROUND_HALF_UP)));
        badyVO.setB_c_lszxd(aluminium);

        badyVO.setB_c_jzg(String.valueOf(maxSteel.setScale(2,BigDecimal.ROUND_HALF_UP)));
        badyVO.setB_c_jzd(String.valueOf(minSteel.setScale(2,BigDecimal.ROUND_HALF_UP)));
        badyVO.setB_c_jjg(String.valueOf(SteelPrice.setScale(2,BigDecimal.ROUND_HALF_UP)));
        badyVO.setB_c_jszxd(teel);

        vo.setUndulationMainBadyVO(badyVO);
        return vo;
    }

    @Override
    public Map<String, Object> getSecondaryWarning(ReportRequest request) {
        Map<String, Object> map = new HashMap<>();
        int i=1;

        Integer secondaryBasic002 = mainBadyMapper.selectSecondaryBasic002(request);
        Integer secondaryBasic003 = mainBadyMapper.selectSecondaryBasic003(request);
        Integer secondaryBasic004 = mainBadyMapper.selectSecondaryBasic004(request);
        Integer secondaryBasic005 = mainBadyMapper.selectSecondaryBasic005(request);
        Integer secondaryBasic006 = mainBadyMapper.selectSecondaryBasic006(request);
        Integer secondaryBasic007 = mainBadyMapper.selectSecondaryBasic007(request);
        Integer secondaryBasic008 = mainBadyMapper.selectSecondaryBasic008(request);
        Integer secondaryBasic009 = mainBadyMapper.selectSecondaryBasic009(request);

        map.put("secondaryWarn"+i++,"11");
        map.put("secondaryWarn"+i++,secondaryBasic002);
        map.put("secondaryWarn"+i++,secondaryBasic003);
        map.put("secondaryWarn"+i++,secondaryBasic004);
        map.put("secondaryWarn"+i++,secondaryBasic005);
        map.put("secondaryWarn"+i++,secondaryBasic006);
        map.put("secondaryWarn"+i++,secondaryBasic007);
        map.put("secondaryWarn"+i++,secondaryBasic008);
        map.put("secondaryWarn"+i++,secondaryBasic009);




        List<LinkedHashMap> maps = mainBadyMapper.selectSecondaryAnalyse001(request);
        for (LinkedHashMap lis:maps){
            Object zbmc = lis.get("指标名称");
            map.put("secondaryWarn"+i++,zbmc);
            Object zb = lis.get("占比");
            map.put("secondaryWarn"+i++,zb+"%");
        }
        return map;
    }


    public LinkedList<UndulationVO> getNewUndulationList(List<UndulationVO> list,ReportRequest request) {
        //1.获取今年和前年所有数据 并进行排序 从大到小
        //2.获取当前日期及前十一个月
        //3.判断当前日期是否在数据集中，如果不在 取
        LinkedList<UndulationVO> test1=getNewDateByOrdDate(request);//获取新的数据集
        for (int i = 0; i < test1.size(); i++) {
            String rawmaterialdate = test1.get(i).getRawmaterialdate();//获取日期1
            for (int y = 0; y < list.size(); y++) {
                String rawmaterialdate1 = list.get(y).getRawmaterialdate();//获取日期2
                if (rawmaterialdate.equals(rawmaterialdate1)) {//日期进行比较
                    test1.get(i).setRawmaterialprice(list.get(y).getRawmaterialprice());
                    break;
                } else {
                    if (i != 0) {
                        test1.get(i).setRawmaterialprice(test1.get(i - 1).getRawmaterialprice());
                    } else {
                        test1.get(i).setRawmaterialprice(list.get(0).getRawmaterialprice());
                    }
                }
            }
        }
        for (UndulationVO t : test1) {
            System.out.println(t);
        }
        return test1;
    }

    /**
     * 获取12月日期
     *
     * @param request
     * @return
     */
    public LinkedList<UndulationVO> getNewDateByOrdDate(ReportRequest request) {
        LinkedList<UndulationVO> vo = new LinkedList<>();
        Integer days = TimeUtil.getDaysByTime(request.getStartTime());//当月天数
        Integer month = Integer.valueOf(request.getMonth());//获取当前月
        Integer year = Integer.valueOf(request.getYear());//获取当前年
        Integer month1 = TimeUtil.getMonth(request.getTime());
        if (days < 10) {
            UndulationVO undulation1 = new UndulationVO();
            undulation1.setRawmaterialdate(year + "-0" + month + "-0" + days);
            vo.addLast(undulation1);
            for (int i=1;i<12;i++){
                UndulationVO undulation = new UndulationVO();
                if (month-1>0){
                    month=month-1;
                    if (month<10){
                        undulation.setRawmaterialdate(year + "-0" + month + "-0" + days);
                        vo.addLast(undulation);
                    }else {
                        undulation.setRawmaterialdate(year + "-" + month + "-0" + days);
                        vo.addLast(undulation);
                    }
                }else {
                    year = year - 1;
                    month = 12;
                    if (month<10){
                        undulation.setRawmaterialdate(year + "-0" + month + "-0" + days);
                        vo.addLast(undulation);
                    }else {
                        undulation.setRawmaterialdate(year + "-" + month + "-0" + days);
                        vo.addLast(undulation);
                    }
                }
            }
        } else {
            UndulationVO undulation1 = new UndulationVO();
            undulation1.setRawmaterialdate(year + "-0" + month + "-" + days);
            vo.addLast(undulation1);
            for (int i=1;i<12;i++){
                UndulationVO undulation = new UndulationVO();
                if (month-1>0){
                    month=month-1;
                    if (month<10){
                        undulation.setRawmaterialdate(year + "-0" + month + "-" + days);
                        vo.addLast(undulation);
                    }else {
                        undulation.setRawmaterialdate(year + "-" + month + "-" + days);
                        vo.addLast(undulation);
                    }
                }else {
                    year = year - 1;
                    month = 12;
                    if (month<10){
                        undulation.setRawmaterialdate(year + "-0" + month + "-" + days);
                        vo.addLast(undulation);
                    }else {
                        undulation.setRawmaterialdate(year + "-" + month + "-" + days);
                        vo.addLast(undulation);
                    }
                }
            }
        }
        return vo;
    }

}
