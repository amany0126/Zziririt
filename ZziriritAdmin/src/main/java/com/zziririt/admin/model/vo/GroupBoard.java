package com.zziririt.admin.model.vo;
import java.sql.Date;
public class GroupBoard {
	
	private int boardNo;//	BOARD_NO	NUMBER
	private String boardTitle;//	BOARD_TITLE	VARCHAR2(30 CHAR)
	private String boardContent;//	BOARD_CONTENT	VARCHAR2(150 CHAR)
	private Date writeTime;//	WRITE_TIME	DATE
	private int status;//	STATUS	NUMBER
	private String groupMemNo; //	GROUP_MEM_NO	NUMBER
	private int groupMemNoStatus; //	GROUP_MEM_NO	NUMBER
	private String group;//	GROUP_NO	NUMBER
	
	
	
	public GroupBoard() {}
	public GroupBoard(int boardNo, String boardTitle, String boardContent, Date writeTime, int status,
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
	public GroupBoard(int boardNo, String boardTitle, String boardContent, Date writeTime, int status,
			int groupMemNoStatus,String groupMemNo, String group) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.writeTime = writeTime;
		this.status = status;
		this.groupMemNo = groupMemNo;
		this.groupMemNoStatus = groupMemNoStatus;
		this.group = group;
	}
	
	
	public GroupBoard(int boardNo, String boardTitle, String boardContent, Date writeTime, int status,
			String groupMemNo, int groupMemNoStatus, String group) {
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.writeTime = writeTime;
		this.status = status;
		this.groupMemNo = groupMemNo;
		this.groupMemNoStatus = groupMemNoStatus;
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
	public Date getWriteTime() {
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
	public void setWriteTime(Date writeTime) {
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
	
	
	
	public int getGroupMemNoStatus() {
		return groupMemNoStatus;
	}
	public void setGroupMemNoStatus(int groupMemNoStatus) {
		this.groupMemNoStatus = groupMemNoStatus;
	}
	@Override
	public String toString() {
		return "GroupBoard [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", writeTime=" + writeTime + ", status=" + status + ", groupMemNo=" + groupMemNo
				+ ", groupMemNoStatus=" + groupMemNoStatus + ", group=" + group + "]";
	}
	
	
	
	
}