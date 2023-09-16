package com.proj.web.entity;

import lombok.Data;

import java.util.Comparator;

/**
 * 对二级通报数据进行排序
 */
@Data
public class MainBadySecond2PO implements Comparable<MainBadySecond2PO> {
    private String city;
    private Double num;
    private Integer rank;

    @Override
    public int compareTo(MainBadySecond2PO o) {

        return o.getNum().compareTo(this.getNum());
    }
}
