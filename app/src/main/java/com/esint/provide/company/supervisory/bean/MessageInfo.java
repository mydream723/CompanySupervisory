package com.esint.provide.company.supervisory.bean;

import java.io.Serializable;

/**
 * Created by MX on 2017/6/3.
 * 提醒信息
 */

public class MessageInfo implements Serializable{
    /**
     * 信息id
     */
    private String e_id;
    /**
     * 信息类别
     */
    private String e_type;
    /**
     * 信息类别 文字描述
     */
    private String e_type_name;
    /**
     * 信息内容
     */
    private String e_info;
    /**
     * 信息状态
     */
    private String e_status;
    /**
     * 读取时间
     */
    private String e_read_time;
    /**
     * 读取人员
     */
    private String e_read_user;
    /**
     * 添加时间
     */
    private String e_add_time;
    /**
     * 运营商id
     */
    private String com_id;

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getE_type() {
        return e_type;
    }

    public void setE_type(String e_type) {
        this.e_type = e_type;
    }

    public String getE_info() {
        return e_info;
    }

    public void setE_info(String e_info) {
        this.e_info = e_info;
    }

    public String getE_status() {
        return e_status;
    }

    public void setE_status(String e_status) {
        this.e_status = e_status;
    }

    public String getE_read_time() {
        return e_read_time;
    }

    public void setE_read_time(String e_read_time) {
        this.e_read_time = e_read_time;
    }

    public String getE_read_user() {
        return e_read_user;
    }

    public void setE_read_user(String e_read_user) {
        this.e_read_user = e_read_user;
    }

    public String getE_add_time() {
        return e_add_time;
    }

    public void setE_add_time(String e_add_time) {
        this.e_add_time = e_add_time;
    }

    public String getCom_id() {
        return com_id;
    }

    public void setCom_id(String com_id) {
        this.com_id = com_id;
    }

    public String getE_type_name() {
        return e_type_name;
    }

    public void setE_type_name(String e_type_name) {
        this.e_type_name = e_type_name;
    }
}
