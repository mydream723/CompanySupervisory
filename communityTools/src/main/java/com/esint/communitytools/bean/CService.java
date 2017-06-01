package com.esint.communitytools.bean;

import java.util.List;

/**
 * 运营商信息
 * 
 * @author Administrator
 *
 */
public class CService {
	/**
	 * 运营商绑定码
	 */
	private String serviceCode;
	/**
	 * 运营商名称
	 */
	private String serviceName;
	/**
	 * 运营商图标
	 */
	private String serviceIcon;
	/**
	 * 运营商app列表信息
	 */
	private List<ServiceApp> serviceApps;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceIcon() {
		return serviceIcon;
	}

	public void setServiceIcon(String serviceIcon) {
		this.serviceIcon = serviceIcon;
	}

	public List<ServiceApp> getServiceApps() {
		return serviceApps;
	}

	public void setServiceApps(List<ServiceApp> serviceApps) {
		this.serviceApps = serviceApps;
	}

}
