package com.zziririt.member.model.vo;

import java.sql.Date;

public class Member { // Member 클래스 영역 시작
	
	// 선언부
	private int userNo; 			 //	USER_NO	NUMBER
	private String userId; 			 //	USER_ID	VARCHAR2(20 CHAR)
	private String userPwd; 		 //	USER_PWD	VARCHAR2(20 CHAR)
	private String userNickname; 	 //	USER_NICKNAME	VARCHAR2(15 CHAR)
	private String userName; 		 //	USER_NAME	VARCHAR2(20 CHAR)
	private String userGender; 		 //	USER_GENDER	CHAR(1 BYTE)
	private String userProfile; 	 //	USER_PROFILE	VARCHAR2(40 CHAR)
	private String userPhone ; 		 //	USER_PHONE	VARCHAR2(11 BYTE)
	private String userMail; 		 //	USER_MAIL	VARCHAR2(40 CHAR)
	private String birthDate; 		 //	BIRTH_DATE	DATE
	private Date joinDate; 		 	 //	JOIN_DATE	DATE
	private Date modifyDate; 		 //	MODIFY_DATE	DATE
	private int status; 			 //	STATUS	NUMBER
	private String address; 		 //	ADDRESS	VARCHAR2(40 CHAR)
	private String introduceContent; //	INTRODUCE_CONTENT	VARCHAR2(500 CHAR)
	
	// 생성자부
	// 기본생성자
	public Member() { }
	
	// (모든) 매개변수 생성자
	public Member(int userNo, String userId, String userPwd, String userNickname, String userName, String userGender,
			String userProfile, String userPhone, String userMail, String birthDate, Date joinDate, Date modifyDate,
			int status, String address, String introduceContent) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userNickname = userNickname;
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
	
	// 회원가입용 생성자
	public Member(String userId, String userPwd, String userNickname, String userName, String userGender,
			String userProfile, String userPhone, String userMail, String birthDate, String address,
			String introduceContent) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userNickname = userNickname;
		this.userName = userName;
		this.userGender = userGender;
		this.userProfile = userProfile;
		this.userPhone = userPhone;
		this.userMail = userMail;
		this.birthDate = birthDate;
		this.address = address;
		this.introduceContent = introduceContent;
	}
	
	// 회원정보 수정용 생성자
	public Member(String userId, String userNickname, String userName, String userProfile, String userPhone,
			String userMail, String address, String introduceContent) {
		super();
		this.userId = userId;
		this.userNickname = userNickname;
		this.userName = userName;
		this.userProfile = userProfile;
		this.userPhone = userPhone;
		this.userMail = userMail;
		this.address = address;
		this.introduceContent = introduceContent;
	}
	
	// 메서드부
	// getter / setter 메서드들
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntroduceContent() {
		return introduceContent;
	}

	public void setIntroduceContent(String introduceContent) {
		this.introduceContent = introduceContent;
	}
	
	// toString 메서드
	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userNickname="
				+ userNickname + ", userName=" + userName + ", userGender=" + userGender + ", userProfile="
				+ userProfile + ", userPhone=" + userPhone + ", userMail=" + userMail + ", birthDate=" + birthDate
				+ ", joinDate=" + joinDate + ", modifyDate=" + modifyDate + ", status=" + status + ", address="
				+ address + ", introduceContent=" + introduceContent + "]";
	}
} // Member 클래스 영역 끝