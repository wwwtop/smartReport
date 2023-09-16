package com.proj.core.utils;

import cn.hutool.core.collection.CollectionUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询相关辅助工具类
 *
 * @author dong.ning
 */
public class QueryUtil {

    /**
     * list直接分页
     * <p>
     * 遵循jdk1.8、stream原则
     *
     * @param list
     * @param pageIndex
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> List<T> getPagerListByCalc(List<T> list, int pageIndex, int pageSize) {
        if (CollectionUtil.isEmpty(list)) {
            return new LinkedList<>();
        }

        PagerUtil pagerUtil = new PagerUtil(pageIndex, pageSize, list.size());
        try {
            return list.stream()
                    .skip(pagerUtil.getLimitOffsetSkip())
                    .limit(pageSize)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
        }
        return new LinkedList<>();
    }

}
