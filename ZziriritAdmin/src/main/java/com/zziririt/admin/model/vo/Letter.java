package com.zziririt.admin.model.vo;

import java.sql.Date;

public class Letter {
	
	private int letterNo;
	private String letterTitle;
	private String letterContent;
	private Date sendTime;
	private Date receiveTime;
	private int status;
	private String userSender;
	private String userReceiver;
	private int count;

	public Letter () {}

	public Letter(int letterNo, String letterTitle, String letterContent, Date sendTime, Date receiveTime, int status,
			String userSender, String userReceiver, int count) {
		super();
		this.letterNo = letterNo;
		this.letterTitle = letterTitle;
		this.letterContent = letterContent;
		this.sendTime = sendTime;
		this.receiveTime = receiveTime;
		this.status = status;
		this.userSender = userSender;
		this.userReceiver = userReceiver;
	}

	public int getLetterNo() {
		return letterNo;
	}

	public String getLetterTitle() {
		return letterTitle;
	}

	public String getLetterContent() {
		return letterContent;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public int getStatus() {
		return status;
	}

	public String getUserSender() {
		return userSender;
	}

	public String getUserReceiver() {
		return userReceiver;
	}

	public void setLetterNo(int letterNo) {
		this.letterNo = letterNo;
	}

	public void setLetterTitle(String letterTitle) {
		this.letterTitle = letterTitle;
	}

	public void setLetterContent(String letterContent) {
		this.letterContent = letterContent;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUserSender(String userSender) {
		this.userSender = userSender;
	}

	public void setUserReceiver(String userReceiver) {
		this.userReceiver = userReceiver;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Letter [letterNo=" + letterNo + ", letterTitle=" + letterTitle + ", letterContent=" + letterContent
				+ ", sendTime=" + sendTime + ", receiveTime=" + receiveTime + ", status=" + status + ", userSender="
				+ userSender + ", userReceiver=" + userReceiver + ", count=" + count + "]";
	}


}