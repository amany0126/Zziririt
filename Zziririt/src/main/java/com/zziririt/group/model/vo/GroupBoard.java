package com.zziririt.group.model.vo;

import java.sql.Date;

public class GroupBoard {

	private int boardNo;//	BOARD_NO	NUMBER
	private String boardTitle;//	BOARD_TITLE	VARCHAR2(30 CHAR)
	private String boardContent;//	BOARD_CONTENT	VARCHAR2(150 CHAR)
	private String writeTime;//	WRITE_TIME	DATE
	private int status;//	STATUS	NUMBER
	private String groupMemNo; //	GROUP_MEM_NO	NUMBER
	private String group;//	GROUP_NO	NUMBER
	private int groupNo;
	
	
	public GroupBoard() {}

	

	public GroupBoard(int boardNo, String boardTitle, String boardContent, String writeTime, String groupMemNo) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.writeTime = writeTime;
		this.groupMemNo = groupMemNo;
	}



	public GroupBoard(int boardNo, String boardTitle, String boardContent, String writeTime, int status,
			String groupMemNo, String group) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.writeTime = writeTime;
		this.status = status;
		this.groupMemNo = groupMemNo;
		this.group = group;
	}

	
	public int getBoardNo() {
		return boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public String getWriteTime() {
		return writeTime;
	}

	public int getStatus() {
		return status;
	}

	public String getGroupMemNo() {
		return groupMemNo;
	}

	public String getGroup() {
		return group;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setGroupMemNo(String groupMemNo) {
		this.groupMemNo = groupMemNo;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	


	public int getGroupNo() {
		return groupNo;
	}



	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	@Override
	public String toString() {
		return "GroupBoard [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", writeTime=" + writeTime + ", status=" + status + ", groupMemNo=" + groupMemNo + ", group=" + group
				+ "]";
	}


	
	
}
