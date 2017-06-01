package com.esint.provide.company.supervisory.utils;

/**
 * Created by MX on 2017/6/1.
 */

public class WebConstances {
    /**
     * 基础服务地址
     */
    public static final String BASE_URL = "http://jujia.zhihuixlf.com/JujiaApi/index/";
    /**
     * 获得信息列表
     */
    public static final String URL_MESSAGE_GET = "getMessage";
    /**
     * 获得信息列表 类别 1、已读 2、未读
     */
    public static final String PARAMS_GETMESSAGE_STATUS = "e_status";
    /**
     * 获得信息列表 运营商编码
     */
    public static final String PARAMS_GETMESSAGE_CODE = "com_code";
    /**
     * 获得信息列表 页码
     */
    public static final String PARAMS_GETMESSAGE_PAGENUM = "page";
    /**
     * 获得信息列表 数量
     */
    public static final String PARAMS_GETMESSAGE_PAGECOUNT = "page_nums";
    /**
     * 获得信息列表
     */
    public static final int WEBFLAG_GETMESSAGE = 0x3001;




}
