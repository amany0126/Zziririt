package com.zziririt.board.model.vo;

public class Reply { // Reply 클래스 영역 시작
	
	// 선언부
	private int replyNo;				//	REPLY_NO	NUMBER
	private String replyContent;		//	REPLY_CONTENT	VARCHAR2(500 CHAR)
	private int refBoardNo;				//	REF_BNO	NUMBER
	private String replyWriter;			//	REPLY_WRITER	NUMBER
										//  작성자 닉네임
	private String createTime;			//	CREATE_TIME	DATE (날짜 > String 타입)
	private int status; 				//	STATUS	NUMBER
	
	private String replyWriterProfile; 	//  댓글 작성자의 프로필 이미지 (기존 Reply 클래스에 추가만)
	
	// 생성자부
	// 기본생성자
	public Reply() { }
	
	// (모든) 매개변수 생성자
	public Reply(int replyNo, String replyContent, int refBoardNo, String replyWriter, String createTime, int status, String replyWriterProfile) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBoardNo = refBoardNo;
		this.replyWriter = replyWriter;
		this.createTime = createTime;
		this.status = status;
		this.replyWriterProfile = replyWriterProfile;
	}
	
	// 댓글목록 조회용 생성자
	public Reply(int replyNo, String replyContent, String replyWriter, String createTime, String replyWriterProfile) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
		this.createTime = createTime;
		this.replyWriterProfile = replyWriterProfile;
	}
	
	// 메서드부
	// getter / setter 메서드들
	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getRefBoardNo() {
		return refBoardNo;
	}

	public void setRefBoardNo(int refBoardNo) {
		this.refBoardNo = refBoardNo;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public String getcreateTime() {
		return createTime;
	}

	public void setcreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getReplyWriterProfile() {
		return replyWriterProfile;
	}

	public void setReplyWriterProfile(String replyWriterProfile) {
		this.replyWriterProfile = replyWriterProfile;
	}

	// toString 메서드
	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", refBoardNo=" + refBoardNo
				+ ", replyWriter=" + replyWriter + ", createTime=" + createTime + ", status=" + status
				+ ", replyWriterProfile=" + replyWriterProfile + "]";
	}
} // Reply 클래스 영역 시작