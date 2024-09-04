package com.zziririt.group.model.vo;

public class Meeting {

	private int meetingNo;
	private String meetingName;
	private String meetingSpot;
	private String meetingYear;
	private String meetingMonth;
	private String meetingDay;
	private String meetingHour;
	private String meetingMinute;
	private String meetingContent;
	private int curMem;
	private int meetingLimit;
	private int groupNo;
	private int status;
	private int createUser;
	private String userName;
	
	public Meeting() {
		super();
	}
	

	public Meeting(int meetingNo, String meetingName, String meetingSpot, String meetingYear, String meetingMonth,
			String meetingDay, String meetingHour, String meetingMinute, String meetingContent, int curMem,
			int meetingLimit, int groupNo) {
		super();
		this.meetingNo = meetingNo;
		this.meetingName = meetingName;
		this.meetingSpot = meetingSpot;
		this.meetingYear = meetingYear;
		this.meetingMonth = meetingMonth;
		this.meetingDay = meetingDay;
		this.meetingHour = meetingHour;
		this.meetingMinute = meetingMinute;
		this.meetingContent = meetingContent;
		this.curMem = curMem;
		this.meetingLimit = meetingLimit;
		this.groupNo = groupNo;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getCreateUser() {
		return createUser;
	}


	public void setCreateUser(int createUser) {
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


	public String getMeetingYear() {
		return meetingYear;
	}


	public void setMeetingYear(String meetingYear) {
		this.meetingYear = meetingYear;
	}


	public String getMeetingMonth() {
		return meetingMonth;
	}


	public void setMeetingMonth(String meetingMonth) {
		this.meetingMonth = meetingMonth;
	}


	public String getMeetingDay() {
		return meetingDay;
	}


	public void setMeetingDay(String meetingDay) {
		this.meetingDay = meetingDay;
	}


	public String getMeetingHour() {
		return meetingHour;
	}


	public void setMeetingHour(String meetingHour) {
		this.meetingHour = meetingHour;
	}


	public String getMeetingMinute() {
		return meetingMinute;
	}


	public void setMeetingMinute(String meetingMinute) {
		this.meetingMinute = meetingMinute;
	}


	public String getMeetingContent() {
		return meetingContent;
	}


	public void setMeetingContent(String meetingContent) {
		this.meetingContent = meetingContent;
	}


	public int getCurMem() {
		return curMem;
	}


	public void setCurMem(int curMem) {
		this.curMem = curMem;
	}


	public int getMeetingLimit() {
		return meetingLimit;
	}


	public void setMeetingLimit(int meetingLimit) {
		this.meetingLimit = meetingLimit;
	}


	public int getGroupNo() {
		return groupNo;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}


	@Override
	public String toString() {
		return "Meeting [meetingNo=" + meetingNo + ", meetingName=" + meetingName + ", meetingSpot=" + meetingSpot
				+ ", meetingYear=" + meetingYear + ", meetingMonth=" + meetingMonth + ", meetingDay=" + meetingDay
				+ ", meetingHour=" + meetingHour + ", meetingMinute=" + meetingMinute + ", meetingContent="
				+ meetingContent + ", curMem=" + curMem + ", meetingLimit=" + meetingLimit + ", groupNo=" + groupNo
				+ ", status=" + status + ", createUser=" + createUser + "]";
	}
	
	
}
