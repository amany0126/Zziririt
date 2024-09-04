package com.zziririt.letter.model.vo;

import java.sql.Date;

public class Letter {
	
	private int letterNo;
	private String letterTitle;
	private String letterContent;
	private String sendTime;
	private String receiveTime;
	private int status;
	private String userSender;
	private String userReceiver;
	private int count;
	private String saveReceiver;

	public Letter () {}

	public Letter(int letterNo, String letterTitle, String letterContent, String sendTime, String receiveTime,
			int status, String userSender, String userReceiver, int count, String saveReceiver) {
		super();
		this.letterNo = letterNo;
		this.letterTitle = letterTitle;
		this.letterContent = letterContent;
		this.sendTime = sendTime;
		this.receiveTime = receiveTime;
		this.status = status;
		this.userSender = userSender;
		this.userReceiver = userReceiver;
		this.count = count;
		this.saveReceiver = saveReceiver;
	}

	public int getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(int letterNo) {
		this.letterNo = letterNo;
	}

	public String getLetterTitle() {
		return letterTitle;
	}

	public void setLetterTitle(String letterTitle) {
		this.letterTitle = letterTitle;
	}

	public String getLetterContent() {
		return letterContent;
	}

	public void setLetterContent(String letterContent) {
		this.letterContent = letterContent;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserSender() {
		return userSender;
	}

	public void setUserSender(String userSender) {
		this.userSender = userSender;
	}

	public String getUserReceiver() {
		return userReceiver;
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

	public String getSaveReceiver() {
		return saveReceiver;
	}

	public void setSaveReceiver(String saveReceiver) {
		this.saveReceiver = saveReceiver;
	}

	@Override
	public String toString() {
		return "Letter [letterNo=" + letterNo + ", letterTitle=" + letterTitle + ", letterContent=" + letterContent
				+ ", sendTime=" + sendTime + ", receiveTime=" + receiveTime + ", status=" + status + ", userSender="
				+ userSender + ", userReceiver=" + userReceiver + ", count=" + count + ", saveReceiver=" + saveReceiver
				+ "]";
	}

	

	


}
