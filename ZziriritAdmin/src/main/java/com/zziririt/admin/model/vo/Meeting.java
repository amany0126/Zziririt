package com.zziririt.admin.model.vo;

import java.sql.Date;

public class Meeting {

	
	private int meetingNo; //	MEETING_NO	NUMBER
	private String meetingName;//	MEETING_NAME	VARCHAR2(30 CHAR)
	private String meetingSpot;//	MEETING_SPOT	VARCHAR2(40 CHAR)
	private Date meetingTime; //	MEETING_TIME	DATE
	private String meetingContent;//	MEETING_CONTENT	VARCHAR2(500 BYTE)
	private int meetingLimit;//	MEETING_LIMIT	NUMBER
	private String groupNo;//	GROUP_NO	NUMBER
	private int status;//	STATUS	NUMBER
	private String createUser;//	CREATE_USER	NUMBER
	
	
	public Meeting() {};
	
	
	
	public Meeting(int meetingNo, String meetingName, String meetingSpot, String groupNo, int status,
			String createUser) {
		this.meetingNo = meetingNo;
		this.meetingName = meetingName;
		this.meetingSpot = meetingSpot;
		this.groupNo = groupNo;
		this.status = status;
		this.createUser = createUser;
	}



	public Meeting(int meetingNo, String meetingName, String meetingSpot, Date meetingTime, String meetingContent,
			int meetingLimit, String groupNo, int status, String createUser) {
		this.meetingNo = meetingNo;
		this.meetingName = meetingName;
		this.meetingSpot = meetingSpot;
		this.meetingTime = meetingTime;
		this.meetingContent = meetingContent;
		this.meetingLimit = meetingLimit;
		this.groupNo = groupNo;
		this.status = status;
		this.createUser = createUser;
	}

	public int getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getMeetingSpot() {
		return meetingSpot;
	}

	public void setMeetingSpot(String meetingSpot) {
		this.meetingSpot = meetingSpot;
	}

	public Date getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Date meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getMeetingContent() {
		return meetingContent;
	}

	public void setMeetingContent(String meetingContent) {
		this.meetingContent = meetingContent;
	}

	public int getMeetingLimit() {
		return meetingLimit;
	}

	public void setMeetingLimit(int meetingLimit) {
		this.meetingLimit = meetingLimit;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Override
	public String toString() {
		return "Meeting [meetingNo=" + meetingNo + ", meetingName=" + meetingName + ", meetingSpot=" + meetingSpot
				+ ", meetingTime=" + meetingTime + ", meetingContent=" + meetingContent + ", meetingLimit="
				+ meetingLimit + ", groupNo=" + groupNo + ", status=" + status + ", createUser=" + createUser + "]";
	}
	
	
	
	
}
