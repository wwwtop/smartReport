package com.proj.model.vo;

import lombok.Data;

/**
 * 波动图正文
 * @author liuyafei
 */
@Data
public class UndulationMainBadyVO {
    String b_c_tzg; //铜价最高{b_c_tzg}元/吨，
    String b_c_tzd;// 最低{b_c_tzd}元/吨。
    String b_c_tjg;// 截至{b_c_ty}月{b_c_tr}日，价格为{b_c_tjg}元/吨，
    String b_c_tszxd;// 较去年同期上涨/下跌{b_c_tszxd}%。

    String b_c_lzg;//铝价最高{b_c_lzg}元/吨，
    String b_c_lzd;// 最低{b_c_lzd}元/吨。截至{b_c_ly}月{b_c_lr}日，
    String b_c_ljg;// 价格为{b_c_ljg}元/吨，
    String b_c_lszxd;// 较去年同期上涨/下跌{b_c_lszxd}%。

    String b_c_jzg;//铝价最高{b_c_lzg}元/吨，
    String b_c_jzd;// 最低{b_c_lzd}元/吨。截至{b_c_ly}月{b_c_lr}日，
    String b_c_jjg;// 价格为{b_c_ljg}元/吨，
    String b_c_jszxd;// 较去年同期上涨/下跌{b_c_lszxd}%。

}
