package com.esint.provide.company.supervisory.bean;

import java.io.Serializable;

/**
 * Created by MX on 2017/6/1.
 */

public class OrderInfo implements Serializable{
    /**
     * 商铺订单
     */
    public static final int TYPE_SHOP = 1;
    /**
     * 社工订单
     */
    public static final int TYPE_SOCIAL = 2;
    /**
     * 呼叫订单
     */
    public static final int TYPE_CALL = 3;

    /**
     * 订单编号
     */
    private String orderNum;
    /**
     * 订单时间
     */
    private String orderTime;
    /**
     * 订单类别 1、商城 2、社工 3、云呼叫
     */
    private int orderType;
    /**
     *  订单描述
     */
    private String orderDesc;


    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
