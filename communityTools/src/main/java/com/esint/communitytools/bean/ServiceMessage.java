package com.esint.communitytools.bean;

import java.util.List;

public class ServiceMessage {
	/**
	 * 运营商名
	 */
	private String name;
	/**
	 * 运营商图标
	 */
	private String icon;
	/**
	 * 运营商下app
	 */
	private List<ServiceApp> managers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<ServiceApp> getManagers() {
		return managers;
	}

	public void setManagers(List<ServiceApp> managers) {
		this.managers = managers;
	}

}
