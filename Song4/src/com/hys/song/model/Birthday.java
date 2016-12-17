package com.hys.song.model;



public class Birthday {
	private int birthdayId;
	private String contactName;
	private String email;
	private String address;
	private String birthday;
	private int age;
	private int userId;
	public Birthday(){	
	}
	public void setBirthdayId(int birthdayId){
		this.birthdayId=birthdayId;
	}
	public int getBirthdayId(){
		return birthdayId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Birthday [contactName=" + contactName + ", birthday="
				+ birthday + ", age=" + age + "]";
	}
	
}
