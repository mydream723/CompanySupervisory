package com.esint.communitytools.bean;

public class CCompanyUser {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 登录状态
	 */
	private int loginStutas;
	/**
	 * 绑定吗
	 */
	private String bindCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLoginStutas() {
		return loginStutas;
	}
	public void setLoginStutas(int loginStutas) {
		this.loginStutas = loginStutas;
	}
	public String getBindCode() {
		return bindCode;
	}
	public void setBindCode(String bindCode) {
		this.bindCode = bindCode;
	}
	
}
