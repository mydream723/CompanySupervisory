package com.esint.communitytools.bean;

public class JsonBindCode {
	/**
	 * 返回码
	 */
	private int code;
	private ServiceMessage content;
	private String error;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ServiceMessage getContent() {
		return content;
	}

	public void setContent(ServiceMessage content) {
		this.content = content;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
