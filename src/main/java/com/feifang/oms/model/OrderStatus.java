package com.feifang.oms.model;

/**
 * 订单状态
 * @author Vincent
 * @date 2017/8/27
 */
public enum OrderStatus {

    /**
     * 初始化
     */
    INI(0),

    /**
     * 订单进行中
     */
    ORDERING(1),

    /**
     * 已完成
     */
    ORDERED(2),

    /**
     * 已取消
     */
    CANNEL(3);

    private int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
