package com.esint.communitytools.bean;

/**
 * 登录信息
 * 
 * @author Administrator
 *
 */
public class JsonLogin {
	/**
	 * 返回值
	 */
	private int code;
	/**
	 * 返回登录信息
	 */
	private LoginContent content;
	/**
	 * 错误信息
	 */
	private String error;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public LoginContent getContent() {
		return content;
	}

	public void setContent(LoginContent content) {
		this.content = content;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
