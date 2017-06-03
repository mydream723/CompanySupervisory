package com.esint.provide.company.supervisory.bean;

import java.util.List;

/**
 * Created by MX on 2017/6/3.
 */

public class MessageBean {
    private List<MessageInfo> mesInfo;
    private MessageCount count;
    private String page_now;
    private String page_all;

    public List<MessageInfo> getMesInfo() {
        return mesInfo;
    }

    public void setMesInfo(List<MessageInfo> mesInfo) {
        this.mesInfo = mesInfo;
    }

    public MessageCount getCount() {
        return count;
    }

    public void setCount(MessageCount count) {
        this.count = count;
    }

    public String getPage_now() {
        return page_now;
    }

    public void setPage_now(String page_now) {
        this.page_now = page_now;
    }

    public String getPage_all() {
        return page_all;
    }

    public void setPage_all(String page_all) {
        this.page_all = page_all;
    }
}
