package com.zziririt.admin.model.vo;

import java.sql.Date;

public class GeneralBulletinComment {

	private int replyNo;//	REPLY_NO	NUMBER
	private String replyComment;//	REPLY_CONTENT	VARCHAR2(500 CHAR)
	private int refBno;//	REF_BNO	NUMBER
	private String replyWriter;//	REPLY_WRITER	NUMBER
	private Date createTime;//	CREATE_TIME	DATE
	private int status; //	STATUS	NUMBER
	
	private String boardCategoty;
	private String title;
	private int groupMemNoStatus;
	
	public GeneralBulletinComment() {};
	
	public GeneralBulletinComment(int replyNo, String replyComment, int refBno, String replyWriter, Date createTime,
			int status, int groupMemNoStatus) {
		this.replyNo = replyNo;
		this.replyComment = replyComment;
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.createTime = createTime;
		this.status = status;
		this.groupMemNoStatus = groupMemNoStatus;
	}
	
	
	
	
	public GeneralBulletinComment(int replyNo, String replyComment, String replyWriter, int status, String boardCategoty,
			String title, int groupMemNoStatus) {
		this.replyNo = replyNo;
		this.replyComment = replyComment;
		this.replyWriter = replyWriter;
		this.status = status;
		this.boardCategoty = boardCategoty;
		this.title = title;
		this.groupMemNoStatus = groupMemNoStatus;
	}

	public GeneralBulletinComment(int replyNo, String replyComment, int refBno, String replyWriter, Date createTime,
			int status, String boardCategoty, String title, int groupMemNoStatus) {
		this.replyNo = replyNo;
		this.replyComment = replyComment;
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.createTime = createTime;
		this.status = status;
		this.boardCategoty = boardCategoty;
		this.title = title;
		this.groupMemNoStatus = groupMemNoStatus;
	}

	
	
	public String getBoardCategoty() {
		return boardCategoty;
	}

	public void setBoardCategoty(String boardCategoty) {
		this.boardCategoty = boardCategoty;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReplyNo() {
		return replyNo;
	}
	
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(String replyComment) {
		this.replyComment = replyComment;
	}

	public int getRefBno() {
		return refBno;
	}

	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
		return "GeneralBulletinComment [replyNo=" + replyNo + ", replyComment=" + replyComment + ", refBno=" + refBno
				+ ", replyWriter=" + replyWriter + ", createTime=" + createTime + ", status=" + status
				+ ", groupMemNoStatus=" + groupMemNoStatus + "]";
	}
	
	
	
}
