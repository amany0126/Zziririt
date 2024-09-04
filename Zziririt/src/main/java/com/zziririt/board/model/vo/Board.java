package com.zziririt.board.model.vo;

public class Board { // Board 클래스 영역 시작
	
	// 선언부
	private int boardNo; 			//	BOARD_NO	NUMBER
	private String boardTitle; 		//	BOARD_TITLE	VARCHAR2(30 CHAR)
	private String boardContent; 	//	BOARD_CONTENT	VARCHAR2(200 CHAR)
	private String createTime; 		//	CREATE_TIME	DATE
	private String meetingTime; 	//	MEETING_TIME	DATE(Date 타입이지만 가공을 위해 String 형으로)
	private String meetingSpot; 	//	MEETING_SPOT	VARCHAR2(30 CHAR)
	private String fileInfo; 		//	FILE_INFO	VARCHAR2(50 CHAR)
	private int peopleLimit; 		//	PEOPLE_LIMIT	NUMBER
	private int status; 			//	STATUS	NUMBER(게시글 존재/삭제여부)
	private String boardWriter; 	//	BOARD_WRITER	NUMBER > 정수형 또는 문자열형으로 사용할 예정
									// (회원번호, 회원아이디 둘다 취급 가능)
	private String categoryName; 	//	CATEGORY_NAME	VARCHAR2(20 CHAR)
	private int meetingStatus; 		//	MEETING_STATUS	NUMBER(모집중/모집완료여부)
	private int count; 				//	COUNT	NUMBER
	
	private int replyCount; 		// 댓글 수 (기존 Board 클래스에 추가만)
	private int likeCount; 			// 좋아요 수 (기존 Board 클래스에 추가만)
	
	private String fileOriginName;	// 파일 원본명 (기존 Board 클래스에 추가만)
	
	private String writerProfile;   // 작성자의 이미지 파일 경로 (기존 Board 클래스에 추가만)
	
	// 생성자부
	// 기본생성자
	public Board() { }
	
	// (모든) 매개변수 생성자
	public Board(int boardNo, String boardTitle, String boardContent, String createTime, String meetingTime,
			String meetingSpot, String fileInfo, int peopleLimit, int status, String boardWriter, String categoryName,
			int meetingStatus, int count, String fileOriginName, String writerProfile) {
		super();
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
		this.fileOriginName = fileOriginName;
		this.writerProfile = writerProfile;
	}
	
	// 일반게시글 목록 조회용 생성자
	public Board(int boardNo, String boardTitle, String createTime, String boardWriter, String categoryName,
			int meetingStatus, int count, int replyCount, int likeCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.createTime = createTime;
		this.boardWriter = boardWriter;
		this.categoryName = categoryName;
		this.meetingStatus = meetingStatus;
		this.count = count;
		this.replyCount = replyCount;
		this.likeCount = likeCount;
	}
	
	// 일반게시글 상세조회용 생성자
	public Board(int boardNo, String boardTitle, String boardContent, String createTime, String meetingTime,
			String meetingSpot, int peopleLimit, String boardWriter, String categoryName,
			int meetingStatus, int count, int replyCount, int likeCount, String fileInfo, String fileOriginName, String writerProfile) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.createTime = createTime;
		this.meetingTime = meetingTime;
		this.meetingSpot = meetingSpot;
		this.peopleLimit = peopleLimit;
		this.boardWriter = boardWriter;
		this.categoryName = categoryName;
		this.meetingStatus = meetingStatus;
		this.count = count;
		this.replyCount = replyCount;
		this.likeCount = likeCount;
		this.fileInfo = fileInfo;
		this.fileOriginName = fileOriginName;
		this.writerProfile = writerProfile;
	}
	
	// 메서드부
	// getter / setter 메서드들
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
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
	
	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getFileOriginName() {
		return fileOriginName;
	}

	public void setFileOriginName(String fileOriginName) {
		this.fileOriginName = fileOriginName;
	}

	public String getWriterProfile() {
		return writerProfile;
	}

	public void setWriterProfile(String writerProfile) {
		this.writerProfile = writerProfile;
	}

	// toString 메서드
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", createTime=" + createTime + ", meetingTime=" + meetingTime + ", meetingSpot=" + meetingSpot
				+ ", fileInfo=" + fileInfo + ", peopleLimit=" + peopleLimit + ", status=" + status + ", boardWriter="
				+ boardWriter + ", categoryName=" + categoryName + ", meetingStatus=" + meetingStatus + ", count="
				+ count + ", replyCount=" + replyCount + ", likeCount=" + likeCount + ", fileOriginName="
				+ fileOriginName + ", writerProfile=" + writerProfile + "]";
	}
} // Board 클래스 영역 끝