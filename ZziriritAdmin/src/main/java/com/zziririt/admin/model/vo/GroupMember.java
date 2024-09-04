package com.zziririt.admin.model.vo;

import java.sql.Date;

public class GroupMember {
	private int groupMenNo;//	GROUP_MEM_NO	NUMBER
	private int groupMenType;//	GROUP_MEM_TYPE	NUMBER
	private String groupMenMent;//	GROUP_MEM_MENT	VARCHAR2(150 CHAR)
	private Date groupJoinDate;//	GROUP_JOIN_DATE	DATE
	private int status;//	STATUS	NUMBER
	private String userNo;//	USER_NO	NUMBER
	private String groupNo;//	GROUP_NO	NUMBER
	private String categoryName; //	CATEGORY_NAME	VARCHAR2(20 CHAR)
	
	public GroupMember() {}

	
	
	public GroupMember(int groupMenNo, int groupMenType, Date groupJoinDate, int status, String userNo, String groupNo,
			String categoryName) {
		this.groupMenNo = groupMenNo;
		this.groupMenType = groupMenType;
		this.groupJoinDate = groupJoinDate;
		this.status = status;
		this.userNo = userNo;
		this.groupNo = groupNo;
		this.categoryName = categoryName;
	}



	public GroupMember(int groupMenNO, int groupMenType, String groupMenMent, Date groupJoinDate, int status,
			String userNo, String groupNo, String categoryName) {
		this.groupMenNo = groupMenNo;
		this.groupMenType = groupMenType;
		this.groupMenMent = groupMenMent;
		this.groupJoinDate = groupJoinDate;
		this.status = status;
		this.userNo = userNo;
		this.groupNo = groupNo;
		this.categoryName = categoryName;
	}

	public int getGroupMenNo() {
		return groupMenNo;
	}

	public void setGroupMenNo(int groupMenNo) {
		this.groupMenNo = groupMenNo;
	}

	public int getGroupMenType() {
		return groupMenType;
	}

	public void setGroupMenType(int groupMenType) {
		this.groupMenType = groupMenType;
	}

	public String getGroupMenMent() {
		return groupMenMent;
	}

	public void setGroupMenMent(String groupMenMent) {
		this.groupMenMent = groupMenMent;
	}

	public Date getGroupJoinDate() {
		return groupJoinDate;
	}

	public void setGroupJoinDate(Date groupJoinDate) {
		this.groupJoinDate = groupJoinDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "GroupMember [groupMenNo=" + groupMenNo + ", groupMenType=" + groupMenType + ", groupMenMent="
				+ groupMenMent + ", groupJoinDate=" + groupJoinDate + ", status=" + status + ", userNo=" + userNo
				+ ", groupNo=" + groupNo + ", categoryName=" + categoryName + "]";
	}
	
	
	
}
