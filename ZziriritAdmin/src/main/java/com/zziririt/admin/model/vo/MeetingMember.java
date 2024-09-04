package com.zziririt.admin.model.vo;

public class MeetingMember {
	
	private String meetingNo;	//	MEETING_NO	NUMBER
	private String meetingTitle;	//	MEETING_NO	NUMBER
	private String groupMemNo;	//	GROUP_MEM_NO	NUMBER
	private String groupMemId;
	private int status;	//	STATUS	NUMBER
	private int groupMemNoStatus;
	
	public MeetingMember() {};
	
	public MeetingMember(String meetingNo, String groupMemNo, int status, int groupMemNoStatus) {
		this.meetingNo = meetingNo;
		this.groupMemNo = groupMemNo;
		this.status = status;
		this.groupMemNoStatus = groupMemNoStatus;
	}

	
	
	public MeetingMember(String meetingNo, String meetingTitle, String groupMemNo, int status, int groupMemNoStatus) {
		this.meetingNo = meetingNo;
		this.meetingTitle = meetingTitle;
		this.groupMemNo = groupMemNo;
		this.status = status;
		this.groupMemNoStatus = groupMemNoStatus;
	}
	
	
	
	public MeetingMember(String meetingNo, String meetingTitle, String groupMemNo, String groupMemId, int status,
			int groupMemNoStatus) {
		this.meetingNo = meetingNo;
		this.meetingTitle = meetingTitle;
		this.groupMemNo = groupMemNo;
		this.groupMemId = groupMemId;
		this.status = status;
		this.groupMemNoStatus = groupMemNoStatus;
	}

	public String getGroupMemId() {
		return groupMemId;
	}

	public void setGroupMemId(String groupMemId) {
		this.groupMemId = groupMemId;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public String getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}

	public String getGroupMemNo() {
		return groupMemNo;
	}

	public void setGroupMemNo(String groupMemNo) {
		this.groupMemNo = groupMemNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getGroupMemNoStatus() {
		return groupMemNoStatus;
	}

	public void setGroupMemNoStatus(int groupMemNoStatus) {
		this.groupMemNoStatus = groupMemNoStatus;
	}

	@Override
	public String toString() {
		return "MeetingMember [meetingNo=" + meetingNo + ", groupMemNo=" + groupMemNo + ", status=" + status
				+ ", groupMemNoStatus=" + groupMemNoStatus + "]";
	}
	
	
}
