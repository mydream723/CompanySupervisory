package com.esint.communitytools.bean;

/**
 * 社区账户信息
 * 
 * @author Administrator
 *
 */
public class CAccount {
	/**
	 * 主键
	 */
	private int id;
	/**
	 * 用户id
	 */
	private int userid;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 性别
	 */
	private int sex;
	/**
	 * 电话
	 */
	private String telphone;
	/**
	 * 身份证号
	 */
	private String cardId;
	/**
	 * 账户使用状态 0:账户未使用 1：账户正在使用
	 */
	private int activite;

	public CAccount() {

	}

	/**
	 * @param userId
	 *            用户id
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param email
	 *            邮箱
	 * @param sex
	 *            性别
	 * @param telphone
	 *            电话
	 * @param cardId
	 *            身份证
	 * @param activite
	 *            使用状态 0未使用 1使用中
	 */
	public CAccount(int userid, String username, String password, String email, int sex, String telphone, String cardId,
			int activite) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.sex = sex;
		this.telphone = telphone;
		this.cardId = cardId;
		this.activite = activite;
	}

	public int getId() {
		return id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getActivite() {
		return activite;
	}

	public void setActivite(int activite) {
		this.activite = activite;
	}

	@Override
	public String toString() {
		return "CAccount [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", sex=" + sex + ", telphone=" + telphone + ", cardId=" + cardId + ", activite=" + activite
				+ ", serviceCode=" + "]";
	}

}
