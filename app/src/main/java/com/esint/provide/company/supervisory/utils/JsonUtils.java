package com.esint.provide.company.supervisory.utils;

import com.esint.provide.company.supervisory.bean.NotificationInfo;
import com.google.gson.Gson;

/**
 * Created by MX on 2017/6/3.
 * json解析
 */

public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName();
    private static JsonUtils instance;
    private JsonUtils(){

    }

    public static JsonUtils getInstance(){
        if(null == instance)
            instance = new JsonUtils();
        return instance;
    }

    /**
     * 获得透传通知
     * @param json
     * @return
     * @throws Exception
     */
    public NotificationInfo getNotification(String json) throws Exception{
        return new Gson().fromJson(json, NotificationInfo.class);
    }

}
