package com.zziririt.group.model.vo;

import java.sql.Date;

public class Group {

	private int groupNo;
	private String groupName;
	private String groupDescript;
	private int groupLimit;
	private Date groupCreateday;
	private String groupArea;
	private String groupProfile;
	private int status;
	private String categoryName;
	private int currentMem; // 현재 인원
	private String wish; // 좋아요 여부
	private String createMem; //그룹 만든 사람
	private String category;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private int count;
    
    
    
    public Group(int groupNo, String groupName, String groupDescript, int groupLimit, Date groupCreateday,
			String groupArea, String groupProfile, int status, String categoryName, int currentMem, String wish,
			int count, String category, String boardTitle, String boardContent, String boardWriter) {
		super();
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.groupDescript = groupDescript;
		this.groupLimit = groupLimit;
		this.groupCreateday = groupCreateday;
		this.groupArea = groupArea;
		this.groupProfile = groupProfile;
		this.status = status;
		this.categoryName = categoryName;
		this.currentMem = currentMem;
		this.wish = wish;
		this.count = count;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
	}


	public Group(int groupNo, String groupName) {
		super();
		this.groupNo = groupNo;
		this.groupName = groupName;
	}

	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
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



	public String getBoardWriter() {
		return boardWriter;
	}



	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}



	public Group() {
		super();
	}

	

	public Group(int groupNo, String groupName, String groupDescript, int groupLimit, Date groupCreateday,
			String groupArea, String groupProfile, int status, String categoryName, int currentMem, String wish,
			String createMem) {
		super();
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.groupDescript = groupDescript;
		this.groupLimit = groupLimit;
		this.groupCreateday = groupCreateday;
		this.groupArea = groupArea;
		this.groupProfile = groupProfile;
		this.status = status;
		this.categoryName = categoryName;
		this.currentMem = currentMem;
		this.wish = wish;
		this.createMem = createMem;
	}



	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescript() {
		return groupDescript;
	}

	public void setGroupDescript(String groupDescript) {
		this.groupDescript = groupDescript;
	}

	public int getGroupLimit() {
		return groupLimit;
	}

	public void setGroupLimit(int groupLimit) {
		this.groupLimit = groupLimit;
	}

	public Date getGroupCreateday() {
		return groupCreateday;
	}

	public void setGroupCreateday(Date groupCreateday) {
		this.groupCreateday = groupCreateday;
	}

	public String getGroupArea() {
		return groupArea;
	}

	public void setGroupArea(String groupArea) {
		this.groupArea = groupArea;
	}

	public String getGroupProfile() {
		return groupProfile;
	}

	public void setGroupProfile(String groupProfile) {
		this.groupProfile = groupProfile;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCurrentMem() {
		return currentMem;
	}

	public void setCurrentMem(int currentMem) {
		this.currentMem = currentMem;
	}

	public String getWish() {
		return wish;
	}

	public void setWish(String wish) {
		this.wish = wish;
	}
	

	public String getCreateMem() {
		return createMem;
	}



	public void setCreateMem(String createMem) {
		this.createMem = createMem;
	}



	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public String toString() {
		return "Group [groupNo=" + groupNo + ", groupName=" + groupName + ", groupDescript=" + groupDescript
				+ ", groupLimit=" + groupLimit + ", groupCreateday=" + groupCreateday + ", groupArea=" + groupArea
				+ ", groupProfile=" + groupProfile + ", status=" + status + ", categoryName=" + categoryName
				+ ", currentMem=" + currentMem + ", wish=" + wish + ", createMem=" + createMem + ", category="
				+ category + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", boardWriter="
				+ boardWriter + "]";
	}



	

}
