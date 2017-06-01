package com.esint.communitytools.bean;

/**
 * 运营商对应
 * 
 * @author Administrator
 *
 */
public class ServiceApp {
	/**
	 * 应用名称
	 */
	private String appname;
	/**
	 * 应用编号
	 */
	private int apptype;
	/**
	 * 访问网址
	 */
	private String url;
	/**
	 * 显示名称
	 */
	private String displayname;

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public int getApptype() {
		return apptype;
	}

	public void setApptype(int apptype) {
		this.apptype = apptype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

}
