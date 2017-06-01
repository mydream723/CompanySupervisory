package com.esint.communitytools.data;

/**
 * 功能URI对应关系
 * 
 * @author Administrator
 *
 */
public class URIMap {

	/**
	 * 添加用户信息
	 */
	public static final String ACCOUNT_ADDUSER = "adduser";
	/**
	 * 添加用户信息
	 */
	public static final int ACCOUNT_URI_ADDUSER = 0x1001;
	/**
	 * 获得正在使用用户信息
	 */
	public static final String ACCOUNT_GETACTIVITE = "getActiviteUser";
	/**
	 * 获得正在使用用户信息
	 */
	public static final int ACCOUNT_URI_GETACTIVITE = 0x1002;
	/**
	 * 判断用户是否存在
	 */
	public static final String ACCOUNT_ISUSEREXIST = "isUserExist";
	/**
	 * 判断用户是否存在
	 */
	public static final int ACCOUNT_URI_ISUSEREXIST = 0x1003;
	/**
	 * 用户登出(根据用户名)
	 */
	public static final String ACCOUNT_USERLOGOUTWITHNAME = "userLogoutWithName";
	/**
	 * 用户登出(根据用户名)
	 */
	public static final int ACCOUNT_URI_USERLOGOUTWITHNAME = 0x1004;
	/**
	 * 用户登出(全部用户更改为登出状态)
	 */
	public static final String ACCOUNT_USERLOGOUT = "userLogout";
	/**
	 * 用户登出(全部用户更改为登出状态)
	 */
	public static final int ACCOUNT_URI_USERLOGOUT = 0x1005;

	/**
	 * 属性值 添加运营商绑定码
	 */
	public static final String PROPERTY_ADDSERVICECODE = "addServiceCode";
	/**
	 * 属性值 判断绑定码是否存在
	 */
	public static final int PROPERTY_URI_ADDSERVICECODE = 0x2001;
	/**
	 * 属性值 解绑运营商绑定码
	 */
	public static final String PROPERTY_DELETESERVICE = "deleteService";
	/**
	 * 属性值 解绑运营商绑定码
	 */
	public static final int PROPERTY_URI_DELETESERVICE = 0x2002;

	/**
	 * 运营商 添加运营商app
	 */
	public static final String SERVICE_ADDSERVICEAPP = "addServiceApp";
	/**
	 * 运营商 添加运营商app
	 */
	public static final int SERVICE_URI_ADDSERVICEAPP = 0x3001;
	/**
	 * 运营商 删除app
	 */
	public static final String SERVICE_DELETEAPP = "deleteServiceApps";
	/**
	 * 运营商 删除app
	 */
	public static final int SERVICE_URI_DELETEAPP = 0x3002;

}
