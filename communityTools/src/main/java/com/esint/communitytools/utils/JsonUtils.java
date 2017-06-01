package com.esint.communitytools.utils;

import com.esint.communitytools.bean.JsonBindCode;
import com.esint.communitytools.bean.JsonLogin;
import com.esint.communitytools.bean.JsonResult;
import com.google.gson.Gson;

/**
 * 解析json
 * 
 * @author Administrator
 *
 */
public class JsonUtils {
	private static JsonUtils mJsonUtils;

	private JsonUtils() {

	}

	public static JsonUtils getInstance() {
		if (null == mJsonUtils) {
			mJsonUtils = new JsonUtils();
		}
		return mJsonUtils;
	}

	/**
	 * 解析请求结果
	 * 
	 * @param json
	 * @return
	 */
	public JsonResult getResult(String json) {
		return new Gson().fromJson(json, JsonResult.class);
	}

	/**
	 * 解析登录结果
	 * 
	 * @param json
	 * @return
	 */
	public JsonLogin getLogin(String json) {
		return new Gson().fromJson(json, JsonLogin.class);
	}

	/**
	 * 解析运营商绑定信息
	 * 
	 * @param json
	 * @return
	 */
	public JsonBindCode getBindService(String json) {
		return new Gson().fromJson(json, JsonBindCode.class);
	}

}
