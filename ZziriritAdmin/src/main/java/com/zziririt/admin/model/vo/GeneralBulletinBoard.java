package com.zziririt.admin.model.vo;

import java.sql.Date;

public class GeneralBulletinBoard {

private int boardNo;//	BOARD_NO	NUMBER
private String boardTitle;//	BOARD_TITLE	VARCHAR2(30 CHAR)
private String boardContent;//	BOARD_CONTENT	VARCHAR2(200 CHAR)
private Date createTime;//	CREATE_TIME	DATE
private Date meetingTime;//	MEETING_TIME	DATE
private String meetingSpot;//	MEETING_SPOT	VARCHAR2(30 CHAR)
private String fileInfo;//	FILE_INFO	VARCHAR2(50 CHAR)
private int peopleLimit;//	PEOPLE_LIMIT	NUMBER
private int status;//	STATUS	NUMBER 게시글 상태값

private String boardWriter;//	BOARD_WRITER	NUMBER 작성자
private int groupMemNoStatus; //	GROUP_MEM_NO	NUMBER 작성자 상태값

private String categoryName;//	CATEGORY_NAME	VARCHAR2(20 CHAR) 카테고리
private int meetingStatus;//	MEETING_STATUS	NUMBER
private int count;//	COUNT	NUMBER


public GeneralBulletinBoard() {}

public GeneralBulletinBoard(int boardNo, String boardTitle, String boardContent, Date createTime, Date meetingTime,
		String meetingSpot, String fileInfo, int peopleLimit, int status, String boardWriter, String categoryName,
		int meetingStatus, int count) {
	this.boardNo = boardNo;
	this.boardTitle = boardTitle;
	this.boardContent = boardContent;
	this.createTime = createTime;
	this.meetingTime = meetingTime;
	this.meetingSpot = meetingSpot;
	this.fileInfo = fileInfo;
	this.peopleLimit = peopleLimit;
	this.status = status;
	this.boardWriter = boardWriter;
	this.categoryName = categoryName;
	this.meetingStatus = meetingStatus;
	this.count = count;
}




public GeneralBulletinBoard(int boardNo, String boardTitle, String boardContent, int status, String boardWriter,
		int groupMemNoStatus, String categoryName) {
	this.boardNo = boardNo;
	this.boardTitle = boardTitle;
	this.boardContent = boardContent;
	this.status = status;
	this.boardWriter = boardWriter;
	this.groupMemNoStatus = groupMemNoStatus;
	this.categoryName = categoryName;
}

public GeneralBulletinBoard(int boardNo, String boardTitle, String boardContent, Date createTime, Date meetingTime,
		String meetingSpot, String fileInfo, int peopleLimit, int status, String boardWriter, int groupMemNoStatus,
		String categoryName, int meetingStatus, int count) {
	this.boardNo = boardNo;
	this.boardTitle = boardTitle;
	this.boardContent = boardContent;
	this.createTime = createTime;
	this.meetingTime = meetingTime;
	this.meetingSpot = meetingSpot;
	this.fileInfo = fileInfo;
	this.peopleLimit = peopleLimit;
	this.status = status;
	this.boardWriter = boardWriter;
	this.groupMemNoStatus = groupMemNoStatus;
	this.categoryName = categoryName;
	this.meetingStatus = meetingStatus;
	this.count = count;
}



public int getGroupMemNoStatus() {
	return groupMemNoStatus;
}

public void setGroupMemNoStatus(int groupMemNoStatus) {
	this.groupMemNoStatus = groupMemNoStatus;
}

public int getBoardNo() {
	return boardNo;
}

public void setBoardNo(int boardNo) {
	this.boardNo = boardNo;
}

public String getBoardTitle() {
	return boardTitle;
}

public void setBoardTitle(String boardTitle) {
	this.boardTitle = boardTitle;
}

public String getBoardContent() {
	return boardContent;
}

public void setBoardContent(String boardContent) {
	this.boardContent = boardContent;
}

public Date getCreateTime() {
	return createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}

public Date getMeetingTime() {
	return meetingTime;
}

public void setMeetingTime(Date meetingTime) {
	this.meetingTime = meetingTime;
}

public String getMeetingSpot() {
	return meetingSpot;
}

public void setMeetingSpot(String meetingSpot) {
	this.meetingSpot = meetingSpot;
}

public String getFileInfo() {
	return fileInfo;
}

public void setFileInfo(String fileInfo) {
	this.fileInfo = fileInfo;
}

public int getPeopleLimit() {
	return peopleLimit;
}

public void setPeopleLimit(int peopleLimit) {
	this.peopleLimit = peopleLimit;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public String getBoardWriter() {
	return boardWriter;
}

public void setBoardWriter(String boardWriter) {
	this.boardWriter = boardWriter;
}

public String getCategoryName() {
	return categoryName;
}

public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}

public int getMeetingStatus() {
	return meetingStatus;
}

public void setMeetingStatus(int meetingStatus) {
	this.meetingStatus = meetingStatus;
}

public int getCount() {
	return count;
}

public void setCount(int count) {
	this.count = count;
}

@Override
public String toString() {
	return "GeneralBulletinBoard [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
			+ ", createTime=" + createTime + ", meetingTime=" + meetingTime + ", meetingSpot=" + meetingSpot
			+ ", fileInfo=" + fileInfo + ", peopleLimit=" + peopleLimit + ", status=" + status + ", boardWriter="
			+ boardWriter + ", groupMemNoStatus=" + groupMemNoStatus + ", categoryName=" + categoryName
			+ ", meetingStatus=" + meetingStatus + ", count=" + count + "]";
}



	




}













