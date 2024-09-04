package com.zziririt.admin.model.vo;

import java.sql.Date;

public class Group {
	private	int groupNo; //	GROUP_NO	NUMBER
	private	String groupName; //	GROUP_NAME	VARCHAR2(100 CHAR)
	private	String groupDescript; //	GROUP_DESCRIPT	VARCHAR2(500 CHAR)
	private	int groupLimit;//	GROUP_LIMIT	NUMBER
	private	Date groupCreatDay; //	GROUP_CREATEDAY	DATE
	private	String groupArea; //	GROUP_AREA	VARCHAR2(40 CHAR)
	private	String groupPeofile; //	GORUP_PROFILE	VARCHAR2(40 CHAR)
	private	int status;//	STATUS	NUMBER
	private String categoryName; //	CATEGORY_NAME	VARCHAR2(20 CHAR)

	public Group() {}
	
	
	
	public Group(int groupNo, String groupName, int groupLimit, Date groupCreatDay, int status, String categoryName) {
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.groupLimit = groupLimit;
		this.groupCreatDay = groupCreatDay;
		this.status = status;
		this.categoryName = categoryName;
	}



	public Group(int groupNo, String groupName, String groupDescript, int groupLimit, Date groupCreatDay,
			String groupArea, String groupPeofile, int status, String categoryName) {
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.groupDescript = groupDescript;
		this.groupLimit = groupLimit;
		this.groupCreatDay = groupCreatDay;
		this.groupArea = groupArea;
		this.groupPeofile = groupPeofile;
		this.status = status;
		this.categoryName = categoryName;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescript() {
		return groupDescript;
	}

	public void setGroupDescript(String groupDescript) {
		this.groupDescript = groupDescript;
	}

	public int getGroupLimit() {
		return groupLimit;
	}

	public void setGroupLimit(int groupLimit) {
		this.groupLimit = groupLimit;
	}

	public Date getGroupCreatDay() {
		return groupCreatDay;
	}

	public void setGroupCreatDay(Date groupCreatDay) {
		this.groupCreatDay = groupCreatDay;
	}

	public String getGroupArea() {
		return groupArea;
	}

	public void setGroupArea(String groupArea) {
		this.groupArea = groupArea;
	}

	public String getGroupPeofile() {
		return groupPeofile;
	}

	public void setGroupPeofile(String groupPeofile) {
		this.groupPeofile = groupPeofile;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Group [groupNo=" + groupNo + ", groupName=" + groupName + ", groupDescript=" + groupDescript
				+ ", groupLimit=" + groupLimit + ", groupCreatDay=" + groupCreatDay + ", groupArea=" + groupArea
				+ ", groupPeofile=" + groupPeofile + ", status=" + status + ", categoryName=" + categoryName + "]";
	}
	
	
	
	
}
