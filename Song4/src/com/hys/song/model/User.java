package com.hys.song.model;

public class User {
	private int userId;
	private String userName;
	private String password;
	private String realName;
	private String email;
	private int number;
	private String aliPay;
	private int shoppingTrolleyId;

	public User() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAliPay() {
		return aliPay;
	}

	public void setAliPay(String aliPay) {
		this.aliPay = aliPay;
	}
	public int getShoppingTrolleyId() {
		return shoppingTrolleyId;
	}

	public void setShoppingTrolleyId(int shoppingTrolleyId) {
		this.shoppingTrolleyId = shoppingTrolleyId;
	}

}
