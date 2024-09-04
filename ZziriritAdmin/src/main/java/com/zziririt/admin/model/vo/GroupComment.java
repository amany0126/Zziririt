package com.zziririt.admin.model.vo;

import java.sql.Date;

public class GroupComment {
	
	private int  commentNo;//	COMMENT_NO	NUMBER
	private String comment;//	CONTENT	VARCHAR2(200 CHAR)
	private Date writeTime;//	WRITE_TIME	DATE
	private int status;//	STATUS	NUMBER
	private String groupMemNo; //	GROUP_MEM_NO	NUMBER
	private int groupMemNoStatus; //	GROUP_MEM_NO	NUMBER
	private String boardNo;//	BOARD_NO	NUMBER
	private String boardTitle;
	private String boardContent;
	
	public GroupComment() {}

	
	
	public GroupComment(int commentNo, String comment, int status, String groupMemNo, int groupMemNoStatus,
			String boardTitle, String boardContent, String boardNo) {
		this.commentNo = commentNo;
		this.comment = comment;
		this.status = status;
		this.groupMemNo = groupMemNo;
		this.groupMemNoStatus = groupMemNoStatus;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardNo =boardNo;
	}



	public GroupComment(int commentNo, String comment, Date writeTime, int status, String groupMemNo,
			int groupMemNoStatus, String boardNo) {
		this.commentNo = commentNo;
		this.comment = comment;
		this.writeTime = writeTime;
		this.status = status;
		this.groupMemNo = groupMemNo;
		this.groupMemNoStatus = groupMemNoStatus;
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



	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getGroupMemNo() {
		return groupMemNo;
	}

	public void setGroupMemNo(String groupMemNo) {
		this.groupMemNo = groupMemNo;
	}

	public int getGroupMemNoStatus() {
		return groupMemNoStatus;
	}

	public void setGroupMemNoStatus(int groupMemNoStatus) {
		this.groupMemNoStatus = groupMemNoStatus;
	}

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "GroupComment [commentNo=" + commentNo + ", comment=" + comment + ", writeTime=" + writeTime
				+ ", status=" + status + ", groupMemNo=" + groupMemNo + ", groupMemNoStatus=" + groupMemNoStatus
				+ ", boardNo=" + boardNo + "]";
	}
	
	
}
