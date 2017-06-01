package com.esint.communitytools.bean;

/**
 * 请求返回值
 * 
 * @author Administrator
 *
 */
public class JsonResult {
	/**
	 * 返回码 0:错误; 1:成功
	 */
	private int code;
	/**
	 * 正确返回值
	 */
	private String content;
	/**
	 * 错误返回值
	 */
	private String error;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
