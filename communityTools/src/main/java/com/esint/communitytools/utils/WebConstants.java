package com.esint.communitytools.utils;

public class WebConstants {
	/**
	 * 基础地址
	 */
	public static final String URL = "http://123.56.136.158:11110";
	/**
	 * 未绑定运营商时默认运营商编码
	 */
	public static final String SERVICECODE_DEFAULT = "000000000000000";
	/**
	 * 登录
	 */
	public static final String URL_LOGIN = "/interface/Login";
	/**
	 * 获得验证码
	 */
	public static final String URL_GETCHECK = "/interface/CheckPhone";
	/**
	 * 注册
	 */
	public static final String URL_REGIST = "/interface/Register";
	/**
	 * 绑定运营商
	 */
	public static final String URL_BIND = "/interface/binder";
	/**
	 * 修改密码
	 */
	public static final String URL_CHANGEPASS = "/interface/Modify";
	/**
	 * 重置密码
	 */
	public static final String URL_REPASS = "/interface/ResetPassword";
	/**
	 * 重置密码获得验证码
	 */
	public static final String URL_REPASSGETCHECK = "/interface/ResetPwd";
	/**
	 * 登录 手机号
	 */
	public static final String LOGIN_USERNAME = "phone";
	/**
	 * 登录 密码
	 */
	public static final String LOGIN_PASS = "password";
	/**
	 * 登录 运营商码
	 */
	public static final String LOGIN_BINDCODE = "bindcode";
	/**
	 * 获得验证码 手机号
	 */
	public static final String GETCHECK_TELPHONE = "phone";
	/**
	 * 获得验证码 绑定社区码
	 */
	public static final String GETCHECK_BINDCODE = "bindcode";
	/**
	 * 注册 手机号
	 */
	public static final String REGIST_TELPHONE = "phone";
	/**
	 * 注册 密码
	 */
	public static final String REGIST_PASS = "password";
	/**
	 * 注册 验证码
	 */
	public static final String REGIST_CHECKCODE = "checkcode";
	/**
	 * 注册 运营商码
	 */
	public static final String REGIST_BINDCODE = "bindcode";
	/**
	 * 绑定运营商 运营码
	 */
	public static final String BIND_CODE = "bindercode";
	/**
	 * 修改密码 用户名
	 */
	public static final String CHANGEPASS_USERNAME = "phone";
	/**
	 * 修改密码 原密码
	 */
	public static final String CHANGEPASS_OPASS = "sourcepwd";
	/**
	 * 修改密码 新密码
	 */
	public static final String CHANGEPASS_NPASS = "newpwd";
	/**
	 * 修改密码 运营码
	 */
	public static final String CHANGEPASS_BINDCODE = "bindcode";
	/**
	 * 重置密码获得验证码 手机号
	 */
	public static final String RESETGETCHECK_TEL = "phone";
	/**
	 * 重置密码获得验证码 绑定码
	 */
	public static final String RESETGETCHECK_BINDCODE = "bindcode";
	/**
	 * 重置密码 用户名
	 */
	public static final String REPASS_USERNAME = "phone";
	/**
	 * 重置密码 新密码
	 */
	public static final String REPASS_NEWPASS = "newpwd";
	/**
	 * 重置密码 验证码
	 */
	public static final String REPASS_CHECKCODE = "code";
	/**
	 * 重置密码 运营码
	 */
	public static final String REPASS_BINDCODE = "bindcode";
	// 网络请求数据返回值
	/**
	 * 网络错误
	 */
	public static final int WEBFLAG_ERROR = 2000;
	/**
	 * 登录
	 */
	public static final int WEBFLAG_LOGIN = 2001;
	/**
	 * 获得验证码
	 */
	public static final int WEBFLAG_GETCHECK = 2002;
	/**
	 * 注册
	 */
	public static final int WEBFLAG_REGIST = 2003;
	/**
	 * 绑定运营商
	 */
	public static final int WEBFLAG_BIND = 2004;
	/**
	 * 修改密码
	 */
	public static final int WEBFLAG_CHANGEPASS = 2005;
	/**
	 * 重置密码
	 */
	public static final int WEBFLAG_REPASS = 2006;
	/**
	 * 重置密码 获得验证码
	 */
	public static final int WEBFLAG_REPASSGETCHECK = 2007;

	/**
	 * 返回信息CODE值 请求成功
	 */
	public static final int RESULTCODE_SUC = 1;
	/**
	 * 返回信息CODE值 请求警告
	 */
	public static final int RESULTCODE_WARN = 2;
	/**
	 * 返回信息CODE值 请求失败
	 */
	public static final int RESULTCODE_FAIL = 3;

}
