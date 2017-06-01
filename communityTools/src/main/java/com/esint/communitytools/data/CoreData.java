package com.esint.communitytools.data;

/**
 * 账户信息常量
 * 
 * @author mx
 *
 */
public class CoreData {
	/**
	 * 账户表 uri
	 */
	public static final String URI_ACCOUNT = "tb_account";
	/**
	 * 账户表 uri对应mathcher
	 */
	public static final int URI_ACCOUNT_MATHCHER = 0x1000;

	/**
	 * 属性表 uri
	 */
	public static final String URI_PROPERTY = "tb_property";
	/**
	 * 属性表UII对应mathcher
	 */
	public static final int URI_PROPERTY_MATHCHER = 0x2000;
	/**
	 * 运营商 app 信息表
	 */
	public static final String URI_SERVICE = "tb_service";
	/**
	 * 运营商 app 信息表UII对应mathcher
	 */
	public static final int URI_SERVICE_MATHCHER = 0X3000;
	/**
	 * 企业用户 uri
	 */
	public static final String URI_COMPANY_USER = "tb_company_user";
	/**
	 * 企业用户uri对应mathcher
	 */
	public static final int URI_COMPANY_USER_MATHCHER = 0X4000;
	
	
	/**
	 * 共享数据
	 */
	public static final String META_APP_AUTHOR = "ACCOUNT_AUTHOR";
	/**
	 * 主程序key
	 */
	public static final String META_APP_KEY = "ACCOUNT_APPKEY";
	/**
	 * 属性表 键
	 */
	public static final String PROPERTY_KEY = "property_key";
	/**
	 * 属性表 值
	 */
	public static final String PROPERTY_VALUE = "property_value";
	/**
	 * 属性表 对应账户id
	 */
	public static final String PROPERTY_ACCOUNTID = "account_id";
	/**
	 * 用户名
	 */
	public static final String ACCOUNT_USER = "username";
	/**
	 * 密码
	 */
	public static final String ACCOUNT_PASS = "password";
	/**
	 * 邮箱
	 */
	public static final String ACCOUNT_EMAIL = "email";
	/**
	 * 性别
	 */
	public static final String ACCOUNT_SEX = "sex";
	/**
	 * 手机号
	 */
	public static final String ACCOUNT_TEL = "telphone";
	/**
	 * 省份证号
	 */
	public static final String ACCOUNT_CARDID = "cardid";
	/**
	 * 生日
	 */
	public static final String ACCOUNT_BIRTH = "birth";
	/**
	 * 服务码
	 */
	public static final String ACCOUNT_SERVICECODE = "service_code";
	/**
	 * 状态码
	 */
	public static final String ACCOUNT_ACTIVITE = "activite";
	/**
	 * 用户id
	 */
	public static final String ACCOUNT_USERID = "user_id";
	/**
	 * 主键id
	 */
	public static final String ACCOUNT_ID = "id";
	/**
	 * 运营商app表 应用名称
	 */
	public static final String SERVICE_APPNAME = "app_name";
	/**
	 * 运营商app表 应用类型
	 */
	public static final String SERVICE_APPTYPE = "app_type";
	/**
	 * 运营商app表 访问网址
	 */
	public static final String SERVICE_URL = "app_url";
	/**
	 * 运营商app表 显示名称
	 */
	public static final String SERVICE_DISPLAYNAME = "app_displayname";

	
	/**
	 * 企业账号 id
	 */
	public static final String COMPANY_USER_ID = "id";
	/**
	 * 企业账号 用户名
	 */
	public static final String COMPANY_USER_USER = "conuser_username";
	/**
	 * 企业账号 密码
	 */
	public static final String COMPANY_USER_PASSWORD = "conuser_password";
	/**
	 * 企业账号 是否登录
	 */
	public static final String COMPANY_USER_STATUS = "conuser_islogin";
	/**
	 * 企业账号 是否登录
	 */
	public static final String COMPANY_USER_BINDCODE = "conuser_bindcode";
}
