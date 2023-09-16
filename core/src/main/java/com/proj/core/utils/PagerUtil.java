package com.proj.core.utils;

import lombok.Getter;

/**
 * 分页辅助工具类
 * <p>
 * 只能使用本类的构造函数来完成相应数据的计算
 * <p>
 * <b>并且向后兼容</b>
 * <p></p>
 * 使用方式：调用构造函数--直接取出对应getter变量，为后续计算做好基础
 * <p></p>
 * 可支持：
 * <ol>
 *     <li>构造函数【1】，mysql的分页的limit所需要的2个参数计算</li>
 *     <li>构造函数【1】，单list或者数组，获取某分页区间的skip和limit参数计算</li>
 *     <li>构造函数【2】，图表的横纵坐标的间隔计算</li>
 * </ol>
 *
 * @author dong.ning
 */
public class PagerUtil {

    /**
     * 内部暂存。页码
     */
    private int pageIndex;

    /**
     * 内部暂存。每页分页数量
     */
    private int pageSize;

    /**
     * 内部暂存。总行数。需要提前查询
     */
    private int total;


    /**
     * 构造函数【1】，初始化，分页参数
     * <p>
     * <b><i>直接计算出结果</i></b>
     * <p>
     * 返回limitOffsetXXX对应的实际计算之后的值，可继续使用在stream，和mysql的limit
     * <p></p>
     * 受影响的返回参数：
     * <ol>
     *     <li>limitOffsetPageCount</li>
     *     <li>limitOffsetSkip</li>
     * </ol>
     *
     * @param pageIndex 当前页码
     * @param pageSize  每页数量
     * @param total     总行数
     */
    public PagerUtil(int pageIndex, int pageSize, int total) {
        if (pageSize == 0 || total == 0) {
            return;
        }

        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;

        this.calcNow();
    }

    /**
     * 构造函数【2】，初始化，分页参数，图表横纵坐标计算
     * <p>
     * for循环的执行计算skip
     * <p>
     * <b>用于横纵坐标计算</b>
     * <p>
     * <b><i>直接计算出结果</i></b>
     * <p></p>
     * 受影响的返回参数：
     * <ol>
     *     <li>forCountMax</li>
     *     <li>forCountSkip</li>
     * </ol>
     *
     * @param maxActualValue 最大实际数值，依赖于基础数据（total）
     * @param needCount      页面显示需要的最大坐标数量
     * @param isForceCount   是否强制考虑坐标初始值数量一致
     * @param isForceHasZero 是否强制考虑坐标初始值0的情况。<b><i>必须和入参{@code isForceCount = True}的时候有效</i></b>
     */
    public PagerUtil(int maxActualValue, int needCount, boolean isForceCount, boolean isForceHasZero) {
        if (maxActualValue == 0 || needCount == 0) {
            this.forCountMax = -1;
            return;
        }

        this.total = maxActualValue;
        this.pageSize = needCount;

        this.calcLoopForNow(isForceCount, isForceHasZero);
    }

    /**
     * for循环的执行计算skip
     *
     * @param isForceCount   是否强制考虑坐标初始值数量一致
     * @param isForceHasZero 是否强制考虑坐标初始值0的情况。<b><i>必须和入参{@code isForceCount = True}保持一致并且配合使用</i></b>
     */
    private void calcLoopForNow(boolean isForceCount, boolean isForceHasZero) {
        int skipEvery = this.total / this.pageSize;

        if (isForceCount) {
            if (this.total % this.pageSize != 0) {
                --this.pageSize;
                if (isForceHasZero) {
                    --this.pageSize;
                }
                skipEvery = this.total / this.pageSize;
            }
        }

        while (this.total % this.pageSize != 0) {
            skipEvery = (++this.total) / this.pageSize;
        }

        this.forCountSkip = skipEvery;
        this.forCountMax = this.total;
    }

    /**
     * 分页计算
     */
    private void calcNow() {
        this.limitOffsetPageCount = this.total / this.pageSize;
        if (this.total % this.pageSize != 0) {
            this.limitOffsetPageCount++;
        }

        this.limitOffsetSkip = (this.pageIndex - 1) * this.pageSize;
        if (this.limitOffsetSkip < 0) {
            this.limitOffsetSkip = 0;
        }
    }

    /**
     * 计算之后的，整体偏移量。跳过多少行。可用在mysql-limit
     */
    @Getter
    private int limitOffsetSkip;

    /**
     * 计算之后的，整体偏移量。总页数
     */
    @Getter
    private int limitOffsetPageCount;

    /**
     * 计算之后的，用于横纵坐标计算。最大节点，可用于在for循环第二段的小于（或者是小于等于）的判断
     */
    @Getter
    private int forCountMax;

    /**
     * 计算之后的，用于横纵坐标计算。最大节点，可用于在for循环第三段的间隔值
     */
    @Getter
    private int forCountSkip;

}
