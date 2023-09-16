package com.proj.model.vo.second;

import lombok.Data;

/**
 * 二级通报正文
 */
@Data
public class SecondMainBadyVO {

    /**
     * 1.esc预警问题整改率
     */
    private String a_a_pjzgl1; //本月，各单位平均整改率为{a_a_pjzgl1}%。
    private String a_a_gs1;//其中{a_a_gs1}等
    private String a_a_j;//{a_a_j}家公司
    private String a_a_pjzgl2;//整改率均为{a_a_pjzgl2}%；
    private String a_a_gs2;//{a_a_gs2}公司正确率较低，
    private String a_a_dy;//均低于{a_a_dy}%。
    /**
     * 2.盘活利库任务完成率
     */
    private String a_b_rwwcl1;//截止2月份，公司盘活利库任务完成率为{a_b_rwwcl1}%，
    private String a_b_gs1;// 其中{a_b_gs1}公司推进较快，
    private String a_a_rwwcl2;// 任务完成率在{a_a_rwwcl2}%以上；
    private String a_b_gs2;// {a_b_gs2}公司进度较慢，需加大推进力度。
    /**
     * 3.计划申报率
     */
    private String a_c_pjzql; //截止2月份，各单位平均正确率为{a_c_pjzql}%。
    private String a_c_gs1;// 其中{a_c_gs1}公司正确率较高，
    private String a_c_zql;// 为{a_c_zql}%；
    private String a_c_gs2;// {a_c_gs2}公司正确率较低，
    private String a_c_dy;// 均低于{a_c_dy}%。
    /**
     * 4.报废物资处置计划申报率
     */
    private String a_d_yjgfl;//截止2月份，各单位平均规范率为{a_d_yjgfl}%。
    private String a_d_gs1;// 其中{a_d_gs1}公司
    private String a_d_w;// 为{a_d_w}%；
    private String a_d_gs2;// {a_d_gs2}公司
    private String a_d_dy;// 低于{a_d_dy}%。
    /**
     * 5.一次采购成功率
     */
    private String a_e_yjcgl; //截止2月份，各单位平均成功率{a_e_yjcgl}%。
    private String a_e_gs1; // 其中{a_e_gs1}公司
    private String a_e_cgcgl; // 一次采购成功率为{a_e_cgcgl}%；
    private String a_e_gs2;// {a_e_gs2}公司
    private String a_e_dy;// 均低于{a_e_dy}%。
    /**
     * 6.服务类框架协议执行率
     */
    private String a_f6_pjzxl; //截止2月份，各单位平均执行率{a_f6_pjzxl}%。
    private String a_f6_gs1;// 其中，{a_f6_gs1}公司较好，
    private String a_f6_gy;// 高于{a_f6_gy}%；
    private String a_f6_gs2;// {a_f6_gs2}公司
    private String a_f6_dy;// 均低于{a_f6_dy}%。
    /**
     * 7.技术规范书退回率
     */
    private String a_f7_pjcgl1; //截止2月份，物资批次中，各单位平均成功率{a_f7_pjcgl1}%，
    private String a_f7_gs1; // 其中{a_f7_gs1}等
    private String a_f7_j; // {a_f7_j}家公司未发生技术规范退回情况；
    private String a_f7_gs2;// {a_f7_gs2}公司退回率较高，
    private String a_f7_cg1;// 超过{a_f7_cg1}%。
    private String a_f7_pjcgl2;//截止2月份，工程及服务批次中，各单位平均成功率{a_f7_pjcgl2}%，
    private String a_f7_gs3;// 其中{a_f7_gs3}公司较好，
    private String a_f7_thl;// 退回率均不到{a_f7_thl}%；
    private String a_f7_gs4;// {a_f7_gs4}公司退回率较高，
    private String a_f7_cg2;// 超过{a_f7_cg2}%。
    /**
     * 8.零星物资电商化请购总金额
     */
    private String a_g_zje;//截止2月份，各单位平均请购总金额{a_g_zje}万元，
    private String a_g_gs1;// 其中{a_g_gs1}公司
    private String a_g_cg;// 超过{a_g_cg}万元；
    private String a_g_gs2;// {a_g_gs2}公司
    private String a_g_dy;// 低于{a_g_dy}万元。

    /**
     * 9.逾期货款清理完成指数
     */
    private String a_h_gs;//截止2月份，{a_h_gs}公司存在逾期款项，
    private String a_h_kx;// 涉及款项{a_h_kx}笔，
    private String a_h_je;// 金额{a_h_je}万元，
    private String a_h_yy;// 原因为{a_h_yy}。

    /**
     * 10.供应计划完成率
     */
    private String a_i_pjwcl;//本月，各单位平均完成率为{a_i_pjwcl}%。
    private String a_i_gs1;// 其中{a_i_gs1}等
    private String a_i_j;// {a_i_j}家公司
    private String a_i_wcl;// 完成率均为{a_i_wcl}%；
    private String a_i_gs2;// {a_i_gs2}公司完成率
    private String a_i_dy;// 均低于{a_i_dy}%。

    /**
     * 11.物资供应计划调整率
     */
    private String a_j_pjtzl; //本月，各单位平均调整率为{a_j_pjtzl}%。
    private String a_j_gs1; // 其中{a_j_gs1}公司调整率较低，
    private String a_j_dy; // 均低于{a_j_dy}%；
    private String a_j_gs2; // {a_j_gs2}公司调整率较高，
    private String a_j_cg; // 超过{a_j_cg}%。

    /**
     * 12.到货交接单签署正确率
     */
    private String a_k_pjzql; //本月，各单位平均正确率为{a_k_pjzql}%。
    private String a_k_gs1; // 其中{a_k_gs1}等
    private String a_k_j;  // {a_k_j}家公司正确率较高，
    private String a_k_jw; // 均为{a_k_jw}%；
    private String a_k_gs2; // {a_k_gs2}公司正确率
    private String a_k_dy; // 低于{a_k_dy}%。

    /**
     * 13.库存积压监控
     */
    private String a_z_zyb1; //截止2月份，北京公司整体库存积压占比{a_z_zyb1}%，
    private String a_z_zyb2;  // 各二级单位平均库存积压占比{a_z_zyb2}%。
    private String a_z_gs1;  // 其中{a_z_gs1}公司库存积压占比相对较低，
    private String a_z_dy;  // 低于{a_z_dy}%；
    private String a_z_gs2;  // {a_z_gs2}公司库存积压
    private String a_z_cg;  // 占比超过{a_z_cg}%。

    /**
     * 14.非电网零星物资人工评价率
     */
    private String a_l_rgpjl; //本月，各单位平均人工评价率为{a_l_rgpjl}%。
    private String a_l_gs1; // {a_l_gs1}公司评价率较好，
    private String a_l_cg; // 超过{a_l_cg}%；
    private String a_l_gs2; // {a_l_gs2}公司评价率
    private String a_l_dy; // 低于{a_l_dy}%。

    /**
     * 15.出厂验收参与率
     */
    private String a_m_pjcyl;//本月，各单位平均参与率为{a_m_pjcyl}%。
    private String a_m_gs1;// 其中{a_m_gs1}
    private String a_m_j;// 等{a_m_j}家公司参与率较高，
    private String a_m_cg; // 超过{a_m_cg}%；
    private String a_m_gs2; // {a_m_gs2}公司
    private String a_m_dy; // 参与率低于{a_m_dy}%。
}
