package com.zziririt.group.model.vo;

public class GroupComment {
	
	private int commentNo;//	COMMENT_NO	NUMBER
	private String content;//	CONTENT	VARCHAR2(200 CHAR)
	private String writeTime;		 //	WRITE_TIME	DATE
	private int status;	//	STATUS	NUMBER
	private int	groupMemNo;  //	GROUP_MEM_NO	NUMBER
	private int boardNo; //	BOARD_NO	NUMBER
	private String userNickName;
	
	public GroupComment() {
		
	}


	public GroupComment(int commentNo, String content, String writeTime, int status, int groupMemNo, int boardNo) {
		super();
		this.commentNo = commentNo;
		this.content = content;
		this.writeTime = writeTime;
		this.status = status;
		this.groupMemNo = groupMemNo;
		this.boardNo = boardNo;
	}
	
	
	
	public GroupComment(int commentNo, String content, String writeTime, int status, int groupMemNo, int boardNo,
			String userNickName) {
		super();
		this.commentNo = commentNo;
		this.content = content;
		this.writeTime = writeTime;
		this.status = status;
		this.groupMemNo = groupMemNo;
		this.boardNo = boardNo;
		this.userNickName = userNickName;
	}
	
	

	public String getUserNickName() {
		return userNickName;
	}


	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}


	public int getCommentNo() {
		return commentNo;
	}


	public String getContent() {
		return content;
	}


	public String getWriteTime() {
		return writeTime;
	}


	public int getStatus() {
		return status;
	}


	public int getGroupMemNo() {
		return groupMemNo;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public void setGroupMemNo(int groupMemNo) {
		this.groupMemNo = groupMemNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	@Override
	public String toString() {
		return "GroupComment [commentNo=" + commentNo + ", content=" + content + ", writeTime=" + writeTime
				+ ", status=" + status + ", groupMemNo=" + groupMemNo + ", boardNo=" + boardNo + "]";
	}


	
}
