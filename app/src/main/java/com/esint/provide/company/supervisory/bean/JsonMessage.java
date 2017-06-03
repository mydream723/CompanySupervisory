package com.esint.provide.company.supervisory.bean;

/**
 * Created by MX on 2017/6/3.
 */

public class JsonMessage {
    private MessageBean msg;
    private String mark;
    private String status;

    public MessageBean getMsg() {
        return msg;
    }

    public void setMsg(MessageBean msg) {
        this.msg = msg;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
