package com.esint.provide.company.supervisory.bean;

/**
 * Created by MX on 2017/6/3.
 */

public class MessageCount {
    /**
     * 已阅读人数
     */
    private String readed;
    private String unread;
    private String all;

    public String getReaded() {
        return readed;
    }

    public void setReaded(String readed) {
        this.readed = readed;
    }

    public String getUnread() {
        return unread;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
