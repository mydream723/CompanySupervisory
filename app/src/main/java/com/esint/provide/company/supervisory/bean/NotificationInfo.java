package com.esint.provide.company.supervisory.bean;

/**
 * Created by MX on 2017/6/3.
 */

public class NotificationInfo {
    /**
     * 信息id
     */
    private String e_id;
    /**
     * 信息类型
     */
    private String e_type;
    /**
     * 信息内容
     */
    private String e_info;
    /**
     * 运营商码
     */
    private String com_code;
    private String e_type_name;
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

    public String getCom_code() {
        return com_code;
    }

    public void setCom_code(String com_code) {
        this.com_code = com_code;
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
