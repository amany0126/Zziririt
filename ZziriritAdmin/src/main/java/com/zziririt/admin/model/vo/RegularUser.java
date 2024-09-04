package com.zziririt.admin.model.vo;

import java.sql.Date;

public class RegularUser {
	private int userNo;//	USER_NO	NUMBER
	private String userId;//	USER_ID	VARCHAR2(20 CHAR)
	private String userPwd;//	USER_PWD	VARCHAR2(20 CHAR)
	private String userNickName;//	USER_NICKNAME	VARCHAR2(15 CHAR)
	private String userName;//	USER_NAME	VARCHAR2(20 CHAR)
	private String userGender;//	USER_GENDER	CHAR(1 BYTE)
	private String userProfile;//	USER_PROFILE	VARCHAR2(40 CHAR)
	private String userPhone;//	USER_PHONE	VARCHAR2(11 BYTE)
	private String userMail;//	USER_MAIL	VARCHAR2(40 CHAR)
	private Date birthDate;//	BIRTH_DATE	DATE
	private Date joinDate;//	JOIN_DATE	DATE
	private Date modifyDate;//	MODIFY_DATE	DATE
	private int status;//	STATUS	NUMBER
	private String address;//	ADDRESS	VARCHAR2(40 CHAR)
	private String introduceContent;//	INTRODUCE_CONTENT	VARCHAR2(500 CHAR)

	public RegularUser () {}
	
	public RegularUser(int userNo, int status) {
		super();
		this.userNo = userNo;
		this.status = status;
	}

	public RegularUser(int userNo, String userId, String userPwd, String userNickName, String userName,
			String userGender, String userProfile, String userPhone, String userMail, Date birthDate, Date joinDate,
			Date modifyDate, int status, String address, String introduceContent) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userNickName = userNickName;
		this.userName = userName;
		this.userGender = userGender;
		this.userProfile = userProfile;
		this.userPhone = userPhone;
		this.userMail = userMail;
		this.birthDate = birthDate;
		this.joinDate = joinDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.address = address;
		this.introduceContent = introduceContent;
	}

	
	public RegularUser(int userNo, String userId, String userNickName, String userName, Date joinDate, int status) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userNickName = userNickName;
		this.userName = userName;
		this.joinDate = joinDate;
		this.status = status;
	}

	public int getUserNo() {
		return userNo;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public String getUserMail() {
		return userMail;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public int getStatus() {
		return status;
	}

	public String getAddress() {
		return address;
	}

	public String getIntroduceContent() {
		return introduceContent;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setIntroduceContent(String introduceContent) {
		this.introduceContent = introduceContent;
	}

	@Override
	public String toString() {
		return "RegularUser [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userNickName="
				+ userNickName + ", userName=" + userName + ", userGender=" + userGender + ", userProfile="
				+ userProfile + ", userPhone=" + userPhone + ", userMail=" + userMail + ", BirthDate=" + birthDate
				+ ", JoinDate=" + joinDate + ", ModifyDate=" + modifyDate + ", status=" + status + ", address="
				+ address + ", introduceContent=" + introduceContent + "]";
	}
	
	



}
