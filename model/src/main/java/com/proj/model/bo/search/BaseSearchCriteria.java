package com.proj.model.bo.search;

import lombok.Data;

/**
 * 搜索器
 * <p>
 * 此类是父类，建议是在具体的业务模块的搜索类中，从此类继承，继续派生
 *
 * @author dong.ning
 */
@Data
public class BaseSearchCriteria {

    /**
     * 搜索当前页面的页码
     */
    private int pageNumber;

    /**
     * 搜索每页显示数量
     */
    private int pageSize;

}
