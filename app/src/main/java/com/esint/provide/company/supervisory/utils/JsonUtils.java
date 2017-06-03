package com.esint.provide.company.supervisory.utils;

import com.esint.provide.company.supervisory.bean.JsonMessage;
import com.esint.provide.company.supervisory.bean.JsonResult;
import com.esint.provide.company.supervisory.bean.NotificationInfo;
import com.google.gson.Gson;

/**
 * Created by MX on 2017/6/3.
 * json解析
 */

public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName();
    private static JsonUtils instance;

    private JsonUtils() {

    }

    public static JsonUtils getInstance() {
        if (null == instance)
            instance = new JsonUtils();
        return instance;
    }

    /**
     * 获得透传通知
     *
     * @param json
     * @return
     * @throws Exception
     */
    public NotificationInfo getNotification(String json) throws Exception {
        return new Gson().fromJson(json, NotificationInfo.class);
    }

    /**
     * 获得信息内容
     *
     * @param json
     * @return
     * @throws Exception
     */
    public JsonMessage getMessgae(String json) throws Exception {
        return new Gson().fromJson(json, JsonMessage.class);
    }

    /**
     * 获得返回值
     *
     * @param json
     * @return
     * @throws Exception
     */
    public JsonResult getResult(String json) throws Exception {
        return new Gson().fromJson(json, JsonResult.class);
    }

}
