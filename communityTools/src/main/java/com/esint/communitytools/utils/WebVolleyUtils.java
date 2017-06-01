package com.esint.communitytools.utils;

import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class WebVolleyUtils {
	private static final String TAG = "Volley";
	private static final String WEBKEY = "esint";
	private static WebVolleyUtils mWebVolleyUtils;

	private WebVolleyUtils() {

	}

	/**
	 * 初始化网络请求队�?
	 * 
	 * @param context
	 * @return
	 */
	public static RequestQueue initRequestQueue(Context context) {
		RequestQueue queue = Volley.newRequestQueue(context);
		return queue;
	}

	/**
	 * 初始化网络访问
	 * 
	 * @return
	 */
	public static WebVolleyUtils getInstants() {
		if (null == mWebVolleyUtils) {
			mWebVolleyUtils = new WebVolleyUtils();
		}
		return mWebVolleyUtils;
	}

	/**
	 * 网络请求
	 * 
	 * @param mQueue
	 *            網絡請求隊列
	 * @param url
	 *            网络请求地址
	 * @param params
	 *            网络请求参数
	 */
	public static void getHttpResult(RequestQueue mQueue, String url, final Map<String, String> params,
			final Handler handler, final int webflag) {
		// Log.d(TAG, "Method:" + params.get(Constants.URL_PARAMS_METHOD));
		mQueue.cancelAll(TAG);
		if (null != params)
			for (Map.Entry<String, String> entry : params.entrySet()) {
				Log.d(TAG, entry.getKey() + ":" + entry.getValue());
			}
		StringRequest stringRequest = new StringRequest(Method.POST, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				// 请求返回�?
				Log.d(TAG, response);
				Message msg = new Message();
				msg.what = webflag;
				msg.obj = response;
				handler.sendMessage(msg);
			}

		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// 请求错误
				if (null != error && null != error.networkResponse) {
					Log.e(TAG, new String(error.networkResponse.data));
				}
				Message msg = new Message();
				msg.what = WebConstants.WEBFLAG_ERROR;
				if (null != error)
					msg.obj = error;
				else
					msg.obj = "错误";
				handler.sendMessage(msg);
			}

		}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params;
			}

		};
		// 设置默认超时时间
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		stringRequest.setTag(TAG);
		mQueue.add(stringRequest);
	}

	/**
	 * 网络请求
	 * 
	 * @param mQueue
	 *            網絡請求隊列
	 * @param url
	 *            网络请求地址
	 * @param params
	 *            网络请求参数
	 */
	public static void getHttpResultWithTag(RequestQueue mQueue, String url, final Map<String, String> params,
			final Handler handler, final int webflag, String tag) {
		// Log.d(TAG, "Method:" + params.get(Constants.URL_PARAMS_METHOD));
		mQueue.cancelAll(tag);
		if (null != params)
			for (Map.Entry<String, String> entry : params.entrySet()) {
				Log.d(TAG, entry.getKey() + ":" + entry.getValue());
			}
		StringRequest stringRequest = new StringRequest(Method.POST, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				// 请求返回�?
				Log.d(TAG, response);
				Message msg = new Message();
				msg.what = webflag;
				msg.obj = response;
				handler.sendMessage(msg);
			}

		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// 请求错误
				if (null != error && null != error.networkResponse) {
					Log.e(TAG, new String(error.networkResponse.data));
				}
				Message msg = new Message();
				msg.what = WebConstants.WEBFLAG_ERROR;
				if (null != error)
					msg.obj = error;
				else
					msg.obj = "错误";
				handler.sendMessage(msg);
			}

		}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params;
			}

		};
		// 设置默认超时时间
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		stringRequest.setTag(tag);
		mQueue.add(stringRequest);
	}

}
