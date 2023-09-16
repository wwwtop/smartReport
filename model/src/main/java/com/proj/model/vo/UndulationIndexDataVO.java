package com.proj.model.vo;



import lombok.Data;

import java.util.List;

/**
 * 实时查询-正文返回前端
 */
@Data
public class UndulationIndexDataVO {

    /**
     * 正文
     */
    public UndulationMainBadyVO undulationMainBadyVO;
    /**
     * 铜线波动图
     */
    public List<UndulationVO> copperUndulationList;

    /**
     * 铝线波动图
     */
    public List<UndulationVO> aluminiumUndulationList;


    /**
     * 角钢波动图
     */
    public List<UndulationVO> steelUndulationList;
}
